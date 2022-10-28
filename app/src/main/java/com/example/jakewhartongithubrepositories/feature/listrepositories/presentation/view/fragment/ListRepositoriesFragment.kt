package com.example.jakewhartongithubrepositories.feature.listrepositories.presentation.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.jakewhartongithubrepositories.R
import com.example.jakewhartongithubrepositories.core.data.local.RepositoryDao
import com.example.jakewhartongithubrepositories.core.persentation.screens.BaseFragment
import com.example.jakewhartongithubrepositories.core.persentation.viewbinding.viewBinding
import com.example.jakewhartongithubrepositories.core.utils.MAX_PER_PAGE
import com.example.jakewhartongithubrepositories.core.utils.showDialog
import com.example.jakewhartongithubrepositories.core.utils.showSnack
import com.example.jakewhartongithubrepositories.databinding.FragmentListRepositoriesBinding
import com.example.jakewhartongithubrepositories.feature.listrepositories.data.model.RepositoryEntity
import com.example.jakewhartongithubrepositories.feature.listrepositories.presentation.view.adapter.ListRepositoryAdapter
import com.example.jakewhartongithubrepositories.feature.listrepositories.presentation.viewmodel.ListRepositoriesContract.*
import com.example.jakewhartongithubrepositories.feature.listrepositories.presentation.viewmodel.ListRepositoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ListRepositoriesFragment :
    BaseFragment<ListRepositoriesViewState, ListRepositoriesViewEvent, ListRepositoriesActions, ListRepositoriesResult>(
        R.layout.fragment_list_repositories
    ) {
    override val viewModel: ListRepositoriesViewModel by viewModels()
    private lateinit var adapter: ListRepositoryAdapter
    private val binding by viewBinding(FragmentListRepositoriesBinding::bind)

    @Inject
    lateinit var repositoryDao: RepositoryDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        handleViewEvent()
        loadMore()
    }

    private fun loadMore() {
        binding.refreshLayout.setOnLoadMoreListener {
            viewModel.dispatch(ListRepositoriesActions.GetRepositories)
        }
    }

    private fun initRecyclerView() {
        adapter = ListRepositoryAdapter {
            Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
        }
        binding.repositoriesRv.adapter = adapter
    }

    private fun disableLoadMore() {
        binding.refreshLayout.finishLoadMore()
        binding.refreshLayout.setEnableLoadMore(false)
    }

    private fun handleViewEvent() {
        initViewEvents { event ->
            when (event) {
                is ListRepositoriesViewEvent.Failure -> {
                    disableLoadMore()
                    handleLoading(false)
                    showFailureDialog()
                }
            }
        }
    }

    private fun showFailureDialog() {
        context?.let {
            it.showDialog(
                title = getString(R.string.failure_title),
                desc = getString(R.string.failure_desc),
                positiveButtonText = getString(R.string.try_again),
                negativeButtonText = getString(R.string.cancel),
                positiveButtonAction = { viewModel.dispatch(ListRepositoriesActions.GetRepositories) },
                negativeButtonAction = {}
            )
        }
    }

    override fun handleViewState(state: ListRepositoriesViewState) {
        when (state) {
            is ListRepositoriesViewState.Loading -> handleLoading(state.isLoading)
            is ListRepositoriesViewState.Success -> {
                handleLoading(false)
                setAdapterData(state.repositories)
            }
            is ListRepositoriesViewState.SuccessLocal -> {
                handleLoading(false)
                setAdapterData(state.repositories, isLocal = true)
            }
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    private fun setAdapterData(repositories: List<RepositoryEntity>, isLocal: Boolean = false) {
        if (repositories.isNotEmpty()) {

            binding.refreshLayout.finishLoadMore()
            binding.refreshLayout.setEnableLoadMore(repositories.size >= MAX_PER_PAGE)
            if (isLocal) showLocalSourceSnackBar()
            submitList(repositories)
        } else {

            if (isLocal) showLocalEmptyDataDialog()
            else showRemoteEmptyDataDialog()
        }
    }

    private fun submitList(repositories: List<RepositoryEntity>) {
        val currentList = adapter.currentList
        val list = mutableListOf<RepositoryEntity>()


        if (currentList.isNotEmpty()) {
            list.addAll(currentList)
        }
        list.addAll(repositories)
        adapter.submitList(list)
    }

    private fun showLocalSourceSnackBar() {
        binding.root.showSnack(getString(R.string.local_source))
        disableLoadMore()
    }

    private fun showRemoteEmptyDataDialog() {
        context?.let {
            it.showDialog(
                title = getString(R.string.empty_repositories_title_remote),
                desc = getString(R.string.empty_repositories_desc_remote),
                positiveButtonText = getString(R.string.ok),
                negativeButtonText = getString(R.string.cancel),
                positiveButtonAction = {},
                negativeButtonAction = {}
            )
        }
    }

    private fun showLocalEmptyDataDialog() {
        context?.let {
            it.showDialog(
                title = getString(R.string.empty_repositories_title_local),
                desc = getString(R.string.empty_repositories_desc_local),
                positiveButtonText = getString(R.string.try_again),
                negativeButtonText = getString(R.string.cancel),
                positiveButtonAction = {
                    handleLoading(true)
                    viewModel.dispatch(ListRepositoriesActions.GetRepositories)
                },
                negativeButtonAction = {}
            )
        }
    }
}