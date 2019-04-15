package dev.petedoyle.chatsample.features.chat

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import dev.petedoyle.chatsample.features.chat.paging.ChatDataSourceFactory
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem
import dev.petedoyle.chatsample.features.chat.persistence.User
import dev.petedoyle.chatsample.persistence.AppDatabase
import io.reactivex.Single
import javax.inject.Inject

class DefaultChatRepository @Inject constructor(
    private val db: AppDatabase,
    private val dataSourceFactory: ChatDataSourceFactory
) : ChatRepository {
    override fun getUser(userId: Int): Single<User> = db.chatDao().getUser(userId)
    override fun getPagedChatItems(): LiveData<PagedList<ChatItem>> {
        return dataSourceFactory.toLiveData(PAGE_SIZE, 0)
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}