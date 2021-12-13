package com.getir.readingisgood.controller;

import com.getir.readingisgood.constants.HeaderConstants;
import com.getir.readingisgood.model.request.CreateBookRequestModel;
import com.getir.readingisgood.model.request.UpdateBookStockCountModel;
import com.getir.readingisgood.model.response.BookResponseModel;
import com.getir.readingisgood.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public ResponseEntity<BookResponseModel> createBook(@RequestBody @Valid CreateBookRequestModel createBookRequestModel) {
        return ResponseEntity.ok(bookService.createBook(createBookRequestModel));
    }

    @PostMapping("/update-stock")
    public ResponseEntity<BookResponseModel> updateBookStock(@RequestBody @Valid UpdateBookStockCountModel updateBookStockCountModel
            , @RequestHeader(value = HeaderConstants.UPDATE_STOCK_HEADER) String updateByIncOrDec) {
        return ResponseEntity.ok(bookService.updateBookStock(updateBookStockCountModel.getBookId(), updateBookStockCountModel.getUpdatedStockCount(), updateByIncOrDec));
    }
}
