package dev.petedoyle.chatsample.features.chat

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem
import dev.petedoyle.chatsample.features.chat.persistence.User
import io.reactivex.Completable
import io.reactivex.Single

interface IChatViewModel {
    fun getUser(userId: Int): Single<User>
    fun displayItems(): LiveData<PagedList<ChatItem>>
    fun delete(item: ChatItem?, success: () -> Unit, error: (Throwable) -> Unit)
}

interface IChatRepository {
    fun getUser(userId: Int): Single<User>
    fun getPagedChatItems(): LiveData<PagedList<ChatItem>>
    fun delete(item: ChatItem?): Completable
}