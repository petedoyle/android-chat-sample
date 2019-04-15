package dev.petedoyle.chatsample.features.chat.widget

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import dev.petedoyle.chatsample.databinding.ItemChatMessageBinding
import dev.petedoyle.chatsample.features.chat.ChatViewModel
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ChatMessageViewHolder(val binding: ItemChatMessageBinding) : RecyclerView.ViewHolder(binding.root) {
    private val disposables = CompositeDisposable()

    fun bindTo(message: ChatItem.Message, viewModel: ChatViewModel) {
        // Clear any data and ongoing loads from reusing a ViewHolder
        disposables.clear()
        binding.username.text = null
        binding.image.setImageDrawable(null)

        // Set info
        binding.message = message
        binding.executePendingBindings()

        // Load user data, including the profile photo
        disposables.add(
            viewModel.getUser(message.userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                    Glide.with(binding.root)
                        .load(user.avatarId)
                        .apply(RequestOptions.circleCropTransform())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(binding.image)

                    binding.username.text = user.name
                }, { throwable ->
                    Timber.w(throwable, "Failed to user for message $message")
                })
        )
    }
}