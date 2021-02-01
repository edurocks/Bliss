package com.example.bliss.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bliss.databinding.FragmentGoogleReposBinding
import com.example.bliss.ui.adapters.UserReposAdapter
import com.example.bliss.ui.viewModel.BlissViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserReposFragment : Fragment() {

    private val viewModel: BlissViewModel by viewModels()
    private var _binding: FragmentGoogleReposBinding? = null
    private val binding get() = _binding!!
    private lateinit var userReposAdapter: UserReposAdapter
    private var linearLayoutManager: LinearLayoutManager? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?
                              ,savedInstanceState: Bundle?): View {
        _binding = FragmentGoogleReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        viewModel.getUserReposList()
        observeResult()
    }

    private fun initRecyclerView() {
        linearLayoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false)
        binding.googleReposRecyclerView.setHasFixedSize(true)
        binding.googleReposRecyclerView.layoutManager = linearLayoutManager
    }

    private fun observeResult() {

        userReposAdapter = UserReposAdapter()
        binding.googleReposRecyclerView.adapter = userReposAdapter

        viewModel.viewModelScope.launch {
            viewModel.getUserReposList().collectLatest {
                userReposAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}