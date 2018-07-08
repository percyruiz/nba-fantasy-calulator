package com.percivalruiz.fantasycalculator.injection.components

import android.content.Context
import com.percivalruiz.fantasycalculator.injection.modules.AppModule
import com.percivalruiz.fantasycalculator.injection.qualifier.AppContext
import com.percivalruiz.fantasycalculator.injection.scopes.PerApplication
import dagger.Component

@PerApplication
@Component(modules = arrayOf(AppModule::class))
interface AppComponent : AppComponentProvides

interface AppComponentProvides {
    @AppContext
    fun appContext(): Context
}