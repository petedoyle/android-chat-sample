package dev.petedoyle.chatsample.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.petedoyle.chatsample.features.chat.persistence.ChatDao
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem
import dev.petedoyle.chatsample.features.chat.persistence.User

@Database(
    entities = [
        User::class,
        ChatItem.Message::class,
        ChatItem.Attachment::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
}