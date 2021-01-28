package com.example.bliss.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bliss.database.entity.EmojiEntity
import com.example.bliss.databinding.EmojiRowBinding
import com.squareup.picasso.Picasso

class EmojiAdapter(private val emojiList : ArrayList<EmojiEntity>,
                   private val emojiClickListener: emojiRowClickListener)
: RecyclerView.Adapter<EmojiAdapter.EmojiHolder>() {

    class EmojiHolder(private val itemBinding: EmojiRowBinding)
                        : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(emoji: EmojiEntity) {
            Picasso.get().load(emoji.url)
                    .into(itemBinding.imageEmoji)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiHolder {
        val itemBinding = EmojiRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmojiHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: EmojiHolder, position: Int) {
        val emoji = emojiList[position]
        holder.bind(emoji)

        holder.itemView.setOnClickListener {
            emojiClickListener.onEmojiClickListener(position, emoji)
        }
    }

    override fun getItemCount(): Int {
        return emojiList.size
    }

    interface emojiRowClickListener {
        fun onEmojiClickListener(position : Int, emoji: EmojiEntity)
    }
}