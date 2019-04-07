package dev.petedoyle.chatsample.features.chat.domain

data class ChatResponse(
    val messages: List<Message>?,
    val users: List<User>?
)