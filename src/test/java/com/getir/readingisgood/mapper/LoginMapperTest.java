package com.getir.readingisgood.mapper;

import com.getir.readingisgood.builder.BaseResponseModelStubBuilder;
import com.getir.readingisgood.model.response.BaseResponseModel;
import com.getir.readingisgood.model.response.LoginResponseModel;
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
public class LoginMapperTest {

    @Test
    void loginEntityToLoginResponseTest(){
        //given
        BaseResponseModel baseResponseModel = BaseResponseModelStubBuilder.create();
        String authToken = "Bearer 18248503295afijc3014";

        //when
        LoginResponseModel loginResponseModel = LoginMapper.INSTANCE.loginEntityToLoginResponse(authToken, baseResponseModel);

        //then
        assertThat(loginResponseModel.getAuthToken()).isEqualTo(authToken);
        assertThat(loginResponseModel.getMessage()).isEqualTo(baseResponseModel.getMessage());
        assertThat(loginResponseModel.getResponseStatusType()).isEqualTo(baseResponseModel.getResponseStatusType());

    }
}
