package dev.petedoyle.chatsample.features.chat

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.squareup.moshi.Moshi
import dev.petedoyle.chatsample.BaseActivity
import dev.petedoyle.chatsample.R
import dev.petedoyle.chatsample.features.chat.domain.ChatResponse
import okio.Okio
import javax.inject.Inject
import javax.inject.Named

class ChatActivity : BaseActivity() {

    @field:[Inject Named("Greeting")]
    lateinit var greeting: String

    @Inject
    lateinit var moshi: Moshi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // A visual proof that dagger is working. See ChatModule.
        findViewById<TextView>(R.id.hello).text = greeting

        // TODO Temporary- just to make sure I can open the file from res/raw and parse it
        resources.openRawResource(resources.getIdentifier("data", "raw", packageName)).use {
            val source = Okio.buffer(Okio.source(it))
            val chatResponse = moshi.adapter(ChatResponse::class.java).fromJson(source)

            chatResponse?.messages?.forEach { message ->
                Log.d(TAG, "Message: $message")
            }

            chatResponse?.users?.forEach { user ->
                Log.d(TAG, "User: $user")
            }
        }
    }

    companion object {
        private const val TAG = "ChatActivity"
    }
}
