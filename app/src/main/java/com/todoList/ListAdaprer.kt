package com.todoList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todoList.databinding.ItemMainBinding
import com.todoList.db.Entity

class ListAdapter (
    private val items: List<Entity>,
    private val onClick: (Entity) -> Unit,
    private val clickDelete: (Entity) -> Unit
) : RecyclerView.Adapter<ListAdapter.LisHolder>() {

    inner class LisHolder(private val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Entity) =
            with(binding) {
                title.text = todo.title
                description.text = todo.description

                root.setOnClickListener { onClick(todo) }
                delete.setOnClickListener { clickDelete(todo) }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LisHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LisHolder(binding)
    }

    override fun onBindViewHolder(holder: LisHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

