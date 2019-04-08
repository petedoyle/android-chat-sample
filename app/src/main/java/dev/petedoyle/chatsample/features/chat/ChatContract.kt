package dev.petedoyle.chatsample.features.chat

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem
import dev.petedoyle.chatsample.features.chat.persistence.User

interface IChatViewModel {
    fun users(): LiveData<List<User>>
    fun displayItems(): LiveData<PagedList<ChatItem>>
}