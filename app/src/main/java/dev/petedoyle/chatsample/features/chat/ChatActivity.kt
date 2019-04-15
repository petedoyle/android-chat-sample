package dev.petedoyle.chatsample.features.chat

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.petedoyle.chatsample.BaseActivity
import dev.petedoyle.chatsample.R
import dev.petedoyle.chatsample.databinding.ActivityChatBinding
import dev.petedoyle.chatsample.features.chat.widget.ChatAttachmentMeViewHolder
import dev.petedoyle.chatsample.features.chat.widget.ChatAttachmentViewHolder
import dev.petedoyle.chatsample.features.chat.widget.ChatMessageMeViewHolder
import dev.petedoyle.chatsample.features.chat.widget.ChatMessageViewHolder
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class ChatActivity : BaseActivity() {
    @Inject
    lateinit var viewModel: ChatViewModel

    private lateinit var binding: ActivityChatBinding
    private lateinit var adapter: ChatAdapter

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = ChatAdapter(viewModel).apply {
            setHasStableIds(true)
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.displayItems().observe(this, Observer {
            adapter.submitList(it)
        })

        initSwipeToDelete()
    }

    private fun initSwipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
                makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

            override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) = delete(viewHolder)

            private fun delete(viewHolder: RecyclerView.ViewHolder) {
                val success = {}
                val error: (Throwable) -> Unit = { Timber.w(it, "Failed to delete item") }

                when (viewHolder) {
                    is ChatMessageViewHolder -> viewModel.delete(viewHolder.binding.message!!, success, error)
                    is ChatMessageMeViewHolder -> viewModel.delete(viewHolder.binding.message!!, success, error)
                    is ChatAttachmentViewHolder -> viewModel.delete(viewHolder.binding.attachment!!, success, error)
                    is ChatAttachmentMeViewHolder -> viewModel.delete(viewHolder.binding.attachment!!, success, error)
                }
            }
        }).attachToRecyclerView(binding.recyclerView)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}