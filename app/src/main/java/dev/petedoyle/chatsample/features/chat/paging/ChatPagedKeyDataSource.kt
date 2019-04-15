package dev.petedoyle.chatsample.features.chat.paging

import androidx.paging.PageKeyedDataSource
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem
import dev.petedoyle.chatsample.features.chat.persistence.ChatQueryResult
import dev.petedoyle.chatsample.persistence.AppDatabase
import javax.inject.Inject

class ChatPagedKeyDataSource @Inject constructor(
    val db: AppDatabase
) : PageKeyedDataSource<Int, ChatItem>() {

    private var previousUserId = -1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ChatItem>) {
        val currentPage = 0
        val previousPageKey = null
        val nextPageKey = currentPage + 1

        val items = db.chatDao().getMessagesPage(params.requestedLoadSize, offset = 0)
        callback.onResult(parse(items), previousPageKey, nextPageKey)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ChatItem>) {
        val page = params.key

        val items = db.chatDao().getMessagesPage(params.requestedLoadSize, offset = page * PAGE_SIZE)
        val adjacentPageKey = if (items.size < params.requestedLoadSize) null else page + 1
        callback.onResult(parse(items), adjacentPageKey)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ChatItem>) {
        TODO("Not implemented")
    }

    private fun parse(queryResults: List<ChatQueryResult>): MutableList<ChatItem> {
        val displayItems = mutableListOf<ChatItem>()

        queryResults.forEach { item ->
            // As seen in mocks, there is less vertical space between messages from the same user
            // Here we add extra space when the user changes
            if (previousUserId == -1) {
                previousUserId = item.message.userId
            }
            if (previousUserId != item.message.userId) {
                displayItems.add(ChatItem.ExtraSpaceDifferentUser())
            }

            displayItems.add(item.message)
            displayItems.addAll(item.attachments)
        }

        return displayItems
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}