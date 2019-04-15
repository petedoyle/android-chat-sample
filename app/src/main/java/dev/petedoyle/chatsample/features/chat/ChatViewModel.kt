package dev.petedoyle.chatsample.features.chat

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel(), IChatViewModel {
    override fun getUser(userId: Int) = chatRepository.getUser(userId)
    override fun displayItems() = chatRepository.getPagedChatItems()
}