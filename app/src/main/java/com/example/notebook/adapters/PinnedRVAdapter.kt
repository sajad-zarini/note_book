package com.example.notebook.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.PrecomputedTextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook.R
import com.example.notebook.databinding.PinnedRvItemsBinding
import com.example.notebook.listener.CardClickListener
import com.example.notebook.room.entities.NoteEntity

class PinnedRVAdapter(private var data: ArrayList<NoteEntity>, var listener: CardClickListener) :
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
        holder.bind(data[position], listener)
    }

    class PinnedRVViewHolder(private val binding:PinnedRvItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(noteEntity: NoteEntity, listener: CardClickListener) {

            binding.pinnedTitle.setTextFuture(
                PrecomputedTextCompat.getTextFuture(
                    noteEntity.noteModels.title,
                    binding.pinnedTitle.textMetricsParamsCompat,
                    null,
                )
            )
            binding.pinnedDescription.text = noteEntity.noteModels.notes
            binding.pinnedCardView.setCardBackgroundColor(Color.parseColor(noteEntity.noteModels.color))

            binding.pinnedCardView.setOnClickListener {
                listener.onItemClickListener(noteEntity)
            }

            binding.imageFilterButton.setOnClickListener {
                listener.onMenuClickListener(it, noteEntity)
            }

            binding.executePendingBindings()
        }

    }
}