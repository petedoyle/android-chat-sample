package dev.petedoyle.chatsample.features.chat

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.petedoyle.chatsample.R
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem
import timber.log.Timber
import java.util.UUID

class ChatAdapter : PagedListAdapter<ChatItem, RecyclerView.ViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ITEM_TYPE_MESSAGE -> ChatMessageViewHolder(
                DataBindingUtil.inflate(inflater, R.layout.item_chat_message, parent, false)
            )

            ITEM_TYPE_MESSAGE_ME -> ChatMessageMeViewHolder(
                DataBindingUtil.inflate(inflater, R.layout.item_chat_message_me, parent, false)
            )

            ITEM_TYPE_ATTACHMENT -> ChatAttachmentViewHolder(
                DataBindingUtil.inflate(inflater, R.layout.item_chat_attachment, parent, false)
            )

            ITEM_TYPE_ATTACHMENT_ME -> ChatAttachmentMeViewHolder(
                DataBindingUtil.inflate(inflater, R.layout.item_chat_attachment_me, parent, false)
            )
            else -> throw UnsupportedOperationException("Unhandled view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is ChatMessageViewHolder -> holder.bindTo(item as ChatItem.Message)
            is ChatMessageMeViewHolder -> holder.bindTo(item as ChatItem.Message)
            is ChatAttachmentViewHolder -> holder.bindTo(item as ChatItem.Attachment)
            is ChatAttachmentMeViewHolder -> holder.bindTo(item as ChatItem.Attachment)
        }
    }

    override fun getItemViewType(position: Int): Int = when (val item = getItem(position)) {
        is ChatItem.Message -> when (item.userId == USER_ID_ME) {
            true -> ITEM_TYPE_MESSAGE_ME
            else -> ITEM_TYPE_MESSAGE
        }
        is ChatItem.Attachment -> when (item.userId == USER_ID_ME) {
            true -> ITEM_TYPE_ATTACHMENT_ME
            else -> ITEM_TYPE_ATTACHMENT
        }

        else -> throw UnsupportedOperationException("Unhandled item type: $item")
    }

    override fun getItemId(position: Int) =
        when (val item = getItem(position)) {
            is ChatItem.Message -> item.id.toLong()
            is ChatItem.Attachment -> UUID.fromString(item.id).leastSignificantBits
            else -> throw UnsupportedOperationException("Unhandled item type: $item")
        }

    override fun submitList(pagedList: PagedList<ChatItem>?) {
        Timber.d("submitList: ${pagedList?.size}")
        super.submitList(pagedList)

    }

    companion object {
        private const val USER_ID_ME = 1

        private const val ITEM_TYPE_MESSAGE = 1
        private const val ITEM_TYPE_MESSAGE_ME = 2
        private const val ITEM_TYPE_ATTACHMENT = 3
        private const val ITEM_TYPE_ATTACHMENT_ME = 4

        private val diffCallback = object : DiffUtil.ItemCallback<ChatItem>() {
            override fun areItemsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals") // == always called on data class subtypes
            override fun areContentsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}