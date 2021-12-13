package com.getir.readingisgood.mapper;

import com.getir.readingisgood.builder.BaseResponseModelStubBuilder;
import com.getir.readingisgood.builder.CustomerRegisterRequestModelStubBuilder;
import com.getir.readingisgood.builder.entity.CustomerEntityStubBuilder;
import com.getir.readingisgood.entity.CustomerEntity;
import com.getir.readingisgood.enums.StatusType;
import com.getir.readingisgood.model.request.CustomerRegisterRequestModel;
import com.getir.readingisgood.model.response.BaseResponseModel;
import com.getir.readingisgood.model.response.CustomerResponseModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;

/***
 Created on 2021

 @author emre.gozukucuk
 **/

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerMapperTest {

    @Test
    void customerModelToCustomerEntityTest() {
        //given
        CustomerRegisterRequestModel registerRequestModel = CustomerRegisterRequestModelStubBuilder.create();
        String encryptedPassword = "password";

        //when
        CustomerEntity customerEntity = CustomerMapper.INSTANCE.customerModelToCustomerEntity(registerRequestModel, encryptedPassword);

        //then
        assertThat(customerEntity.getName()).isEqualTo(registerRequestModel.getName());
        assertThat(customerEntity.getSurname()).isEqualTo(registerRequestModel.getSurname());
        assertThat(customerEntity.getPassword()).isEqualTo(encryptedPassword);
        assertThat(customerEntity.getEmail()).isEqualTo(registerRequestModel.getEmail());
        assertThat(customerEntity.getAccountNonExpired()).isTrue();
        assertThat(customerEntity.getAccountNonLocked()).isTrue();
        assertThat(customerEntity.getCredentialsNonExpired()).isTrue();
        assertThat(customerEntity.getEnabled()).isTrue();
        assertThat(customerEntity.getStatus()).isEqualTo(StatusType.ACTIVE);
    }

    @Test
    void customerEntityToCustomerModelTest() {
        //given
        BaseResponseModel baseResponseModel = BaseResponseModelStubBuilder.create();
        CustomerEntity customerEntity = CustomerEntityStubBuilder.create();

        //when
        CustomerResponseModel customerResponseModel = CustomerMapper.INSTANCE.customerEntityToCustomerModel(baseResponseModel, customerEntity);

        //then
        assertThat(customerResponseModel.getName()).isEqualTo(customerEntity.getName());
        assertThat(customerResponseModel.getSurname()).isEqualTo(customerEntity.getSurname());
        assertThat(customerResponseModel.getEmail()).isEqualTo(customerEntity.getEmail());
        assertThat(customerResponseModel.getAccountNonExpired()).isEqualTo(customerEntity.getAccountNonExpired());
        assertThat(customerResponseModel.getAccountNonLocked()).isEqualTo(customerEntity.getAccountNonLocked());
        assertThat(customerResponseModel.getCredentialsNonExpired()).isEqualTo(customerEntity.getCredentialsNonExpired());
        assertThat(customerResponseModel.getEnabled()).isEqualTo(customerEntity.getEnabled());
        assertThat(customerEntity.getStatus()).isEqualTo(StatusType.ACTIVE);
        assertThat(customerResponseModel.getMessage()).isEqualTo(baseResponseModel.getMessage());
        assertThat(customerResponseModel.getResponseStatusType()).isEqualTo(baseResponseModel.getResponseStatusType());
    }

}
