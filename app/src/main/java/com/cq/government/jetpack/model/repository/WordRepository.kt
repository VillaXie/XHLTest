package com.cq.government.jetpack.model.repository

import androidx.lifecycle.LiveData
import com.cq.government.jetpack.model.database.dao.WordDao
import com.cq.government.jetpack.model.data.Word
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 *
 * @Description:    手动写描述
 * @Author:         user
 * @CreateDate:     2020/6/11 14:46
 * @version         版本号
 *
 */
class WordRepository private constructor(private val wordDao: WordDao) {

    companion object {
        @Volatile
        private var instance: WordRepository? = null

        fun getInstance(wordDao: WordDao): WordRepository =
            instance ?: synchronized(this) {
                instance ?: WordRepository(wordDao).also {
                    instance = it
                }
            }
    }

    suspend fun insert(vararg word: Word) {//需要在子线程中操作
        withContext(IO) {
            wordDao.insertWords(*word)
        }
    }

    suspend fun update(vararg word: Word) {//需要在子线程中操作
        withContext(IO) {
            wordDao.update(*word)
        }
    }

    suspend fun deleteWords(vararg word: Word) {//需要在子线程中操作
        withContext(IO) {
            wordDao.deleteWords(*word)
        }
    }


    suspend fun deleteAllWords() {//需要在子线程中操作
        withContext(IO) {
            wordDao.deleteAllWords()
        }
    }

    fun getAllWordsLive(): LiveData<List<Word>> {//查询方法系统默认在子线程中操作
        return wordDao.getAllWords()
    }
}