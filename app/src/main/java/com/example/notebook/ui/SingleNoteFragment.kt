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

    private var savedColor = "#64C8FD"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_single_note, container, false)

        binding.singleNote = this

        return binding.root
    }

    fun onColorViewClick(check: View) {
        hideAllCheck()

        check.visibility = View.VISIBLE

        binding.apply {
            when(check.id) {
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