package dev.petedoyle.chatsample.persistence

import android.annotation.SuppressLint
import android.app.Application
import com.squareup.moshi.Moshi
import dev.petedoyle.chatsample.features.chat.json.ChatResponseJson
import dev.petedoyle.chatsample.features.chat.persistence.ChatItem
import dev.petedoyle.chatsample.features.chat.persistence.User
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okio.Okio
import timber.log.Timber
import javax.inject.Inject

/**
 * Imports data from data.json.
 */
class DataImporter @Inject constructor(
    private val moshi: Moshi,
    private val db: AppDatabase
) {

    @SuppressLint("CheckResult")
    fun import(application: Application) {
        // Import data on a background thread
        Completable
            .create { emitter ->
                application.resources.openRawResource(
                    application.resources.getIdentifier("data", "raw", application.packageName)
                ).use { inputStream ->
                    val source = Okio.buffer(Okio.source(inputStream))
                    val adapter = moshi.adapter(ChatResponseJson::class.java)
                    val json = adapter.fromJson(source)

                    parseChatResponse(json)
                }

                emitter.onComplete()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d("Imported data from json")
            }, { throwable ->
                Timber.e(throwable, "Failed to import data from json")
            })
    }

    private fun parseChatResponse(json: ChatResponseJson?) {
        if (json == null) {
            return
        }

        val messages = mutableListOf<ChatItem.Message>()
        val attachments = mutableListOf<ChatItem.Attachment>()
        val users = mutableListOf<User>()

        // Add the message, followed by any attachments
        json.messages?.forEach { message ->
            messages += ChatItem.Message.fromJson(message)

            message.attachments?.forEach { attachment ->
                attachments += ChatItem.Attachment.fromJson(attachment, message.id, message.userId)
            }
        }

        json.users?.forEach {
            users.add(User.fromJson(it))
        }

        db.runInTransaction {
            db.chatDao().insertMessages(messages)
            db.chatDao().insertAttachments(attachments)
            db.chatDao().insertUsers(users)
        }
    }
}