package dev.petedoyle.chatsample.features.chat

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem
import dev.petedoyle.chatsample.features.chat.persistence.User

interface ChatRepository {
    fun chatItems(): LiveData<List<ChatItem>>
    fun users(): LiveData<List<User>>
    fun getPagedChatItems(): LiveData<PagedList<ChatItem>>
}