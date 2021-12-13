package com.getir.readingisgood.mapper;

import com.getir.readingisgood.enums.ResponseStatusType;
import com.getir.readingisgood.model.response.BaseResponseModel;
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
public class ResponseMapperTest {

    @Test
    void fieldToModelTest(){
        //given
        ResponseStatusType responseStatusType = ResponseStatusType.SUCCESS;
        String message = "test";

        //when
        BaseResponseModel baseResponseModel = ResponseMapper.INSTANCE.fieldToModel(responseStatusType, message);

        //then
        assertThat(baseResponseModel.getResponseStatusType()).isEqualTo(responseStatusType);
        assertThat(baseResponseModel.getMessage()).isEqualTo(message);
    }
}
