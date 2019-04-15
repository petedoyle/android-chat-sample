package dev.petedoyle.chatsample.features.chat.widget

import androidx.recyclerview.widget.RecyclerView
import dev.petedoyle.chatsample.databinding.ItemChatAttachmentMeBinding
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem

class ChatAttachmentMeViewHolder(val binding: ItemChatAttachmentMeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(attachment: ChatItem.Attachment) {
        binding.attachment = attachment
        binding.executePendingBindings()
    }
}