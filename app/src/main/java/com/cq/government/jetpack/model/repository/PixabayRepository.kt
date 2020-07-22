package com.cq.government.jetpack.model.repository

import com.cq.government.jetpack.model.network.PixabayNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *
 * @Description:    图片仓库
 * @Author:         user
 * @CreateDate:     2020/6/16 14:56
 * @version         版本号
 *
 */
class PixabayRepository private constructor(private val pixabayNetwork: PixabayNetwork) {

    suspend fun getPhotos(key: String, q: String, per_page: Int) = withContext(Dispatchers.IO) {
        pixabayNetwork.fetchPhotos(key, q, per_page)
    }

    companion object {

        private lateinit var instance: PixabayRepository

        fun getInstance(network: PixabayNetwork): PixabayRepository {
            if (!::instance.isInitialized) {
                synchronized(PixabayRepository::class.java) {
                    if (!::instance.isInitialized) {
                        instance = PixabayRepository(network)
                    }
                }
            }
            return instance
        }

    }
}