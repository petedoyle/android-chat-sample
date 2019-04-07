package dev.petedoyle.chatsample

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class ChatApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
}