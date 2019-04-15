package dev.petedoyle.chatsample.features.chat.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem
import dev.petedoyle.chatsample.persistence.AppDatabase
import javax.inject.Inject

class ChatDataSourceFactory @Inject constructor(
    private val db: AppDatabase
) : DataSource.Factory<Int, ChatItem>() {

    private val sourceLiveData = MutableLiveData<ChatPagedKeyDataSource>()

    var latestSource: ChatPagedKeyDataSource = ChatPagedKeyDataSource(db).apply {
        sourceLiveData.postValue(this)
    }

    override fun create(): DataSource<Int, ChatItem> {
        latestSource = ChatPagedKeyDataSource(db)
        sourceLiveData.postValue(latestSource)
        return latestSource
    }
}
