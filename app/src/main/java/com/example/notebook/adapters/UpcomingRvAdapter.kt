package com.example.notebook.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook.R
import com.example.notebook.databinding.UpcomingRvItemsBinding
import com.example.notebook.listener.CardClickListener
import com.example.notebook.room.entities.NoteEntity

class UpcomingRvAdapter(private val model: ArrayList<NoteEntity>, var listener: CardClickListener) : RecyclerView.Adapter<UpcomingRvAdapter.UpcomingRvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingRvViewHolder {
        val upcomingRvItemsBinding: UpcomingRvItemsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.upcoming_rv_items,parent,false)
        return UpcomingRvViewHolder(upcomingRvItemsBinding)
    }

    override fun onBindViewHolder(holder: UpcomingRvViewHolder, position: Int) {
        holder.bind(model[position], listener)
    }

    override fun getItemCount() = model.size

    class UpcomingRvViewHolder(private val binding: UpcomingRvItemsBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(noteEntity: NoteEntity, listener: CardClickListener) {


            binding.upcomingCard.setCardBackgroundColor(Color.parseColor(noteEntity.noteModels.color))
            binding.pinnedTitle.text = noteEntity.noteModels.title
            binding.pinnedDescription.text = noteEntity.noteModels.notes

            binding.upcomingCard.setOnClickListener {
                listener.onItemClickListener(noteEntity)
            }

            binding.executePendingBindings()
        }
    }
}