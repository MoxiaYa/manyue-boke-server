package com.example.demo.utils;

import java.lang.annotation.*;
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountLoginToken {
	boolean required() default true;
}
