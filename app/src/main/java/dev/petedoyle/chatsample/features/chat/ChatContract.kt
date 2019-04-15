package dev.petedoyle.chatsample.features.chat

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem
import dev.petedoyle.chatsample.features.chat.persistence.User
import io.reactivex.Single

interface IChatViewModel {
    fun getUser(userId: Int): Single<User>
    fun displayItems(): LiveData<PagedList<ChatItem>>
}