package com.percivalruiz.fantasycalculator.injection.components

import android.content.Context
import android.support.v4.app.FragmentManager
import com.percivalruiz.fantasycalculator.injection.modules.ActivityModule
import com.percivalruiz.fantasycalculator.injection.qualifier.ActivityContext
import com.percivalruiz.fantasycalculator.injection.scopes.PerActivity
import com.percivalruiz.fantasycalculator.ui.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent : ActivityComponentProvides {
    // create inject methods for your Activities here

    fun inject(activity: MainActivity)

}

interface ActivityComponentProvides : AppComponentProvides {
    @ActivityContext
    fun activityContext(): Context
}