package dev.petedoyle.chatsample.features.chat.json

data class ChatResponseJson(
    val messages: List<MessageJson>?,
    val users: List<UserJson>?
)