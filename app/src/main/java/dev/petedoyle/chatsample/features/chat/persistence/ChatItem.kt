package dev.petedoyle.chatsample.features.chat.persistence

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import dev.petedoyle.chatsample.features.chat.json.AttachmentJson
import dev.petedoyle.chatsample.features.chat.json.MessageJson

/**
 * Data models for each visible row in the RecyclerView.
 */
sealed class ChatItem {

    @Entity(tableName = "messages")
    data class Message(
        @PrimaryKey val id: Int,
        val userId: Int,
        val content: String
    ) : ChatItem() {
        companion object {
            fun fromJson(message: MessageJson) = Message(message.id, message.userId, message.content)
        }
    }

    @Entity(
        tableName = "attachments",
        foreignKeys = [
            ForeignKey(
                entity = Message::class,
                parentColumns = ["id"],
                childColumns = ["messageId"],
                onDelete = ForeignKey.CASCADE
            )
        ],
        indices = [
            Index("messageId")
        ]
    )
    data class Attachment(
        @PrimaryKey val id: String,
        var messageId: Int,
        var userId: Int,
        val title: String,
        val url: String,
        val thumbnailUrl: String
    ) : ChatItem() {
        companion object {
            fun fromJson(attachment: AttachmentJson, messageId: Int, userId: Int) = Attachment(
                id = attachment.id,
                messageId = messageId,
                userId = userId,
                title = attachment.title,
                url = attachment.url,
                thumbnailUrl = attachment.thumbnailUrl
            )
        }
    }
}