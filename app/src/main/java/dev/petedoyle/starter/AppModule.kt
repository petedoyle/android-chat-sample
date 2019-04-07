package dev.petedoyle.starter

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.math.BigInteger
import javax.inject.Named
import javax.inject.Singleton
import kotlin.random.Random

@Module
abstract class AppModule {

    @Binds
    internal abstract fun bindsContext(application: Application): Context

    @Module
    companion object {
        @Provides
        @JvmStatic
        internal fun provideResources(context: Context) = context.resources
    }
}