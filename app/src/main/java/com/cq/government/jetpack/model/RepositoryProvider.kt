package com.cq.government.jetpack.model

import android.content.Context
import com.cq.government.jetpack.model.database.AppDataBase
import com.cq.government.jetpack.model.network.PixabayNetwork
import com.cq.government.jetpack.model.repository.PixabayRepository
import com.cq.government.jetpack.model.repository.WordRepository

/**
 *
 * @Description:    提供仓库的工具
 * @Author:         user
 * @CreateDate:     2020/4/20 9:07
 * @version         版本号
 *
 */
object RepositoryProvider {
    fun providerWordRepository(context: Context): WordRepository {
        return WordRepository.getInstance(AppDataBase.getInstance(context).getWordDao())
    }

    fun providerPixabayRepository():PixabayRepository{
        return PixabayRepository.getInstance(PixabayNetwork.getInstance())
    }
}