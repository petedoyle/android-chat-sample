package dev.petedoyle.chatsample

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.petedoyle.chatsample.features.chat.ChatActivity
import dev.petedoyle.chatsample.features.chat.ChatModule
import dev.petedoyle.daggerutil.ActivityScope

@Module
internal abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [ChatModule::class])
    abstract fun chatActivity(): ChatActivity
}