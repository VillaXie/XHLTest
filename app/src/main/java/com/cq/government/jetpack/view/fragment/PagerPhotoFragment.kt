package com.cq.government.jetpack.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.cq.government.jetpack.R
import com.cq.government.jetpack.model.data.PhotoItem
import com.cq.government.jetpack.view.adapter.PagerPhotoAdapter
import kotlinx.android.synthetic.main.fragment_pager_photo.*

/**
 *
 * @Description:    手动写描述
 * @Author:         user
 * @CreateDate:     2020/7/8 17:07
 * @version         版本号
 *
 */
class PagerPhotoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pager_photo, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val photoList = arguments?.getParcelableArrayList<PhotoItem>("PHOTO_LIST")
        PagerPhotoAdapter().apply {
            vg_photo.adapter = this
            submitList(photoList)
        }
        vg_photo.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tv_pager_photo.text = "${position + 1} / ${photoList?.size}"
            }
        })
        vg_photo.setCurrentItem(arguments?.getInt("PHOTO_POSITION") ?: 0, false)
    }
}