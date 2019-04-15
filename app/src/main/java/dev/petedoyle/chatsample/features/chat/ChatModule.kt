package dev.petedoyle.chatsample.features.chat

import dagger.Module
import dagger.Provides
import dev.petedoyle.chatsample.features.chat.paging.ChatDataSourceFactory
import dev.petedoyle.chatsample.persistence.AppDatabase
import dev.petedoyle.daggerutil.ActivityScope

@Module
abstract class ChatModule {

    @Module
    companion object {
        @ActivityScope
        @Provides
        @JvmStatic
        fun provideChatDataSourceFactory(db: AppDatabase): ChatDataSourceFactory = ChatDataSourceFactory(db)

        @ActivityScope
        @Provides
        @JvmStatic
        fun provideChatRepository(db: AppDatabase, factory: ChatDataSourceFactory): ChatRepository =
            DefaultChatRepository(db, factory)
    }
}