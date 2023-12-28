package com.example.notebook.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.notebook.R
import com.example.notebook.adapters.PinnedRVAdapter
import com.example.notebook.databinding.FragmentHomeBinding
import com.example.notebook.models.NoteModels
import com.example.notebook.viewModel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: AppViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)

        binding.fragmentHome = this

        setupPinnedRecyclerview()

        return binding.root
    }

    private fun setupPinnedRecyclerview() {
        val data: ArrayList<NoteModels> = ArrayList()
        data.add(NoteModels("note 1", "this is note 1", "", false))
        data.add(NoteModels("note 2", "this is note 2", "", false))
        data.add(NoteModels("note 3", "this is note 3", "", false))
        data.add(NoteModels("note 4", "this is note 4", "", false))

        if (data.isEmpty()) {
            binding.pinnedCon.visibility = View.GONE
        }else {
            binding.pinnedCon.visibility = View.VISIBLE
        }

        binding.pinnedRv.adapter = PinnedRVAdapter(data)
    }

    fun fabOnClick(view: View) {
        view.findNavController().navigate(R.id.action_homeFragment_to_singleNoteFragment)
    }

}