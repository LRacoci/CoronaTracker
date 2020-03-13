package com.github.coronatracker.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BindableViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T): Unit
}

fun <T> List<T>.adapt(
    context: Context,
    @LayoutRes itemId: Int,
    bindCall: View.(T) -> Unit
) = object: RecyclerView.Adapter<BindableViewHolder<T>>() {
    // Inflates the item views
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindableViewHolder<T> {
        val itemView = LayoutInflater.from(context).inflate(itemId, parent, false)
        return object : BindableViewHolder<T>(itemView) {
            override fun bind(item: T) = itemView.bindCall(item)
        }
    }
    override fun getItemCount(): Int = size

    override fun onBindViewHolder(holder: BindableViewHolder<T>, position: Int) =
        holder.bind(get(position))

}

abstract class ViewBindViewHolder<V: ViewBinding, M>(itemView: View) : RecyclerView.ViewHolder(itemView)

fun <V: ViewBinding, M> List<M>.adapt(context: Context, bindCall: V.(M) -> Unit): RecyclerView.Adapter<ViewBindViewHolder<V, M>> {
    return object : RecyclerView.Adapter<ViewBindViewHolder<V, M>>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewBindViewHolder<V, M> {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: ViewBindViewHolder<V, M>, position: Int) {
            get(position)
        }

        override fun getItemCount(): Int = size

    }
}
