package com.zhangyan.myssc.frame;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NkProperty {
	public  java.lang.String name() default "";
}
