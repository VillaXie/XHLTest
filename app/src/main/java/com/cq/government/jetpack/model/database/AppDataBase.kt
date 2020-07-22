package com.cq.government.jetpack.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cq.government.jetpack.model.database.dao.WordDao
import com.cq.government.jetpack.model.data.Word

/**
 *
 * @Description:    手动写描述
 * @Author:         user
 * @CreateDate:     2020/6/11 10:26
 * @version         版本号
 *
 */
@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getWordDao(): WordDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDataBase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDataBase(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context.applicationContext, AppDataBase::class.java,
                "government-database"
            ).build()
        }
    }
}