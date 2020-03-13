package com.github.coronatracker.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BindableViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T): Unit
}

fun <T> List<T>.adapt(context: Context, itemId: Int, bindCall: View.(T) -> Unit) = object: RecyclerView.Adapter<BindableViewHolder<T>>() {
    // Inflates the item views
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindableViewHolder<T> {
        val itemView = LayoutInflater.from(context).inflate(itemId, parent)
        return object : BindableViewHolder<T>(itemView) {
            override fun bind(item: T) = itemView.bindCall(item)
        }
    }
    override fun getItemCount(): Int = size

    override fun onBindViewHolder(holder: BindableViewHolder<T>, position: Int) =
        holder.bind(get(position))

}