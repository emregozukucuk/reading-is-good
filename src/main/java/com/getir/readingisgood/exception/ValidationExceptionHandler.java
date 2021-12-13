package com.getir.readingisgood.exception;

import com.getir.readingisgood.enums.ResponseStatusType;
import com.getir.readingisgood.mapper.ResponseMapper;
import com.getir.readingisgood.model.exception.MessageModel;
import com.getir.readingisgood.model.response.BaseResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.print.attribute.standard.Severity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@ControllerAdvice
public class ValidationExceptionHandler{

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<BaseResponseModel> handleConstraintViolationException(ConstraintViolationException ex) {
        BaseResponseModel baseResponseModel = ResponseMapper.INSTANCE.fieldToModel(ResponseStatusType.FAIL, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponseModel);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<BaseResponseModel> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> invalidFieldMessageList = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            invalidFieldMessageList.add(fieldError.getDefaultMessage());
        }

        BaseResponseModel baseResponseModel = ResponseMapper.INSTANCE.fieldToModel(ResponseStatusType.FAIL, String.join(" , ", invalidFieldMessageList));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponseModel);
    }
}
