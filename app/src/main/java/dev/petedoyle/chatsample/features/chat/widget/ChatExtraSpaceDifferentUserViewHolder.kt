package dev.petedoyle.chatsample.features.chat.widget

import androidx.recyclerview.widget.RecyclerView
import dev.petedoyle.chatsample.databinding.ItemChatExtraSpaceDifferentUserBinding
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem

class ChatExtraSpaceDifferentUserViewHolder(
    val binding: ItemChatExtraSpaceDifferentUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(data: ChatItem.ExtraSpaceDifferentUser) {
        // nothing to do
    }
}