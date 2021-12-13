package com.getir.readingisgood.exception;

import com.getir.readingisgood.enums.ResponseStatusType;
import com.getir.readingisgood.mapper.ResponseMapper;
import com.getir.readingisgood.model.exception.MessageModel;
import com.getir.readingisgood.model.response.BaseResponseModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
public class GlobalExceptionHandler{

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<BaseResponseModel> handleLinkFormatException(BusinessException ex) {
        BaseResponseModel baseResponseModel = ResponseMapper.INSTANCE.fieldToModel(ResponseStatusType.FAIL, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponseModel);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<BaseResponseModel> handleRuntimeException(RuntimeException ex) {
        BaseResponseModel baseResponseModel = ResponseMapper.INSTANCE.fieldToModel(ResponseStatusType.FAIL, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponseModel);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<BaseResponseModel> handleValidationException(ValidationException ex) {
        BaseResponseModel baseResponseModel = ResponseMapper.INSTANCE.fieldToModel(ResponseStatusType.FAIL, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponseModel);
    }

}
