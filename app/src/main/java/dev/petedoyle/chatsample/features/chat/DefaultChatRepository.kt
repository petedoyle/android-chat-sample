package dev.petedoyle.chatsample.features.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import dev.petedoyle.chatsample.features.chat.paging.ChatDataSourceFactory
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem
import dev.petedoyle.chatsample.features.chat.persistence.User
import dev.petedoyle.chatsample.persistence.AppDatabase
import javax.inject.Inject

class DefaultChatRepository @Inject constructor(
    private val db: AppDatabase,
    private val dataSourceFactory: ChatDataSourceFactory
) : ChatRepository {

    private val users = MutableLiveData<List<User>>()
    private val chatItems = MutableLiveData<List<ChatItem>>()

    override fun chatItems(): LiveData<List<ChatItem>> = chatItems
    override fun users(): LiveData<List<User>> = users

    override fun getPagedChatItems(): LiveData<PagedList<ChatItem>> {
        return dataSourceFactory.toLiveData(PAGE_SIZE, 0)
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}