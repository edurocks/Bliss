package com.example.bliss.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bliss.R
import com.example.bliss.databinding.FragmentAvatarListBinding
import com.example.bliss.ui.adapters.UserAvatarAdapter

class AvatarListFragment : Fragment() {

    private var _binding: FragmentAvatarListBinding? = null
    private val binding get() = _binding!!
    private lateinit var userAvatarAdapter: UserAvatarAdapter
    private var gridLayoutManager: GridLayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?
                              , savedInstanceState: Bundle?): View {
        _binding = FragmentAvatarListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        gridLayoutManager = GridLayoutManager(requireContext(), 4)
        binding.avatarRecyclerView.layoutManager = gridLayoutManager
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}