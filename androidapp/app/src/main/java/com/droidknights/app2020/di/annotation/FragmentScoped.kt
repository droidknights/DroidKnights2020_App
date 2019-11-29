package com.droidknights.app2020.di.annotation

import javax.inject.Scope

/**
 * Created by jiyoung on 29/11/2019
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class FragmentScoped