package com.zhangyan.myssc.frame;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NoDbProperty {
	public  java.lang.String name() default "";
}
