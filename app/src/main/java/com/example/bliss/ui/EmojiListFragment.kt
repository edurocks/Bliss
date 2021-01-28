package com.example.bliss.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bliss.R
import com.example.bliss.database.entity.EmojiEntity
import com.example.bliss.databinding.FragmentEmojiListBinding
import com.example.bliss.ui.adapters.EmojiAdapter
import java.util.ArrayList

class EmojiListFragment : Fragment(), EmojiAdapter.emojiRowClickListener {

    private var _binding: FragmentEmojiListBinding? = null
    private val binding get() = _binding!!
    private lateinit var emojiAdapter: EmojiAdapter
    private var gridLayoutManager: GridLayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentEmojiListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments.let {
           val emojiData = it?.getParcelableArrayList<EmojiEntity>("emojiList")
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
        Log.e("nome", emoji.name)
    }
}