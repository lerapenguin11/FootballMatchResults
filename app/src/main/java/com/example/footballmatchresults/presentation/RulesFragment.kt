package com.example.footballmatchresults.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.footballmatchresults.R
import com.example.footballmatchresults.databinding.FragmentHomeBinding
import com.example.footballmatchresults.databinding.FragmentRulesBinding
import com.example.footballmatchresults.utilits.replaceFragment

class RulesFragment : Fragment() {
    private var _binding : FragmentRulesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRulesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        onClick()
    }

    private fun onClick() {
        binding.btArrow.setOnClickListener {
            replaceFragment(HomeFragment())
        }

        binding.btInfoMatch.setOnClickListener {
            Toast.makeText(context, R.string.info_rules, Toast.LENGTH_SHORT).show()
        }
    }
}