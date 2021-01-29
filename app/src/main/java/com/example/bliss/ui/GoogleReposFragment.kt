package com.example.bliss.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bliss.R
import com.example.bliss.databinding.FragmentGoogleReposBinding
import com.example.bliss.ui.viewModel.BlissViewModel


class GoogleReposFragment : Fragment() {

    private val viewModel: BlissViewModel by viewModels()
    private var _binding: FragmentGoogleReposBinding? = null
    private val binding get() = _binding!!
    private var linearLayoutManager: LinearLayoutManager? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?
                              ,savedInstanceState: Bundle?): View? {
        _binding = FragmentGoogleReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        observeResult()
    }

    private fun initRecyclerView() {
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.googleReposRecyclerView.setHasFixedSize(true)
        binding.googleReposRecyclerView.layoutManager = linearLayoutManager
    }

    private fun observeResult() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}