package com.musicbar.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)//运行时有效
@Target(ElementType.METHOD)//只作用在方法上
public @interface LoggerAnnotation {
	public String value() default "";
	public String begin();
	public String end();
}
