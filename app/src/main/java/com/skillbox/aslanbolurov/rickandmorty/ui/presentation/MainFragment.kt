package com.skillbox.aslanbolurov.rickandmorty.ui.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.skillbox.aslanbolurov.rickandmorty.data.RickymortyRepository
import com.skillbox.aslanbolurov.rickandmorty.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    val binding get() = _binding!!

    val rickymortiRecyclerAdapter = RickmortiRecyclerAdapter()

    private val viewModel: MainViewModel by viewModels() {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                    return MainViewModel(RickymortyRepository()) as T
                } else {
                    throw IllegalArgumentException("Unknown class name")
                }

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter =
            rickymortiRecyclerAdapter.withLoadStateFooter(MyLoadStateAdapter())

        binding.swipeRefreshLayout.setOnClickListener {
            rickymortiRecyclerAdapter.refresh()
        }

        rickymortiRecyclerAdapter.loadStateFlow.onEach {
            binding.swipeRefreshLayout.isRefreshing =
                it.refresh == LoadState.Loading

        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.pagedCharacters.onEach {
            rickymortiRecyclerAdapter?.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.isLoading.onEach{
            binding.swipeRefreshLayout.isRefreshing=it
        }.launchIn(viewLifecycleOwner.lifecycleScope)


        lifecycleScope.launchWhenStarted {
            viewModel.throwable.observe(
                viewLifecycleOwner,
                Observer {
                    val showError = it != null
                    binding.recyclerView.isVisible = !showError
                    binding.btnUpdate.isVisible=showError
                }
            )
        }

        binding.btnUpdate.setOnClickListener { viewModel.reloadList() }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}