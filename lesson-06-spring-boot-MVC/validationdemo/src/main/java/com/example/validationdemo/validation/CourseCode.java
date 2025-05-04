package com.example.validationdemo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {
    //not necessary to make a default value

    //define default course code ('value' and a default value to 'value')
    public String value() default "SUV";

    //define default error message
    public String message() default "must start with SUV";

    //define default groups (idk what it is but it's empty)
    public Class<?>[] groups() default{}; 

    //define default payloads
    //payloads : provide custom details about validation failure (severity level, error code ..etc)
    public Class<? extends Payload>[] payload() default{};

}
