package com.example.jakewhartongithubrepositories.core.persentation.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


/**
 * Base list adapter
 *
 * Utilize creating and binding data into viewHolder
 * @param T the data item class.
 * @see BaseViewHolder
 */
abstract class BaseListAdapter<T>(callback: DiffUtil.ItemCallback<T> = BaseDiffCallback()) :
    ListAdapter<T, BaseViewHolder<ViewBinding>>(callback) {

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding>, position: Int) {
        bind(holder.binding, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        getViewHolder(parent, viewType)

    open fun getViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(createBinding(parent, viewType))

    abstract fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding

    protected abstract fun bind(binding: ViewBinding, position: Int)

    fun addItems(items :List<T>){
        this.currentList.addAll(items)
    }
}

open class BaseDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}

open class BaseViewHolder<out T : ViewBinding>(val binding: T) :
    RecyclerView.ViewHolder(binding.root)