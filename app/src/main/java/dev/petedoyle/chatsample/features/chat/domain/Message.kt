package dev.petedoyle.chatsample.features.chat.domain

data class Message(
    val id: Int,
    val userId: Int,
    val content: String,
    val attachments: List<Attachment>?
)