package com.sefiso.matlatlefuneralpalourapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sefiso.matlatlefuneralpalourapplication.databinding.FragmentMessagesBinding

class MessagesFragment : Fragment() {
    private lateinit var binding: FragmentMessagesBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentMessagesBinding.inflate(layoutInflater)
        return binding.root
    }
}