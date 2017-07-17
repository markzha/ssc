package com.zhangyan.myssc.frame;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NkPropertyForUpdate {
	public  java.lang.String name() default "";
}
