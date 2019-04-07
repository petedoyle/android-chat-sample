package dev.petedoyle.starter

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class StarterApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
}