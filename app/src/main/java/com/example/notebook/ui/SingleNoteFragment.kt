package com.example.notebook.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.notebook.R
import com.example.notebook.databinding.FragmentSingleNoteBinding

class SingleNoteFragment : Fragment() {

    private lateinit var binding: FragmentSingleNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_single_note, container, false)

        return binding.root
    }
}