package com.getir.readingisgood.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgood.entity.AuditEntity;
import com.getir.readingisgood.enums.OperationType;
import com.getir.readingisgood.mapper.AuditMapper;
import com.getir.readingisgood.repository.AuditRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

import static com.getir.readingisgood.constants.JwtConstants.AUTHORIZATION_BEARER_LENGTH;
import static com.getir.readingisgood.constants.JwtConstants.EMAIL_KEY;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Slf4j
@Service
public class AuditService {

    private final AuditRepository auditRepository;
    private final CustomerService customerService;

    private AuditService(AuditRepository auditRepository, CustomerService customerService) {
        this.auditRepository = auditRepository;
        this.customerService = customerService;
    }

    public void addAudit(HttpServletRequest servlet, OperationType operation, String message) throws IOException {
        String token = servlet.getHeader("Authorization");
        String customerEmail = null;
        if (!token.isBlank()) {
            customerEmail = getCustomerEmailFromAuthorizationToken(token);
        }
        final AuditEntity auditEntity = AuditMapper.INSTANCE.fieldToAuditEntity(customerEmail, operation, message);
        auditRepository.save(auditEntity);
        log.info("AuditService :: addAudit :: servlet = {} :: operation = {} :: message = {} :: message = {}", servlet, operation, message, "New audit saved");
    }

    private String getCustomerEmailFromAuthorizationToken(String authorizationToken) throws IOException {
        String token = authorizationToken.substring(AUTHORIZATION_BEARER_LENGTH);
        Jwt jwt = JwtHelper.decode(token);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> claimsMap = objectMapper.readValue(jwt.getClaims(), new TypeReference<>() {
        });

        return (String) claimsMap.get(EMAIL_KEY);
    }

}
