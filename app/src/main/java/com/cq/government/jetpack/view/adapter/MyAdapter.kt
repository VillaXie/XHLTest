package com.cq.government.jetpack.view.adapter


import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cq.government.jetpack.R
import com.cq.government.jetpack.model.data.Word
import kotlinx.android.synthetic.main.item_normal.view.*

/**
 *
 * @Description:    手动写描述
 * @Author:         user
 * @CreateDate:     2020/6/12 10:11
 * @version         版本号
 *
 */
class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    var wordList = mutableListOf<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_normal, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val word = wordList[position]
        holder.itemView.textView.text = (position + 1).toString()
        holder.itemView.textView2.text = word.word
        holder.itemView.textView3.text = word.chineseMeaning
        holder.itemView.setOnClickListener {
            val uri:Uri = Uri.parse("http://m.youdao.com/dict?le=eng&q=" + word.word)
            val intent=Intent(Intent.ACTION_VIEW)
            intent.data = uri
            holder.itemView.context.startActivity(intent)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}