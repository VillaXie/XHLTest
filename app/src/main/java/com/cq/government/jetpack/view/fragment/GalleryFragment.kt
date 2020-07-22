package com.cq.government.jetpack.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.cq.government.jetpack.R
import com.cq.government.jetpack.view.adapter.GalleryAdapter
import com.cq.government.jetpack.viewmodel.GalleryViewModel
import kotlinx.android.synthetic.main.gallery_fragment.*

class GalleryFragment : Fragment() {

    companion object {
        fun newInstance() = GalleryFragment()
    }

    private lateinit var viewModel: GalleryViewModel

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.gallery_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                swipeLayoutGallery.isRefreshing = true
                viewModel.fetchData(swipeLayoutGallery)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        val galleryAdapter = GalleryAdapter()
        swipeLayoutGallery.isRefreshing = true
        recyclerViewPhoto.apply {
            adapter = galleryAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        viewModel = ViewModelProviders.of(activity!!).get(GalleryViewModel::class.java)
        viewModel.photoListLive.value ?: viewModel.fetchData(swipeLayoutGallery)
        viewModel.photoListLive.observe(viewLifecycleOwner, Observer {
            galleryAdapter.submitList(it)
            swipeLayoutGallery.isRefreshing = false
        })

        swipeLayoutGallery.setOnRefreshListener {
            viewModel.fetchData(swipeLayoutGallery)
        }
    }

}
