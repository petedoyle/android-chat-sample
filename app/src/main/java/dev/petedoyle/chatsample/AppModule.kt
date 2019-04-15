package dev.petedoyle.chatsample

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dev.petedoyle.chatsample.persistence.AppDatabase
import timber.log.Timber

@Module
abstract class AppModule {

    @Binds
    internal abstract fun bindsContext(application: Application): Context

    @Module
    companion object {
        @Provides
        @JvmStatic
        internal fun provideApplication(application: Application) = application

        @Provides
        @JvmStatic
        internal fun provideChatApplication(application: Application) = application

        @Provides
        @JvmStatic
        internal fun provideResources(context: Context) = context.resources

        @Provides
        @JvmStatic
        internal fun provideMoshi() = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        @Provides
        @JvmStatic
        internal fun provideTimberTree(): Timber.Tree = Timber.DebugTree()

        @Provides
        @JvmStatic
        internal fun provideAppDatabase(application: ChatApplication): AppDatabase =
            Room.databaseBuilder(application, AppDatabase::class.java, "chat")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        application.onDatabaseCreated()
                    }
                })
                .build()
    }
}