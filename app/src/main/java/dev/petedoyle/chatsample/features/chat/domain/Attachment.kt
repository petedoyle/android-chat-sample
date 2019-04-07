package dev.petedoyle.chatsample.features.chat.domain

data class Attachment(
    val id: String,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)