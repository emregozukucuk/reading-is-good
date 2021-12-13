package com.getir.readingisgood.service;

import com.getir.readingisgood.constants.AppConstants;
import com.getir.readingisgood.entity.BookEntity;
import com.getir.readingisgood.enums.ResponseStatusType;
import com.getir.readingisgood.enums.UpdateStockType;
import com.getir.readingisgood.exception.BusinessException;
import com.getir.readingisgood.mapper.BookMapper;
import com.getir.readingisgood.mapper.ResponseMapper;
import com.getir.readingisgood.model.request.CreateBookRequestModel;
import com.getir.readingisgood.model.response.BaseResponseModel;
import com.getir.readingisgood.model.response.BookResponseModel;
import com.getir.readingisgood.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Slf4j
@Service
public class BookService {

    public static final String INVALID_UPDATE_ERROR_MESSAGE = "Invalid update stock type. It should be increase or decrease";
    public static final String STOCK_NOT_ENOUGH_ERROR = "Remaining stock not enough error.";
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookEntity getBookById(String bookId) {
        return bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new BusinessException("Book not found with given id: " + bookId));
    }

    public BookResponseModel createBook(CreateBookRequestModel createBookRequestModel) {
        BookEntity bookEntity = BookMapper.INSTANCE.bookToBookEntity(createBookRequestModel);
        bookRepository.save(bookEntity);
        BaseResponseModel baseResponseModel = ResponseMapper.INSTANCE.fieldToModel(ResponseStatusType.SUCCESS, AppConstants.CREATE_BOOK_SUCCESS);
        return BookMapper.INSTANCE.bookEntityToBookModel(baseResponseModel, bookEntity);
    }

    public synchronized BookResponseModel updateBookStock(String bookId, int increasedStockCount, String updateByIncOrDec) {
        if (UpdateStockType.INCREASE.getUpdateType().equals(updateByIncOrDec)) {
            return increaseBookStock(bookId, increasedStockCount);
        } else if (UpdateStockType.DECREASE.getUpdateType().equals(updateByIncOrDec)) {
            return decreaseBookStock(bookId, increasedStockCount);
        } else {
            log.error("BookService :: updateBookStock :: bookId = {} :: increasedStockCount = {} :: updateByIncOrDec = {} :: message = {}", bookId, increasedStockCount, updateByIncOrDec, INVALID_UPDATE_ERROR_MESSAGE);
            throw new BusinessException(INVALID_UPDATE_ERROR_MESSAGE);
        }
    }

    private BookResponseModel increaseBookStock(String bookId, Integer increasedStockCount) {
        BookEntity bookEntity = getBookById(bookId);
        bookEntity.setStockCount(bookEntity.getStockCount() + increasedStockCount);
        bookRepository.save(bookEntity);
        BaseResponseModel baseResponseModel = ResponseMapper.INSTANCE.fieldToModel(ResponseStatusType.SUCCESS, AppConstants.CREATE_BOOK_SUCCESS);
        return BookMapper.INSTANCE.bookEntityToBookModel(baseResponseModel, bookEntity);
    }

    private BookResponseModel decreaseBookStock(String bookId, Integer decreasedStockCount) {
        BookEntity bookEntity = getBookById(bookId);
        checkRemainingStock(decreasedStockCount, bookEntity);
        bookEntity.setStockCount(bookEntity.getStockCount() - decreasedStockCount);
        bookRepository.save(bookEntity);
        BaseResponseModel baseResponseModel = ResponseMapper.INSTANCE.fieldToModel(ResponseStatusType.SUCCESS, AppConstants.CREATE_BOOK_SUCCESS);
        return BookMapper.INSTANCE.bookEntityToBookModel(baseResponseModel, bookEntity);
    }

    private void checkRemainingStock(int decreasedStockCount, BookEntity bookEntity) {
        if (bookEntity.getStockCount() < decreasedStockCount) {
            log.error("BookService :: checkRemainingStock :: decreasedStockCount = {} :: bookEntity = {} :: message = {}", decreasedStockCount, bookEntity, STOCK_NOT_ENOUGH_ERROR);
            throw new BusinessException("Trying to buy " + decreasedStockCount + " but remaining stock count is: " + bookEntity.getStockCount().toString());
        }
    }

    public void checkBookIdIsExist(String bookId) {
        bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new BusinessException("Book not found with given id: " + bookId));
    }
}
