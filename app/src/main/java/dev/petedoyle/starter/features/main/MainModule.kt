package dev.petedoyle.starter.features.main

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dev.petedoyle.daggerutil.ActivityScope
import java.math.BigInteger
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class MainModule {

    @Module
    companion object {
        @ActivityScope
        @Provides
        @JvmStatic
        @Named("Greeting")
        fun provideGreeting(): String = "Hello, Starter App!"
    }
}