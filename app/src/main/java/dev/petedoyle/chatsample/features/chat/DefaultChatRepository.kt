package dev.petedoyle.chatsample.features.chat

import androidx.paging.PagedList
import androidx.paging.toLiveData
import dev.petedoyle.chatsample.features.chat.paging.ChatDataSourceFactory
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem
import dev.petedoyle.chatsample.features.chat.persistence.User
import dev.petedoyle.chatsample.persistence.AppDatabase
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class DefaultChatRepository @Inject constructor(
    private val db: AppDatabase,
    private val dataSourceFactory: ChatDataSourceFactory
) : ChatRepository {
    override fun getUser(userId: Int): Single<User> = db.chatDao().getUser(userId)
    override fun getPagedChatItems() = dataSourceFactory.toLiveData(
        PagedList.Config.Builder()
            .setPageSize(20)
            .build()
    )

    override fun delete(item: ChatItem?): Completable {
        if (item == null) {
            return Completable.complete()
        }

        val completable = when (item) {
            is ChatItem.Message -> db.chatDao().deleteMessage(item)
            is ChatItem.Attachment -> db.chatDao().deleteAttachment(item)
            is ChatItem.ExtraSpaceDifferentUser -> Completable.complete()
        }

        return completable.doOnComplete {
            dataSourceFactory.latestSource.invalidate()
        }
    }
}