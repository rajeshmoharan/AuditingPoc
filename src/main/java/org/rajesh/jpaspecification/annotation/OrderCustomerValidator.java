package org.rajesh.jpaspecification.annotation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OrderCustomerValidator implements ConstraintValidator<OrderCustomerAnnotaion, Long> {

    @Override
    public void initialize(OrderCustomerAnnotaion constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long s, ConstraintValidatorContext constraintValidatorContext) {
        return s.toString().length() > 10 ? true : false;
    }
}
