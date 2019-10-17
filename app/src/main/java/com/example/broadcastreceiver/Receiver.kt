package com.example.broadcastreceiver

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbManager
import android.os.AsyncTask
import android.os.BatteryManager
import android.util.Log
import com.example.broadcastreceiver.model.Received
import org.greenrobot.eventbus.EventBus
import java.lang.RuntimeException

class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Task(goAsync(), intent).execute()

    }

    inner class Task(private val pendingResult: PendingResult, val intent: Intent?) :
        AsyncTask<String, Int, Received?>() {

        override fun doInBackground(vararg params: String?): Received? {

            val received = Received(null, null, null, null, null)

            val action = intent?.action

            if (action.equals("android.intent.action.AIRPLANE_MODE")) {
                received.airplaneMode = intent?.extras?.get("state").toString()
            } else if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                when (intent?.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)) {
                    BluetoothAdapter.STATE_OFF -> received.bluetooth = "OFF"
                    BluetoothAdapter.STATE_ON -> received.bluetooth = "ON"
                }
            } else if (action.equals(UsbManager.ACTION_USB_DEVICE_DETACHED)) {
                if (intent?.extras?.get("state") == true) {
                    received.usbDetached = "false"
                } else {
                    received.usbDetached = "true"
                }
            } else if (action.equals(BatteryManager.ACTION_CHARGING)) {
                if(intent?.extras?.get("state") == true){
                    received.batteryChraging = "true"
                } else {
                    received.batteryChraging = "false"
                }
            }

            return received
        }

        override fun onPostExecute(result: Received?) {
            super.onPostExecute(result)
            EventBus.getDefault().post(result)
            pendingResult.finish()
        }
    }
}