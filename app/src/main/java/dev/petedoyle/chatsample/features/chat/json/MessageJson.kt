package dev.petedoyle.chatsample.features.chat.json

data class MessageJson(
    val id: Int,
    val userId: Int,
    val content: String,
    val attachments: List<AttachmentJson>?
)