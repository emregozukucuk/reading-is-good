package com.getir.readingisgood.service;

import com.getir.readingisgood.builder.CustomerRegisterRequestModelStubBuilder;
import com.getir.readingisgood.builder.entity.CustomerEntityStubBuilder;
import com.getir.readingisgood.entity.CustomerEntity;
import com.getir.readingisgood.exception.BusinessException;
import com.getir.readingisgood.model.request.CustomerRegisterRequestModel;
import com.getir.readingisgood.model.response.CustomerResponseModel;
import com.getir.readingisgood.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RegisterServiceTest {

    @InjectMocks
    private RegisterService registerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Test
    void registerCustomer() {
        //given
        CustomerRegisterRequestModel registerRequestModel = CustomerRegisterRequestModelStubBuilder.create();

        //when
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(CustomerEntityStubBuilder.create());
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encrypted");

        //then
        CustomerResponseModel customerResponseModel = registerService.registerCustomer(registerRequestModel);

        assertThat(customerResponseModel.getEmail()).isEqualTo(registerRequestModel.getEmail());
        assertThat(customerResponseModel.getName()).isEqualTo(registerRequestModel.getName());
        assertThat(customerResponseModel.getSurname()).isEqualTo(registerRequestModel.getSurname());
    }

    @Test
    void registerCustomer_should_throw_exception_when_try_to_register_with_existing_email() {
        //given
        CustomerRegisterRequestModel registerRequestModel = CustomerRegisterRequestModelStubBuilder.create();

        //when
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.of(CustomerEntityStubBuilder.create()));
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(CustomerEntityStubBuilder.create());
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encrypted");

        //then
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            registerService.registerCustomer(registerRequestModel);
        });

        assertThat(businessException.getMessage()).isEqualTo("User already exists with this email: " + registerRequestModel.getEmail());
    }

    @Test
    void loadUserByUsername_should_throw_exception_when_email_not_found_given_email() {
        //given
        String email = "test@test.com";

        //when
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        //then
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            registerService.loadUserByUsername(email);
        });

        assertThat(businessException.getMessage()).isEqualTo("User not found with given email: " + email);

    }
}