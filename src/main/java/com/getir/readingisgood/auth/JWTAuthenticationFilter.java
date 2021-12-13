package com.getir.readingisgood.auth;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgood.constants.AppConstants;
import com.getir.readingisgood.entity.CustomerEntity;
import com.getir.readingisgood.enums.ResponseStatusType;
import com.getir.readingisgood.mapper.LoginMapper;
import com.getir.readingisgood.mapper.ResponseMapper;
import com.getir.readingisgood.model.request.LoginRequestModel;
import com.getir.readingisgood.model.response.BaseResponseModel;
import com.getir.readingisgood.model.response.LoginResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.getir.readingisgood.constants.JwtConstants.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/***
 Created on 2021

 @author emre.gozukucuk
 **/

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;

        setFilterProcessesUrl(SIGN_UP_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            final LoginRequestModel loginRequestModel = new ObjectMapper().readValue(req.getInputStream(), LoginRequestModel.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestModel.getEmail(),
                            loginRequestModel.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            log.error("JWTAuthenticationFilter :: attemptAuthentication :: req = {} :: res = {}", req, res, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
        final String token = JWT.create()
                .withClaim("email", ((CustomerEntity) auth.getPrincipal()).getEmail())
                .withClaim("customerId", ((CustomerEntity) auth.getPrincipal()).getCustomerId())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SIGNING_KEY.getBytes()));
        res.addHeader(AUTHORIZATION, token);
        res.setStatus(HttpServletResponse.SC_OK);
        prepareResponse(res, prepareLoginResponse(token));
        log.info("JWTAuthorizationFilter :: successfulAuthentication :: req = {} :: res = {} :: chain = {} :: message = {}", req, res, chain, "Login success!");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        baseResponseModel.setResponseStatusType(ResponseStatusType.FAIL);
        baseResponseModel.setMessage("Invalid email or password!");
        prepareResponse(response, baseResponseModel);
        log.info("JWTAuthorizationFilter :: unsuccessfulAuthentication :: request = {} :: response = {} :: failed = {} :: message = {}", request, response, failed, "Login fail!");
    }

    private LoginResponseModel prepareLoginResponse(String authorizationToken) {
        BaseResponseModel baseResponseModel = ResponseMapper.INSTANCE.fieldToModel(ResponseStatusType.SUCCESS, AppConstants.LOGIN_SUCCESS);
        return LoginMapper.INSTANCE.loginEntityToLoginResponse(TOKEN_PREFIX.concat(authorizationToken), baseResponseModel);
    }

    private void prepareResponse(HttpServletResponse res, BaseResponseModel baseResponseModel) throws IOException {
        res.setContentType("application/json;charset=UTF-8");
        final PrintWriter writer = res.getWriter();
        writer.write(objectMapper.writeValueAsString(baseResponseModel));
        writer.flush();
        writer.close();
    }
}