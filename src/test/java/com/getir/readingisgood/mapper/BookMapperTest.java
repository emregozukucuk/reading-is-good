package com.getir.readingisgood.mapper;

import com.getir.readingisgood.builder.BaseResponseModelStubBuilder;
import com.getir.readingisgood.builder.CreateBookRequestModelStubBuilder;
import com.getir.readingisgood.builder.entity.BookEntityStubBuilder;
import com.getir.readingisgood.entity.BookEntity;
import com.getir.readingisgood.model.request.CreateBookRequestModel;
import com.getir.readingisgood.model.response.BaseResponseModel;
import com.getir.readingisgood.model.response.BookResponseModel;
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
public class BookMapperTest {

    @Test
    void bookToBookEntityTest(){
        //given
        CreateBookRequestModel bookRequestModel = CreateBookRequestModelStubBuilder.create();

        //when
        BookEntity bookEntity = BookMapper.INSTANCE.bookToBookEntity(bookRequestModel);

        //then
        assertThat(bookEntity.getBookName()).isEqualTo(bookRequestModel.getBookName());
        assertThat(bookEntity.getPrice()).isEqualTo(bookRequestModel.getPrice());
        assertThat(bookEntity.getStockCount()).isEqualTo(bookRequestModel.getStockCount());
    }

    @Test
    void bookEntityToBookModelTest(){
        //given
        BaseResponseModel baseResponseModel = BaseResponseModelStubBuilder.create();
        BookEntity bookEntity = BookEntityStubBuilder.create();

        //when
        BookResponseModel bookResponseModel = BookMapper.INSTANCE.bookEntityToBookModel(baseResponseModel, bookEntity);

        //then
        assertThat(bookResponseModel.getBookName()).isEqualTo(bookEntity.getBookName());
        assertThat(bookResponseModel.getPrice()).isEqualTo(bookEntity.getPrice());
        assertThat(bookResponseModel.getStockCount()).isEqualTo(bookEntity.getStockCount());
        assertThat(bookResponseModel.getMessage()).isEqualTo(baseResponseModel.getMessage());
        assertThat(bookResponseModel.getResponseStatusType()).isEqualTo(baseResponseModel.getResponseStatusType());

    }

}
