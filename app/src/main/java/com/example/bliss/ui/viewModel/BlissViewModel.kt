package com.example.bliss.ui.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bliss.database.entity.EmojiEntity
import com.example.bliss.database.entity.UserAvatar
import com.example.bliss.repository.BlissRepository
import kotlinx.coroutines.launch

class BlissViewModel @ViewModelInject constructor(private val blissRepository: BlissRepository)
    : ViewModel() {


    private var _emojisListValue : MutableLiveData<List<EmojiEntity>> = MutableLiveData()

    val emojisList : LiveData<List<EmojiEntity>>
    get() {
        return _emojisListValue
    }

    private var _userAvatarValue : MutableLiveData<UserAvatar> = MutableLiveData()

    val userAvatar : LiveData<UserAvatar>
        get() {
            return _userAvatarValue
        }

    private var _allAvatarsValue : MutableLiveData<List<UserAvatar>> = MutableLiveData()

    val allAvatar : LiveData<List<UserAvatar>>
        get() {
            return _allAvatarsValue
        }

    fun getEmojiFromDb()  {
        viewModelScope.launch {
            _emojisListValue.value = blissRepository.getEmojisFromDb()
        }
    }

    fun getAvatar(name : String)  {
      viewModelScope.launch{
            blissRepository.getAvatarFromDb(name)
            getAvatarFromDb(name)
        }
    }

    private fun getAvatarFromDb(name : String){
        viewModelScope.launch {
            _userAvatarValue.value = blissRepository.getAvatarFromDb(name)
        }
    }

    fun getAllAvatarsFromDb()  {
        viewModelScope.launch{
           _allAvatarsValue.value = blissRepository.getAllAvatars()
        }
    }

    fun deleteAvatar(userAvatar: UserAvatar)  {
        viewModelScope.launch{
            blissRepository.deleteAvatar(userAvatar)
        }
    }
}