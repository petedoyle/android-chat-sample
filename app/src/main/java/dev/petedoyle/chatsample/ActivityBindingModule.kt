package dev.petedoyle.chatsample

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.petedoyle.daggerutil.ActivityScope
import dev.petedoyle.chatsample.features.main.MainActivity
import dev.petedoyle.chatsample.features.main.MainModule

@Module
internal abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity
}