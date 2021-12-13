package com.getir.readingisgood.service;

import com.getir.readingisgood.constants.AppConstants;
import com.getir.readingisgood.entity.CustomerEntity;
import com.getir.readingisgood.enums.ResponseStatusType;
import com.getir.readingisgood.exception.BusinessException;
import com.getir.readingisgood.mapper.CustomerMapper;
import com.getir.readingisgood.mapper.ResponseMapper;
import com.getir.readingisgood.model.request.CustomerRegisterRequestModel;
import com.getir.readingisgood.model.response.BaseResponseModel;
import com.getir.readingisgood.model.response.CustomerResponseModel;
import com.getir.readingisgood.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/***
 Created on 2021

 @author emre.gozukucuk
 **/

@Slf4j
@Service
public class RegisterService implements UserDetailsService {

    public static final String USER_ALREADY_EXIST_ERROR = "User already exist error.";
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegisterService(CustomerRepository customerRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public CustomerResponseModel registerCustomer(CustomerRegisterRequestModel customerRegisterRequestModel) {
        checkCustomerAlreadyExists(customerRegisterRequestModel.getEmail());
        CustomerEntity customerEntity = customerRepository.save(createCustomer(customerRegisterRequestModel));
        BaseResponseModel baseResponseModel = ResponseMapper.INSTANCE.fieldToModel(ResponseStatusType.SUCCESS, AppConstants.REGISTER_SUCCESS);
        return CustomerMapper.INSTANCE.customerEntityToCustomerModel(baseResponseModel,customerEntity);
    }

    private CustomerEntity createCustomer(CustomerRegisterRequestModel registerRequest) {
       return CustomerMapper.INSTANCE.customerModelToCustomerEntity(registerRequest,bCryptPasswordEncoder.encode(registerRequest.getPassword()) );
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessException("User not found with given email: " + username));
    }

    private void checkCustomerAlreadyExists(String email) {
        Optional<CustomerEntity> registeredUser = customerRepository.findByEmail(email);
        if (registeredUser.isPresent()) {
            log.error("RegisterService :: checkCustomerAlreadyExists :: email = {} :: message = {}", email, USER_ALREADY_EXIST_ERROR);
            throw new BusinessException("User already exists with this email: " + email);
        }
    }
}
