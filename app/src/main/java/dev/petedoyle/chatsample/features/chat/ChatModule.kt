package dev.petedoyle.chatsample.features.chat

import dagger.Module
import dagger.Provides
import dev.petedoyle.daggerutil.ActivityScope
import javax.inject.Named

@Module
abstract class ChatModule {

    @Module
    companion object {
        @ActivityScope
        @Provides
        @JvmStatic
        @Named("Greeting")
        fun provideGreeting(): String = "Chat sample"
    }
}