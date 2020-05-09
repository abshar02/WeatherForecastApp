package com.abshar.weatherapp.dagger.components

import com.abshar.weatherapp.dagger.modules.OpenWeatherAPIModule
import com.abshar.weatherapp.dagger.modules.SharedPreferencesModule
import com.abshar.weatherapp.ui.base.BaseView
import com.abshar.weatherapp.ui.main.MainPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(OpenWeatherAPIModule::class), (SharedPreferencesModule::class)])
interface PresenterComponent {
    fun inject(presenter: MainPresenter)


    @Component.Builder
    interface Builder {
        fun build(): PresenterComponent

        fun apiModule(module: OpenWeatherAPIModule): Builder

        fun prefsModule(module: SharedPreferencesModule): Builder

        @BindsInstance
        fun baseView(view: BaseView): Builder
    }
}