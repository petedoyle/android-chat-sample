package dev.petedoyle.chatsample.features.chat

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.petedoyle.chatsample.BaseActivity
import dev.petedoyle.chatsample.R
import dev.petedoyle.chatsample.databinding.ActivityChatBinding
import javax.inject.Inject

class ChatActivity : BaseActivity() {
    @Inject
    lateinit var viewModel: ChatViewModel

    private lateinit var binding: ActivityChatBinding

    private lateinit var adapter: ChatAdapter

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
    }
}