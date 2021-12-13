package com.getir.readingisgood.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = PositiveNumberValidator.class)
public @interface Positive {
    String message() default "Girilen input sıfırdan büyük olmalı.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
