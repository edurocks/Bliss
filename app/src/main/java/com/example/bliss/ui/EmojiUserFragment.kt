package com.example.bliss.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.bliss.R
import com.example.bliss.database.entity.EmojiEntity
import com.example.bliss.databinding.FragmentEmojiUserBinding
import com.example.bliss.ui.viewModel.BlissViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmojiUserFragment : Fragment(), View.OnClickListener {

    private val viewModel: BlissViewModel by viewModels()
    private var _binding: FragmentEmojiUserBinding? = null
    private val binding get() = _binding!!
    private var emojiList = arrayListOf<EmojiEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmojiUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.emojiRandom.setOnClickListener(this)
        binding.emojiList.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getEmojiFromDb()
        observeResult()
    }

    private fun observeResult() {
        viewModel.emojisList.observe(viewLifecycleOwner, { emojisList ->
            if(emojisList != null && emojisList.isNotEmpty()){
                Picasso.get().load(emojisList[(emojisList.indices).random()].url)
                                .into(binding.emojiImage)
                emojiList.addAll(emojisList)
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.emojiRandom.id -> viewModel.getEmojiFromDb()
            binding.emojiList.id -> navigateToEmojiList()
        }
    }

    private fun navigateToEmojiList() {
        val bundle = bundleOf("emojiList" to emojiList)
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_emojiUserFragment_to_emojiListFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}