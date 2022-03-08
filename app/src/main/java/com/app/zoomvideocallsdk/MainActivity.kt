package com.app.zoomvideocallsdk

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import us.zoom.sdk.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeZoomSdk(this)
        joinButton.setOnClickListener {
        val username = name.text?.trim().toString()
        val meetingid = meetingid.text?.trim().toString()
        val password = pass.text?.trim().toString()
            if (username.length>0 && meetingid.length>0 && password.length>0){
                startMeeting(username,password,meetingid,this)
            }else{
                Toast.makeText(this,"Invalid Details", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun startMeeting(name: String, password: String, meetingid: String, context: Context) {
        val meetingService:MeetingService = ZoomSDK.getInstance().meetingService
        val joinMeetingOptions:JoinMeetingOptions = JoinMeetingOptions()
        val joinMeetingParams :JoinMeetingParams = JoinMeetingParams()
        joinMeetingParams.displayName = name
        joinMeetingParams.password = password
        joinMeetingParams.meetingNo = meetingid
        meetingService.joinMeetingWithParams(context,joinMeetingParams,joinMeetingOptions)
    }

    private fun initializeZoomSdk(context: Context) {
        val sdk = ZoomSDK.getInstance();
        val params = ZoomSDKInitParams();
        params.appKey = "sJBA46PwL10RX2sXCClR0xrOGleadGeypoNf"
        params.appSecret = "Ll818HAKnupAimOmtnIv8tNE4CFkOhaOo5Gz"
        params.domain = "zoom.us"
        params.enableLog = false

        val listener = object : ZoomSDKInitializeListener {
            override fun onZoomSDKInitializeResult(p0: Int, p1: Int) {

            }

            override fun onZoomAuthIdentityExpired() {

            }
        }
        sdk.initialize(context,listener,params)
    }
}

