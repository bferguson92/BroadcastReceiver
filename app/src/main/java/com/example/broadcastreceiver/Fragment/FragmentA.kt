package com.example.broadcastreceiver.Fragment

import android.content.IntentFilter
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.broadcastreceiver.R
import com.example.broadcastreceiver.Receiver
import kotlinx.android.synthetic.main.fragment_a_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class FragmentA: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a_layout, container, false)
    }


    fun getAirplaneMode(isAirplaneModeOn: String){
        text_view.text = isAirplaneModeOn
    }

    fun getBluetoothState(isBluetoothOn: String){
        text_view_bluetooth.text = isBluetoothOn

    }
}