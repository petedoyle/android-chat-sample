package dev.petedoyle.chatsample.features.chat

import androidx.recyclerview.widget.RecyclerView
import dev.petedoyle.chatsample.databinding.ItemChatMessageMeBinding
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem

class ChatMessageMeViewHolder(val binding: ItemChatMessageMeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(message: ChatItem.Message) {
        binding.message = message
        binding.executePendingBindings()
    }
}