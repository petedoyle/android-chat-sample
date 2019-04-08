package dev.petedoyle.chatsample

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.petedoyle.chatsample.di.ViewModelModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class, // Required for AndroidInjector, etc
        AppModule::class,
        ViewModelModule::class,
        ActivityBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<ChatApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: ChatApplication): AppComponent.Builder

        fun build(): AppComponent
    }
}