package com.example.notebook.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.notebook.R
import com.example.notebook.adapters.PinnedRVAdapter
import com.example.notebook.adapters.UpcomingRvAdapter
import com.example.notebook.databinding.FragmentHomeBinding
import com.example.notebook.models.NoteModels
import com.example.notebook.room.entities.NoteEntity
import com.example.notebook.viewModel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

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
        setupUpComingRecyclerview()

        return binding.root
    }

    private fun setupUpComingRecyclerview() {
        viewModel.liveData.observe(viewLifecycleOwner) { listData ->
            val data: ArrayList<NoteEntity> = ArrayList()

            listData.forEach {
                if (!it.noteModels.pin) {
                    data.add(it)
                }
            }

            if (data.isEmpty()) {
                binding.clickToAddTxt.visibility = View.VISIBLE
            } else {
                binding.clickToAddTxt.visibility = View.GONE
            }

            binding.upcomingRv.adapter = UpcomingRvAdapter(data)
        }
    }

    private fun setupPinnedRecyclerview() {
        viewModel.liveData.observe(viewLifecycleOwner) { listData ->
            val data: ArrayList<NoteEntity> = ArrayList()

            listData.forEach {
                if (it.noteModels.pin) {
                    data.add(it)
                }
            }

            if (data.isEmpty()) {
                binding.pinnedCon.visibility = View.GONE
            } else {
                binding.pinnedCon.visibility = View.VISIBLE
            }

            binding.pinnedRv.adapter = PinnedRVAdapter(data)
        }
    }

    fun fabOnClick(view: View) {
        view.findNavController().navigate(R.id.action_homeFragment_to_singleNoteFragment)
    }

}