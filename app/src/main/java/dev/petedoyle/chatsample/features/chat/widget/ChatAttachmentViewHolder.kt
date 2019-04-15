package dev.petedoyle.chatsample.features.chat.widget

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dev.petedoyle.chatsample.databinding.ItemChatAttachmentBinding
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem

class ChatAttachmentViewHolder(val binding: ItemChatAttachmentBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(attachment: ChatItem.Attachment) {
        binding.attachment = attachment
        binding.executePendingBindings()

        Glide.with(binding.root)
            .load(attachment.thumbnailUrl)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.image)
    }
}