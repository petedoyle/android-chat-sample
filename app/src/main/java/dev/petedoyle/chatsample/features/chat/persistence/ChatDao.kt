package dev.petedoyle.chatsample.features.chat.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ChatDao {
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUser(userId: Int): Single<User>

    @Transaction
    @Query("SELECT * FROM messages ORDER BY id ASC LIMIT :pageSize OFFSET :offset")
    fun getMessagesPage(pageSize: Int, offset: Int): List<ChatQueryResult>

    @Insert
    fun insertUsers(users: List<User>)

    @Insert
    fun insertMessages(messages: List<ChatItem.Message>)

    @Insert
    fun insertAttachments(attachments: List<ChatItem.Attachment>)

    @Delete
    fun deleteMessage(message: ChatItem.Message): Completable

    @Delete
    fun deleteAttachment(attachment: ChatItem.Attachment): Completable
}