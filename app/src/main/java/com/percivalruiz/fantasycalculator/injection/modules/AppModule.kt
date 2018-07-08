package com.percivalruiz.fantasycalculator.injection.modules

import android.app.Application
import android.content.Context
import com.percivalruiz.fantasycalculator.injection.qualifier.AppContext
import com.percivalruiz.fantasycalculator.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: Application) {

  @Provides
  @PerApplication
  @AppContext
  internal fun provideAppContext(): Context = app

}