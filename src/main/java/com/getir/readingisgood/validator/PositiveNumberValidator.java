package com.getir.readingisgood.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
public class PositiveNumberValidator implements ConstraintValidator<Positive, Integer> {
    @Override
    public void initialize(Positive constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return value > 1;
    }
}
