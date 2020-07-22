package com.cq.government.jetpack.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cq.government.jetpack.R
import com.cq.government.jetpack.model.data.PhotoItem
import kotlinx.android.synthetic.main.item_photo_pager.view.*

/**
 *
 * @Description:    手动写描述
 * @Author:         user
 * @CreateDate:     2020/7/8 17:14
 * @version         版本号
 *
 */
class PagerPhotoAdapter : ListAdapter<PhotoItem, PagerPhotoHolder>(DIFFCALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerPhotoHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_photo_pager, parent, false)
            .apply {
                return PagerPhotoHolder(this)
            }
    }

    override fun onBindViewHolder(holder: PagerPhotoHolder, position: Int) {
        Glide.with(holder.itemView).load(getItem(position).previewUrl)
            .placeholder(R.drawable.ic_insert_photo_grey).into(holder.itemView.iv_pager_photo)
    }

    object DIFFCALLBACK : DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.photoId == newItem.photoId
        }

    }
}

class PagerPhotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
