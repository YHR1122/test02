package com.example.akihiyo76.twittersample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.models.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TimelineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)

        // http://westplain.sakuraweb.com/translate/twitter/Documentation/Fabric/TwitterKit/Android/AccessTwittersRESTAPI.cgi
        val service = TwitterCore.getInstance().apiClient.statusesService
        val call = service.homeTimeline(100, null, null, false, false, false, false)
        call.enqueue(object : Callback<List<Tweet>> {
            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                response.body().forEach {
                    Log.d("", it.text)
                }
            }

            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                Log.e("", t.localizedMessage)
            }
        })

    }

}
