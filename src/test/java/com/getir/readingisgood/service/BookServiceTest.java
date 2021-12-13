package com.getir.readingisgood.service;

import com.getir.readingisgood.builder.CreateBookRequestModelStubBuilder;
import com.getir.readingisgood.builder.entity.BookEntityStubBuilder;
import com.getir.readingisgood.entity.BookEntity;
import com.getir.readingisgood.exception.BusinessException;
import com.getir.readingisgood.model.request.CreateBookRequestModel;
import com.getir.readingisgood.model.response.BookResponseModel;
import com.getir.readingisgood.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    void createBook() {
        //given
        CreateBookRequestModel bookRequestModel = CreateBookRequestModelStubBuilder.create();

        //when
        when(bookRepository.save(any(BookEntity.class))).thenReturn(BookEntityStubBuilder.create());

        //then
        BookResponseModel book = bookService.createBook(bookRequestModel);

        assertThat(book.getBookName()).isEqualTo("Tutunamayanlar");
        assertThat(book.getStockCount()).isEqualTo(10);
        assertThat(book.getPrice()).isEqualTo(BigDecimal.valueOf(45.0));

    }

    @Test
    void updateBookStock_increase() {
        //given
        String bookId = "1111";
        int increasedStockCount = 2;
        String updateByIncOrDe = "increase";

        //when
        when(bookRepository.findByBookId(anyString())).thenReturn(Optional.of(BookEntityStubBuilder.create()));

        //then
        BookResponseModel bookResponseModel = bookService.updateBookStock(bookId, increasedStockCount, updateByIncOrDe);

        assertThat(bookResponseModel.getStockCount()).isEqualTo(12);
    }

    @Test
    void updateBookStock_decrease() {
        //given
        String bookId = "1111";
        int decreasedStockCount = 2;
        String updateByIncOrDe = "decrease";

        //when
        when(bookRepository.findByBookId(anyString())).thenReturn(Optional.of(BookEntityStubBuilder.create()));

        //then
        BookResponseModel bookResponseModel = bookService.updateBookStock(bookId, decreasedStockCount, updateByIncOrDe);

        assertThat(bookResponseModel.getStockCount()).isEqualTo(8);
    }

    @Test
    void updateBookStock_should_throw_when_update_type_not_in_type() {
        //given
        String bookId = "1111";
        int decreasedStockCount = 2;
        String updateByIncOrDe = "test";

        //when
        when(bookRepository.findByBookId(anyString())).thenReturn(Optional.of(BookEntityStubBuilder.create()));

        //then
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            bookService.updateBookStock(bookId, decreasedStockCount, updateByIncOrDe);
        });

        assertThat(businessException.getMessage()).isEqualTo("Invalid update stock type. It should be increase or decrease");
    }

    @Test
    void checkRemainingStock_should_throw_error_when_decreased_is_less_then_amount() {
        //given
        String bookId = "1111";
        int decreasedStockCount = 11;
        String updateByIncOrDe = "decrease";

        //when
        when(bookRepository.findByBookId(anyString())).thenReturn(Optional.of(BookEntityStubBuilder.create()));

        //then
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            bookService.updateBookStock(bookId, decreasedStockCount, updateByIncOrDe);
        });

        assertThat(businessException.getMessage()).isEqualTo("Trying to buy " + decreasedStockCount + " but remaining stock count is: 10");
    }

    @Test
    public void should_throw_exception_when_book_not_found_with_given_id() {
        //given
        String bookId = "1111";

        //when
        when(bookRepository.findByBookId(anyString())).thenReturn(Optional.empty());

        //then
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            bookService.getBookById(bookId);
        });

        assertThat(businessException.getMessage()).isEqualTo("Book not found with given id: " + bookId);
    }

    @Test
    public void checkBookIdIsExist_should_throw_exception_when_book_not_found_with_given_id() {
        //given
        String bookId = "1111";

        //when
        when(bookRepository.findByBookId(anyString())).thenReturn(Optional.empty());

        //then
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            bookService.getBookById(bookId);
        });

        assertThat(businessException.getMessage()).isEqualTo("Book not found with given id: " + bookId);
    }


}