package com.getir.readingisgood.auth;

/***
 Created on 2021

 @author emre.gozukucuk
 **/

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgood.constants.JwtConstants;
import com.getir.readingisgood.enums.ResponseStatusType;
import com.getir.readingisgood.mapper.ResponseMapper;
import com.getir.readingisgood.model.response.BaseResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final ObjectMapper objectMapper;

    public JWTAuthorizationFilter(AuthenticationManager authManager, ObjectMapper objectMapper) {
        super(authManager);
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String token = req.getHeader(AUTHORIZATION);

        if (token == null || !token.startsWith(JwtConstants.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication;
        try {
            authentication = getAuthentication(token);
        } catch (Exception exception) {
            log.error("JWTAuthorizationFilter :: doFilterInternal :: req = {} :: res = {} :: chain = {}", req, res, chain, exception);
            SecurityContextHolder.clearContext();
            BaseResponseModel baseResponseModel = ResponseMapper.INSTANCE.fieldToModel(ResponseStatusType.FAIL, exception.getMessage());
            prepareResponse(res, baseResponseModel);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        final String email = JWT.require(Algorithm.HMAC512(JwtConstants.SIGNING_KEY.getBytes()))
                .build()
                .verify(token.replace(JwtConstants.TOKEN_PREFIX, ""))
                .getClaim("email")
                .asString();
        if (email != null) {
            return new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
        }
        log.error("JWTAuthorizationFilter :: UsernamePasswordAuthenticationToken :: token = {} :: message = {}", token, "Email is not present in token!");
        throw new BadCredentialsException("Email is not present in token!");
    }

    private void prepareResponse(HttpServletResponse res, BaseResponseModel baseResponseModel) throws IOException {
        res.setContentType("application/json;charset=UTF-8");
        final PrintWriter writer = res.getWriter();
        writer.write(objectMapper.writeValueAsString(baseResponseModel));
        writer.flush();
        writer.close();
    }
}