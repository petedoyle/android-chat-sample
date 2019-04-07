package dev.petedoyle.starter

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.petedoyle.daggerutil.ActivityScope
import dev.petedoyle.starter.features.main.MainActivity
import dev.petedoyle.starter.features.main.MainModule

@Module
internal abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity
}