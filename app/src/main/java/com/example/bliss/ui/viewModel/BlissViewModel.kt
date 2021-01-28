package com.example.bliss.ui.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bliss.database.entity.EmojiEntity
import com.example.bliss.repository.BlissRepository
import kotlinx.coroutines.launch

class BlissViewModel @ViewModelInject constructor(private val blissRepository: BlissRepository)
    : ViewModel() {


    var _emojisListValue : MutableLiveData<List<EmojiEntity>> = MutableLiveData()

    val emojisList : LiveData<List<EmojiEntity>>
    get() {
        return _emojisListValue
    }

    fun getEmojiFromDb()  {
        viewModelScope.launch {
            _emojisListValue.value = blissRepository.getEmojisFromDb()
        }
    }
}