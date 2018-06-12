package com.example.akihiyo76.twittersample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.twitter.sdk.android.core.*
import kotlinx.android.synthetic.main.activity_main.*
import com.twitter.sdk.android.core.Callback

class MainActivity : AppCompatActivity() {

    companion object {
        private const val CONSUMER_KEY = "olDds7kmubQCIUcuLjlcDb0Cf"
        private const val SECRET_KEY   = "UK7aeU5fONstxgCV9E3dxZtWDysaZQ5oqoalnB84dKjKQQqUpx"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TwitterConfig.Builder(this)
                .twitterAuthConfig(TwitterAuthConfig(CONSUMER_KEY, SECRET_KEY))
                .debug(true)
                .build()
                .let { Twitter.initialize(it) }

        button.isEnabled = true
        button.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                val intent = Intent(this@MainActivity, TimelineActivity::class.java)
                startActivity(intent)
            }

            override fun failure(exception: TwitterException) {
                Toast.makeText(this@MainActivity, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        button.onActivityResult(requestCode, resultCode, data)
    }

}
