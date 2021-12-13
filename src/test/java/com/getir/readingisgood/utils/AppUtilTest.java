package com.getir.readingisgood.utils;

import com.getir.readingisgood.builder.entity.OrderEntityStubBuilder;
import com.getir.readingisgood.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AppUtilTest {

    @Test
    void getPage() {
        //given
        List<OrderEntity> orderEntityList = OrderEntityStubBuilder.createList();
        int page = 1;
        int pageSize = 1;

        //when
        List<OrderEntity> entityList = AppUtil.getPage(orderEntityList, page, pageSize);

        //then
        assertThat(entityList).isNotNull();
        assertThat(entityList.size()).isEqualTo(1);
    }

    @Test
    void getPage_should_return_all_list_when_page_equals_zero() {
        //given
        List<OrderEntity> orderEntityList = OrderEntityStubBuilder.createList();
        int page = 0;
        int pageSize = 1;

        //when
        List<OrderEntity> entityList = AppUtil.getPage(orderEntityList, page, pageSize);

        //then
        assertThat(entityList).isNotNull();
        assertThat(entityList.size()).isEqualTo(orderEntityList.size());
    }

    @Test
    void getPage_should_return_all_list_when_page_size_equals_zero() {
        //given
        List<OrderEntity> orderEntityList = OrderEntityStubBuilder.createList();
        int page = 1;
        int pageSize = 0;

        //when
        List<OrderEntity> entityList = AppUtil.getPage(orderEntityList, page, pageSize);

        //then
        assertThat(entityList).isNotNull();
        assertThat(entityList.size()).isEqualTo(orderEntityList.size());
    }

    @Test
    void isNullOrEmpty_should_return_true_when_input_empty() {
        //given
        String empty = "";

        //when
        boolean nullOrEmpty = AppUtil.isNullOrEmpty(empty);

        //then
        assertThat(nullOrEmpty).isEqualTo(true);
    }

    @Test
    void isNullOrEmpty_should_return_true_when_input_null() {
        //given
        String empty = null;

        //when
        boolean nullOrEmpty = AppUtil.isNullOrEmpty(empty);

        //then
        assertThat(nullOrEmpty).isEqualTo(true);
    }

    @Test
    void isNullOrEmpty_should_return_false_when_input_not_null_or_not_empty() {
        //given
        String str = "test";

        //when
        boolean nullOrEmpty = AppUtil.isNullOrEmpty(str);

        //then
        assertThat(nullOrEmpty).isEqualTo(false);
    }
}