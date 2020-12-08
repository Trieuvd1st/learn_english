package com.example.learnenglish.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.learnenglish.R
import com.example.learnenglish.activity.CommTestViewModel

class EnSenToMicFragment : Fragment() {

    private lateinit var viewModelCommTest: CommTestViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_en_sen_to_mic, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelCommTest = ViewModelProviders.of(activity!!).get(CommTestViewModel::class.java).apply {

        }
    }
}