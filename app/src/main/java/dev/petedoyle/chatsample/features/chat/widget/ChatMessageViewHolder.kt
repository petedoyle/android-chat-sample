package dev.petedoyle.chatsample.features.chat.widget

import androidx.recyclerview.widget.RecyclerView
import dev.petedoyle.chatsample.databinding.ItemChatMessageBinding
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem

class ChatMessageViewHolder(val binding: ItemChatMessageBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(message: ChatItem.Message) {
        binding.message = message
        binding.executePendingBindings()
    }
}