package com.cyw.origin.frame.galaxy.aop;

import java.lang.annotation.*;

/**
 * Created by zhiwenzhu on 18/4/28.
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RequireOperationLog {
}
