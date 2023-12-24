package com.example.notebook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.PrecomputedTextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook.R
import com.example.notebook.databinding.PinnedRvItemsBinding
import com.example.notebook.models.NoteModels

class PinnedRVAdapter(private var data: ArrayList<NoteModels>) :
    RecyclerView.Adapter<PinnedRVAdapter.PinnedRVViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PinnedRVViewHolder {
        val pinnedRvItemsBinding: PinnedRvItemsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.pinned_rv_items,
            parent,
            false,
        )

        return PinnedRVViewHolder(pinnedRvItemsBinding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PinnedRVViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class PinnedRVViewHolder(private val binding:PinnedRvItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(noteModels: NoteModels) {
            binding.pinnedTitle.setTextFuture(
                PrecomputedTextCompat.getTextFuture(
                    noteModels.title,
                    binding.pinnedTitle.textMetricsParamsCompat,
                    null,
                )
            )

            binding.pinnedDescription.text = noteModels.notes
        }

    }
}