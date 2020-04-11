package com.example.newlocation02.myAnnotation

import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.FUNCTION,AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class Cat {

}