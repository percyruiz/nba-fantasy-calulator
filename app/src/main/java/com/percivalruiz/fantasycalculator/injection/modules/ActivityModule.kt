package com.percivalruiz.fantasycalculator.injection.modules

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.percivalruiz.fantasycalculator.injection.qualifier.ActivityContext
import com.percivalruiz.fantasycalculator.injection.scopes.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @PerActivity
    @ActivityContext
    internal fun provideActivityContext(): Context = activity

}
