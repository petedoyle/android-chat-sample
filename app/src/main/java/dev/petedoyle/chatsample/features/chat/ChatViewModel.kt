package dev.petedoyle.chatsample.features.chat

import androidx.lifecycle.ViewModel
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel(), IChatViewModel {
    private val disposables = CompositeDisposable()

    override fun getUser(userId: Int) = chatRepository.getUser(userId)
    override fun displayItems() = chatRepository.getPagedChatItems()

    override fun delete(item: ChatItem?, success: () -> Unit, error: (Throwable) -> Unit) {
        disposables.add(
            chatRepository.delete(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    success()
                }, { throwable ->
                    error(throwable)
                })
        )
    }

    override fun onCleared() = disposables.clear()
}