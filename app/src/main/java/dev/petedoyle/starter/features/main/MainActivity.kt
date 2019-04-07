package dev.petedoyle.starter.features.main

import android.os.Bundle
import android.widget.TextView
import dev.petedoyle.starter.BaseActivity
import dev.petedoyle.starter.R
import javax.inject.Inject
import javax.inject.Named

class MainActivity : BaseActivity() {

    @field:[Inject Named("Greeting")]
    lateinit var greeting: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // A visual proof that dagger is working. See MainModule.
        findViewById<TextView>(R.id.hello).text = greeting
    }
}
