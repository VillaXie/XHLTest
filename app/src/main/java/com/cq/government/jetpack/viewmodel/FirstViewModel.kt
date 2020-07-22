package com.cq.government.jetpack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cq.government.jetpack.model.RepositoryProvider
import com.cq.government.jetpack.model.data.Word
import com.cq.government.jetpack.model.repository.WordRepository
import kotlinx.coroutines.launch

class FirstViewModel(application: Application) : AndroidViewModel(application) {
    var wordListLiveData: LiveData<List<Word>>? = null
    private var wordRepository: WordRepository = RepositoryProvider.providerWordRepository(application)

    init {
        wordListLiveData = wordRepository.getAllWordsLive()//获取所有数据
    }

    fun insert(vararg word: Word) {
        viewModelScope.launch {
            wordRepository.insert(*word)
        }
    }

    fun update(vararg word: Word) {
        viewModelScope.launch {
            wordRepository.update(*word)
        }
    }

    fun deleteWords(vararg word: Word) {
        viewModelScope.launch {
            wordRepository.deleteWords(*word)
        }
    }

    fun deleteAllWords() {
        viewModelScope.launch {
            wordRepository.deleteAllWords()
        }
    }
}
