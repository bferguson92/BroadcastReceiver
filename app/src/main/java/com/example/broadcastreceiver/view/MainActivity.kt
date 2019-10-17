package com.example.broadcastreceiver.view

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcastreceiver.Fragment.FragmentA
import com.example.broadcastreceiver.Fragment.FragmentB
import com.example.broadcastreceiver.Fragment.FragmentC
import com.example.broadcastreceiver.R
import com.example.broadcastreceiver.Receiver
import com.example.broadcastreceiver.model.Received
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_a_layout.*
import kotlinx.android.synthetic.main.fragment_c_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    val receiver: Receiver = Receiver()
    val fragmentA = FragmentA()
    val fragmentB = FragmentB()
    val fragmentC = FragmentC()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        supportFragmentManager.beginTransaction().replace(R.id.frame_layout_1, fragmentA).commit()
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout_2, fragmentB).commit()
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout_3, fragmentC).commit()

        EventBus.getDefault().register(this)
        var filter = IntentFilter("android.intent.action.AIRPLANE_MODE")
        filter.addAction("android.bluetooth.adapter.action.STATE_CHANGED")
        filter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED")
        filter.addAction("android.os.action.CHARGING")
        filter.addAction("com.example.broadcastreceiver.action")

        registerReceiver(receiver, filter)
        button_send_broadcast.setOnClickListener{
            Intent().also { intent ->
                intent.action = ("com.example.broadcastreceiver.action")
                intent.putExtra("data", "Notice me senpai!")
                sendBroadcast(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        unregisterReceiver(receiver)
    }

    @Subscribe
    fun getMessage(msg: Received?){
        runOnUiThread {
            if(msg?.airplaneMode != null)
                fragmentA.getAirplaneMode(msg.airplaneMode.toString())
            else if(msg?.bluetooth != null)
                fragmentA.getBluetoothState(msg.bluetooth.toString())
            else if(msg?.usbDetached != null)
                fragmentB.getUsbDevice(msg.usbDetached.toString())
            else if(msg?.batteryChraging != null)
                fragmentB.isBatteryCharging(msg.batteryChraging.toString())
            else if(msg?.myAction != null)
                fragmentC.getMyAction(msg?.myAction.toString())
        }
    }

}
