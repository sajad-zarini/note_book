package com.example.notebook.ui

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.notebook.MainActivity
import com.example.notebook.R
import com.example.notebook.databinding.FragmentSingleNoteBinding
import com.example.notebook.models.NoteModels
import com.example.notebook.room.entities.NoteEntity
import com.example.notebook.viewModel.AppViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleNoteFragment : Fragment() {

    private lateinit var binding: FragmentSingleNoteBinding

    private val viewModel: AppViewModel by viewModels()

    private lateinit var noteEntity: NoteEntity

    private var savedColor = "#64C8FD"

    private lateinit var mainActivity: MainActivity
    private lateinit var navController: NavController

    private var pinned = false

    private var isUpdating = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_single_note, container, false)

        binding.singleNote = this

        getData()

        return binding.root
    }

    private fun Bundle.parcelable(key: String): NoteEntity = when {
        SDK_INT >= 33 -> getParcelable(key, NoteEntity::class.java)!!
        else -> (@Suppress("DEPRECATION") getParcelable(key) as? NoteEntity)!!
    }

    private fun getData() {
        if (arguments != null) {

            noteEntity = arguments?.parcelable("data_model")!!

            binding.titleEdtTxt.setText(noteEntity.noteModels.title)
            binding.noteEdtTxt.setText(noteEntity.noteModels.notes)

            pinned = noteEntity.noteModels.pin
            savedColor = noteEntity.noteModels.color

            isUpdating = true

            hideAllCheck()
            colorCheckToVisible(noteEntity.noteModels.color)
        }
    }

    /// visible exact color according to card click
    private fun colorCheckToVisible(color: String) {
        binding.apply {
            when (color) {
                "#64C8FD" -> this.check1.visibility = View.VISIBLE
                "#8069FF" -> this.check2.visibility = View.VISIBLE
                "#FFCC36" -> this.check3.visibility = View.VISIBLE
                "#D77FFD" -> this.check4.visibility = View.VISIBLE
                "#FF419A" -> this.check5.visibility = View.VISIBLE
                "#7FFB76" -> this.check6.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar(view)
    }

    private fun setUpToolbar(view: View) {
        navController = Navigation.findNavController(view)
        val appBarConfiguration = AppBarConfiguration.Builder(R.id.singleNoteFragment).build()
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)

        mainActivity.setSupportActionBar(toolbar)
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // for change left icon of toolbar (back Icon)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.singleNoteFragment) {
                toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.single_note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.pin_item ->
                if (!pinned) {
                    item.icon = ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.baseline_push_pin_24
                    )
                    pinned = !pinned
                    true
                } else {
                    item.icon = ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.baseline_outline_push_pin_24
                    )
                    pinned = !pinned
                    true
                }
            android.R.id.home -> {
                mainActivity.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
                        pin = pinned
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}