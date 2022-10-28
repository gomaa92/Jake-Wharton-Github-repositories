package com.example.jakewhartongithubrepositories.feature.repositorydetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.jakewhartongithubrepositories.R
import com.example.jakewhartongithubrepositories.core.persentation.viewbinding.viewBinding
import com.example.jakewhartongithubrepositories.core.utils.loadImage
import com.example.jakewhartongithubrepositories.databinding.FragmentRepositoryDetailsBinding

class RepositoryDetailsFragment : Fragment(R.layout.fragment_repository_details) {
    private val args: RepositoryDetailsFragmentArgs by navArgs()
    private val binding by viewBinding(FragmentRepositoryDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUIData()
    }

    private fun setUIData() {
        binding.repositoryOwnerName.text = args.repositoryDetails.name
        binding.repositoryDesc.text = args.repositoryDetails.description
        loadImage(
            binding.root.context,
            args.repositoryDetails.owner?.avatarUrl ?: "",
            binding.repositoryOwnerIV
        )
    }
}