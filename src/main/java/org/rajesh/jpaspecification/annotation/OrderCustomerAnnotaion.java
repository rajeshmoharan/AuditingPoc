package org.rajesh.jpaspecification.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {OrderCustomerValidator.class})
public @interface OrderCustomerAnnotaion {

    String[] extensions() default {};

    String message() default "{customer id should be more that 10 characters}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
