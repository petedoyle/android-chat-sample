package dev.petedoyle.chatsample

import android.app.Application
import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AppModule {

    @Binds
    internal abstract fun bindsContext(application: Application): Context

    @Module
    companion object {
        @Provides
        @JvmStatic
        internal fun provideResources(context: Context) = context.resources

        @Provides
        @JvmStatic
        internal fun provideMoshi() = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}