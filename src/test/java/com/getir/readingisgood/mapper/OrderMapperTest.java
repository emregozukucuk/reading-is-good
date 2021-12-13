package com.getir.readingisgood.mapper;

import com.getir.readingisgood.builder.BaseResponseModelStubBuilder;
import com.getir.readingisgood.builder.CreateOrderRequestModelStubBuilder;
import com.getir.readingisgood.builder.entity.OrderEntityStubBuilder;
import com.getir.readingisgood.entity.OrderEntity;
import com.getir.readingisgood.model.request.CreateOrderRequestModel;
import com.getir.readingisgood.model.response.BaseResponseModel;
import com.getir.readingisgood.model.response.OrderResponseModelModel;
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
public class OrderMapperTest {

    @Test
    void orderModelToOrderEntityTest(){
        //given
        CreateOrderRequestModel orderRequestModel = CreateOrderRequestModelStubBuilder.create();

        //when
        OrderEntity orderEntity = OrderMapper.INSTANCE.orderModelToOrderEntity(orderRequestModel);

        //then
        assertThat(orderEntity.getBookId()).isEqualTo(orderRequestModel.getBookId());
        assertThat(orderEntity.getCustomerId()).isEqualTo(orderRequestModel.getCustomerId());
        assertThat(orderEntity.getPurchasedBookCount()).isEqualTo(orderRequestModel.getPurchasedBookCount());
    }

    @Test
    void orderEntityToOrderModelTest(){
        //when
        OrderEntity orderEntity = OrderEntityStubBuilder.create();
        BaseResponseModel baseResponseModel = BaseResponseModelStubBuilder.create();

        //given
        OrderResponseModelModel orderResponseModelModel = OrderMapper.INSTANCE.orderEntityToOrderModel(baseResponseModel, orderEntity);

        //then
        assertThat(orderResponseModelModel.getCustomerId()).isEqualTo(orderEntity.getCustomerId());
        assertThat(orderResponseModelModel.getBookId()).isEqualTo(orderEntity.getBookId());
        assertThat(orderResponseModelModel.getPurchasedBookCount()).isEqualTo(orderEntity.getPurchasedBookCount());
        assertThat(orderResponseModelModel.getMessage()).isEqualTo(baseResponseModel.getMessage());
        assertThat(orderResponseModelModel.getResponseStatusType()).isEqualTo(baseResponseModel.getResponseStatusType());

    }
}
