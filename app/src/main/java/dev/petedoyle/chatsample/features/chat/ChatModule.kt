package dev.petedoyle.chatsample.features.chat

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dev.petedoyle.chatsample.di.ViewModelKey
import dev.petedoyle.chatsample.features.chat.paging.ChatDataSourceFactory
import dev.petedoyle.chatsample.persistence.AppDatabase
import dev.petedoyle.daggerutil.ActivityScope

@Module
abstract class ChatModule {

    @Suppress("unused")
    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    abstract fun bindChatViewModel(chatViewModel: ChatViewModel): ViewModel

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