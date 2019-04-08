package dev.petedoyle.chatsample.features.chat

import dagger.Module
import dagger.Provides
import dev.petedoyle.chatsample.persistence.AppDatabase
import dev.petedoyle.daggerutil.ActivityScope

@Module
abstract class ChatModule {

    @Module
    companion object {
        @ActivityScope
        @Provides
        @JvmStatic
        fun provideChatRepository(db: AppDatabase): ChatRepository = DefaultChatRepository(db)

        @ActivityScope
        @Provides
        @JvmStatic
        internal fun provideChatViewModel(chatRepository: ChatRepository) = ChatViewModel(chatRepository)
    }
}