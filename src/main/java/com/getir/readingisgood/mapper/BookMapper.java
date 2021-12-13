package com.getir.readingisgood.mapper;

import com.getir.readingisgood.entity.BookEntity;
import com.getir.readingisgood.model.request.CreateBookRequestModel;
import com.getir.readingisgood.model.response.BaseResponseModel;
import com.getir.readingisgood.model.response.BookResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Date;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Mapper(componentModel = "spring", imports = { Date.class})
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper( BookMapper.class );

    @Mapping(target = "createDate", expression = "java(new Date())")
    @Mapping(target = "lastUpdateDate", expression = "java(new Date())")
    BookEntity bookToBookEntity(CreateBookRequestModel createBookRequestModel);

    @Mapping(target = "createDate", source = "bookEntity.createDate")
    @Mapping(target = "lastUpdateDate", source = "bookEntity.lastUpdateDate")
    BookResponseModel bookEntityToBookModel(BaseResponseModel baseResponseModel, BookEntity bookEntity);
}
