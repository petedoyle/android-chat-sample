package dev.petedoyle.chatsample.features.chat

import androidx.lifecycle.ViewModel

class ChatViewModel(private val chatRepository: ChatRepository) : ViewModel(), IChatViewModel {
    override fun users() = chatRepository.users()
    override fun displayItems() = chatRepository.getPagedChatItems()
}