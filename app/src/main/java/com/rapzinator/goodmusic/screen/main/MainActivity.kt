package com.rapzinator.goodmusic.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.RequestManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.inappmessaging.internal.injection.qualifiers.Analytics
import com.google.firebase.ktx.Firebase
import com.rapzinator.goodmusic.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var glide: RequestManager
    private lateinit var analytics: FirebaseAnalytics
    //private val analytics = FirebaseAnalytics.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        analytics = Firebase.analytics
        val parameter = Bundle().apply {
            this.putBoolean("logged_in", true) }
        analytics.logEvent("logged_in", parameter)
        analytics.logEvent(FirebaseAnalytics.Event.LOGIN,null)


        //analytics.logEvent(FirebaseAnalytics.Event.LOGIN, null)
        //analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, null)
    }
}