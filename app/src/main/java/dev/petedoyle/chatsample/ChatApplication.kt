package dev.petedoyle.chatsample

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import dev.petedoyle.chatsample.persistence.DataImporter
import timber.log.Timber
import javax.inject.Inject

class ChatApplication : DaggerApplication() {

    @Inject
    internal lateinit var loggingTree: Timber.Tree

    @Inject
    lateinit var dataImporter: DataImporter

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(loggingTree)
    }

    fun onDatabaseCreated() = dataImporter.import(this)
}