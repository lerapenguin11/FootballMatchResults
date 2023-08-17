package com.example.footballmatchresults.presentation.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.footballmatchresults.R

class ThirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_thirst, container, false)
        val textThirst = view.findViewById<TextView>(R.id.tv_thirst)
        textThirst.text

        return view
    }
}