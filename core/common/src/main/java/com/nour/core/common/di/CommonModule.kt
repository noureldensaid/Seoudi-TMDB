package com.nour.core.common.di

import com.nour.core.common.util.Navigator
import com.nour.core.common.util.NavigatorImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonModule = module {
    singleOf(::NavigatorImpl) { bind<Navigator>() }
}