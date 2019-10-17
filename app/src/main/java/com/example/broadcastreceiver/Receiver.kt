package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import org.greenrobot.eventbus.EventBus

class Receiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Task(goAsync(), intent).execute()

    }

    inner class Task(private val pendingResult: PendingResult, val intent: Intent?): AsyncTask<String, Int, String>(){

        override fun doInBackground(vararg params: String?): String {
            return intent?.extras?.get("state").toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            EventBus.getDefault().post(result)
            pendingResult.finish()
        }
    }
}