package com.example.broadcastreceiver.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.broadcastreceiver.R
import kotlinx.android.synthetic.main.fragment_b_layout.*

class FragmentB : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b_layout, container, false)
    }

    fun getUsbDevice(usb: String){
        text_view_usb.text = usb
    }

    fun isBatteryCharging(battery: String){
        text_view_battery.text = battery
    }
}