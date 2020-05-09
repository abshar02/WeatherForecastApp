package com.abshar.weatherapp.ui.base

import com.abshar.weatherapp.dagger.components.DaggerPresenterComponent
import com.abshar.weatherapp.dagger.components.PresenterComponent
import com.abshar.weatherapp.dagger.modules.OpenWeatherAPIModule
import com.abshar.weatherapp.dagger.modules.SharedPreferencesModule
import com.abshar.weatherapp.ui.main.MainPresenter

/**
 * Base presenter any presenter of the application must extend. It provides initial injections and
 * required methods.
 * @param V the type of the View the presenter is based on
 * @property view the view the presenter is based on
 * @property component The component used to inject required dependencies
 * @constructor Injects the required dependencies
 */
abstract class BasePresenter<out V : BaseView>(protected val view: V) {
    private val component: PresenterComponent = DaggerPresenterComponent
            .builder()
            .baseView(view)
            .apiModule(OpenWeatherAPIModule)
            .prefsModule(SharedPreferencesModule(view.getContext()))
            .build()


    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is MainPresenter -> component.inject(this)

        }
    }
}