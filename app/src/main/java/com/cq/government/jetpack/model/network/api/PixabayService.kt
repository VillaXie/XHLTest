package com.cq.government.jetpack.model.network.api

import androidx.lifecycle.MutableLiveData
import com.cq.government.jetpack.model.data.PhotoItem
import com.cq.government.jetpack.model.data.Pixabay
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * @Description:    手动写描述
 * @Author:         user
 * @CreateDate:     2020/6/16 9:51
 * @version         版本号
 *
 */
interface PixabayService {
    @GET("api")
    suspend fun getPhotos(@Query("key") key: String, @Query("q") q: String
    , @Query("per_page") per_page:Int): Pixabay
}