/*
 * Copyright (c) 2018. Create and edit by ChangedenChan.
 */

package com.chansos.libs.rxkotlin.anno

/**
 * 自动装配Presenter
 * */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class AutowirePresent(val path: String)
