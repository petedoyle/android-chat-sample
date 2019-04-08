package dev.petedoyle.chatsample.features.chat.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.petedoyle.chatsample.features.chat.json.UserJson

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: Int,
    val name: String,
    val avatarId: String
) {
    companion object {
        fun fromJson(user: UserJson) = User(user.id, user.name, user.avatarId)
    }
}