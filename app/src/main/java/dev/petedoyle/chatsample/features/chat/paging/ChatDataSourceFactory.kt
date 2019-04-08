package dev.petedoyle.chatsample.features.chat.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem
import dev.petedoyle.chatsample.persistence.AppDatabase

class ChatDataSourceFactory(
    private val db: AppDatabase
) : DataSource.Factory<Int, ChatItem>() {
    private val sourceLiveData = MutableLiveData<ChatPagedKeyDataSource>()
    private lateinit var latestSource: ChatPagedKeyDataSource

    override fun create(): DataSource<Int, ChatItem> {
        latestSource = ChatPagedKeyDataSource(db)
        sourceLiveData.postValue(latestSource)
        return latestSource
    }
}