package com.example.bliss.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bliss.database.entity.UserAvatar
import com.example.bliss.databinding.FragmentAvatarListBinding
import com.example.bliss.ui.adapters.UserAvatarAdapter
import com.example.bliss.ui.viewModel.BlissViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AvatarListFragment : Fragment(), UserAvatarAdapter.userAvatarRowClickListener {

    private val viewModel: BlissViewModel by viewModels()
    private var _binding: FragmentAvatarListBinding? = null
    private val binding get() = _binding!!
    private lateinit var userAvatarAdapter: UserAvatarAdapter
    private var gridLayoutManager: GridLayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAvatarListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        viewModel.getAllAvatarsFromDb()
        observeResult()
    }

    private fun observeResult() {
        viewModel.allAvatar.observe(viewLifecycleOwner, { userAvatarList ->
            if (userAvatarList != null && userAvatarList.isNotEmpty()) {
                userAvatarAdapter = UserAvatarAdapter(userAvatarList as ArrayList<UserAvatar>,
                        this)
                binding.avatarRecyclerView.adapter = userAvatarAdapter
            }
        })
    }

    private fun initRecyclerView() {
        gridLayoutManager = GridLayoutManager(requireContext(), 4)
        binding.avatarRecyclerView.setHasFixedSize(true)
        binding.avatarRecyclerView.layoutManager = gridLayoutManager
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onUserAvatarClickListener(position: Int, userAvatar: UserAvatar) {
        userAvatarAdapter.removeAt(position)
        viewModel.deleteAvatar(userAvatar)
    }
}