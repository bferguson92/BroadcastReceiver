package com.example.broadcastreceiver.view

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcastreceiver.Fragment.FragmentA
import com.example.broadcastreceiver.R
import com.example.broadcastreceiver.Receiver
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    val receiver: Receiver = Receiver()
    val fragmentA = FragmentA()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.frame_layout_1, fragmentA).commit()

        EventBus.getDefault().register(this)
        registerReceiver(receiver, IntentFilter("android.intent.action.AIRPLANE_MODE"))

        button_send_broadcast.setOnClickListener{
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        unregisterReceiver(receiver)
    }

    @Subscribe
    fun getMessage(msg: String){s
        runOnUiThread {
            fragmentA.getAirplaneMode(msg)
        }
    }
}
