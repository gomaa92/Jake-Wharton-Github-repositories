package com.example.jakewhartongithubrepositories.feature.listrepositories.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.jakewhartongithubrepositories.core.persentation.adapter.BaseListAdapter
import com.example.jakewhartongithubrepositories.core.utils.loadImage
import com.example.jakewhartongithubrepositories.databinding.ItemRepositoryBinding
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity

class ListRepositoryAdapter(private val onItemClickedListener: ((item: RepositoryEntity) -> Unit)) :
    BaseListAdapter<RepositoryEntity>(callback = MessagesDiffCallback()) {

    override fun createBinding(parent: ViewGroup, viewType: Int) =
        ItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

    override fun bind(binding: ViewBinding, position: Int) {
        val itemViewBinding = binding as ItemRepositoryBinding
        val item = getItem(position)
        loadImage(
            binding.root.context,
            item.owner?.avatarUrl ?: "",
            itemViewBinding.imageView
        )
        itemViewBinding.titleTextView.text = item.name

        itemViewBinding.root.setOnClickListener {
            onItemClickedListener(item)
        }
    }
}

class MessagesDiffCallback : DiffUtil.ItemCallback<RepositoryEntity>() {
    override fun areItemsTheSame(oldItem: RepositoryEntity, newItem: RepositoryEntity) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RepositoryEntity, newItem: RepositoryEntity) =
        oldItem.id == newItem.id && oldItem.name == newItem.name
}