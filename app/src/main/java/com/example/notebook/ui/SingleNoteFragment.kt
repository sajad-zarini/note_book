package com.example.notebook.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notebook.R
import com.example.notebook.databinding.FragmentSingleNoteBinding
import com.example.notebook.models.NoteModels
import com.example.notebook.viewModel.AppViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleNoteFragment : Fragment() {

    private lateinit var binding: FragmentSingleNoteBinding

    private val viewModel: AppViewModel by viewModels()

    private var savedColor = "#64C8FD"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_single_note, container, false)

        binding.singleNote = this

        return binding.root
    }

    fun addNoteClick(view: View) {
        binding.apply {
            if (this.titleEdtTxt.text.isNullOrBlank()) {
                Snackbar.make(this.mainCoord, "Please Enter your title....", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                if (this.noteEdtTxt.text.isNullOrBlank()) {
                    Snackbar.make(
                        this.mainCoord,
                        "Please Enter your Note....",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    val title = this.noteEdtTxt.text.toString()
                    val note = this.titleEdtTxt.text.toString()
                    val color = savedColor

                    val noteModel = NoteModels(
                        title = title,
                        notes = note,
                        color = color,
                        pin = false
                    )
                    viewModel.insertNoteToDatabase(noteModel)

                    Navigation.findNavController(view).navigate(R.id.action_singleNoteFragment_to_homeFragment)
                }
            }
        }
    }

    fun onColorViewClick(check: View) {
        hideAllCheck()

        check.visibility = View.VISIBLE

        binding.apply {
            when (check.id) {
                this.check1.id -> savedColor = "#64C8FD"
                this.check2.id -> savedColor = "#8069FF"
                this.check3.id -> savedColor = "#FFCC36"
                this.check4.id -> savedColor = "#D77FFD"
                this.check5.id -> savedColor = "#FF419A"
                this.check6.id -> savedColor = "#7FFB76"
            }
        }
    }

    private fun hideAllCheck() {
        binding.apply {
            this.check1.visibility = View.INVISIBLE
            this.check2.visibility = View.INVISIBLE
            this.check3.visibility = View.INVISIBLE
            this.check4.visibility = View.INVISIBLE
            this.check5.visibility = View.INVISIBLE
            this.check6.visibility = View.INVISIBLE
        }
    }
}