package com.cq.government.jetpack.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cq.government.jetpack.model.data.Word

/**
 *
 * @Description:    手动写描述
 * @Author:         user
 * @CreateDate:     2020/6/10 17:02
 * @version         版本号
 *
 */
@Dao  //Database access object
interface WordDao {
    @Insert
    fun insertWords(vararg word: Word)

    @Update
    fun update(vararg word: Word)

    @Delete
    fun deleteWords(vararg word: Word)

    @Query("DELETE FROM WORD")
    fun deleteAllWords()

    @Query("SELECT * FROM WORD ORDER BY ID ASC")
    fun getAllWords(): LiveData<List<Word>>
}