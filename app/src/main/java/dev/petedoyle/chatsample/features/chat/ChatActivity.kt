package dev.petedoyle.chatsample.features.chat

import android.os.Bundle
import android.widget.TextView
import dev.petedoyle.chatsample.BaseActivity
import dev.petedoyle.chatsample.R
import javax.inject.Inject
import javax.inject.Named

class ChatActivity : BaseActivity() {

    @field:[Inject Named("Greeting")]
    lateinit var greeting: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // A visual proof that dagger is working. See ChatModule.
        findViewById<TextView>(R.id.hello).text = greeting
    }
}
