package com.cq.government.jetpack.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @Description:    手动写描述
 * @Author:         user
 * @CreateDate:     2020/6/10 16:55
 * @version         版本号
 *
 */
@Entity(tableName = "Word")
data class Word(@ColumnInfo(name = "word") var word: String,
                @ColumnInfo(name = "chineseMeaning") var chineseMeaning: String) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}