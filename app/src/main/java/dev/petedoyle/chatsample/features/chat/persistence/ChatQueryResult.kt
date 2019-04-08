package dev.petedoyle.chatsample.features.chat.persistence

import androidx.room.Embedded
import androidx.room.Relation
import androidx.room.Transaction

class ChatQueryResult {
    @Embedded
    lateinit var message: ChatItem.Message

    @Relation(
        parentColumn = "id",
        entityColumn = "messageId",
        entity = ChatItem.Attachment::class
    )
    lateinit var attachments: List<ChatItem.Attachment>
}