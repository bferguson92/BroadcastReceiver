package com.example.broadcastreceiver.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.broadcastreceiver.R
import kotlinx.android.synthetic.main.fragment_c_layout.*

class FragmentC : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_c_layout, container, false)
    }

    fun getMyAction(action: String){
        text_view_action.text = action
    }
}