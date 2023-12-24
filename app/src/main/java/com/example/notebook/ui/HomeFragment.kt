package com.example.notebook.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.notebook.R
import com.example.notebook.adapters.PinnedRVAdapter
import com.example.notebook.databinding.FragmentHomeBinding
import com.example.notebook.models.NoteModels

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)

        setupPinnedRecyclerview()

        return binding.root
    }

    private fun setupPinnedRecyclerview() {
        val data: ArrayList<NoteModels> = ArrayList()
        data.add(NoteModels("note 1", "this is note 1"))
        data.add(NoteModels("note 2", "this is note 2"))
        data.add(NoteModels("note 3", "this is note 3"))
        data.add(NoteModels("note 4", "this is note 4"))

        if (data.isEmpty()) {
            binding.pinnedCon.visibility = View.GONE
        }else {
            binding.pinnedCon.visibility = View.VISIBLE
        }

        binding.pinnedRv.adapter = PinnedRVAdapter(data)
    }

}