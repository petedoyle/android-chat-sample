package dev.petedoyle.chatsample.features.main

import dagger.Module
import dagger.Provides
import dev.petedoyle.daggerutil.ActivityScope
import javax.inject.Named

@Module
abstract class MainModule {

    @Module
    companion object {
        @ActivityScope
        @Provides
        @JvmStatic
        @Named("Greeting")
        fun provideGreeting(): String = "Chat sample"
    }
}