package com.cq.government.jetpack.model.network

import com.cq.government.jetpack.model.network.api.PixabayService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 *
 * @Description:    图片加载类
 * @Author:         user
 * @CreateDate:     2020/6/16 11:13
 * @version         版本号
 *
 */
class PixabayNetwork private constructor() {
    private val pixabayService = ServiceCreator.create(PixabayService::class.java)

    suspend fun fetchPhotos(key: String, q: String, per_page:Int) = pixabayService.getPhotos(key, q, per_page)

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

            })
        }
    }

    companion object {
        private var INSTANCE: PixabayNetwork? = null
        fun getInstance(): PixabayNetwork {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: PixabayNetwork().also {
                    INSTANCE = it
                }
            }
        }
    }
}