package com.cq.government.jetpack.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cq.government.jetpack.model.RepositoryProvider
import com.cq.government.jetpack.model.data.PhotoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private var _photoListLive = MutableLiveData<List<PhotoItem>>()
    val photoListLive: LiveData<List<PhotoItem>>
        get() = _photoListLive

    private val pixabayRepository = RepositoryProvider.providerPixabayRepository()

    fun fetchData(swipeRefreshLayout: SwipeRefreshLayout) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val pixabay =
                    pixabayRepository.getPhotos(
                        "17062634-1e99fe53abe0b4de18d1eb0a1",
                        keyWords.random(),
                        100
                    )
                _photoListLive.value = pixabay.hits.toList()
            } catch (e: Exception) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val keyWords = arrayOf("cat", "dog", "beauty", "phone", "computer", "flower", "animal")
}
