package dev.petedoyle.chatsample.features.chat.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ChatDao {
    @Query("SELECT * FROM users")
    fun getUsers(): LiveData<List<User>>

    @Transaction
    @Query("SELECT * FROM messages ORDER BY id ASC LIMIT :pageSize OFFSET :offset")
    fun getMessagesPage(pageSize: Int, offset: Int): Single<List<ChatQueryResult>>

    @Insert
    fun insertUsers(users: List<User>): Completable

    @Insert
    fun insertMessages(messages: List<ChatItem.Message>): Completable

    @Insert
    fun insertAttachments(attachments: List<ChatItem.Attachment>): Completable
}