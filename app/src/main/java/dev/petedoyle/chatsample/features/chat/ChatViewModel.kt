package dev.petedoyle.chatsample.features.chat

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel(), IChatViewModel {
    override fun users() = chatRepository.users()
    override fun displayItems() = chatRepository.getPagedChatItems()
}