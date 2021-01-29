package com.example.bliss.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.bliss.R
import com.example.bliss.database.entity.EmojiEntity
import com.example.bliss.databinding.FragmentEmojiListBinding
import com.example.bliss.ui.adapters.EmojiAdapter
import java.util.*

class EmojiListFragment : Fragment(), EmojiAdapter.emojiRowClickListener,
                                      SwipeRefreshLayout.OnRefreshListener {

    private var _binding: FragmentEmojiListBinding? = null
    private val binding get() = _binding!!
    private lateinit var emojiAdapter: EmojiAdapter
    private var gridLayoutManager: GridLayoutManager? = null
    private var emojiData : ArrayList<EmojiEntity>? = null
    private var emojiDataOriginal = arrayListOf<EmojiEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentEmojiListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.refreshEmoji.setOnRefreshListener(this)
        binding.refreshEmoji.setColorSchemeColors(
                ContextCompat.getColor(requireContext(), R.color.background),
                ContextCompat.getColor(requireContext(),android.R.color.holo_green_dark),
                ContextCompat.getColor(requireContext(),android.R.color.holo_orange_dark),
                ContextCompat.getColor(requireContext(), android.R.color.holo_blue_dark)
        )

        arguments.let {
            emojiData = it?.getParcelableArrayList("emojiList")
            emojiDataOriginal.addAll(emojiData!!)
            populateList(emojiData)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        gridLayoutManager = GridLayoutManager(requireContext(), 4)
        binding.emojiRecyclerView.layoutManager = gridLayoutManager
    }

    private fun populateList(emojiData: ArrayList<EmojiEntity>?) {
        emojiAdapter = EmojiAdapter(emojiData!!, this)
        binding.emojiRecyclerView.adapter = emojiAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onEmojiClickListener(position : Int, emoji: EmojiEntity) {
        emojiAdapter.removeAt(position)
    }

    override fun onRefresh() {
        emojiDataOriginal.let {
            emojiAdapter.restoreList(emojiDataOriginal)
        }
        binding.refreshEmoji.isRefreshing = false
    }
}