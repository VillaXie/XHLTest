package com.cq.government.jetpack.view.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cq.government.jetpack.view.adapter.MyAdapter

import com.cq.government.jetpack.R
import com.cq.government.jetpack.model.data.Word
import com.cq.government.jetpack.viewmodel.FirstViewModel
import kotlinx.android.synthetic.main.first_fragment.*

class FirstFragment : Fragment() {
    private var myAdapter: MyAdapter? = null

    companion object {
        fun newInstance() = FirstFragment()
    }

    private lateinit var viewModel: FirstViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        myAdapter = MyAdapter()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = myAdapter
        viewModel = ViewModelProviders.of(this).get(FirstViewModel::class.java)
        viewModel.wordListLiveData!!.observe(viewLifecycleOwner, Observer {
            myAdapter!!.wordList = it as MutableList<Word>
            myAdapter!!.notifyDataSetChanged()
        })
        button_insert.setOnClickListener {
            val word1 = Word("hello", "你好")
            val word2 = Word("world", "世界")
            val word3 = Word("bike", "自行车")
            val word4 = Word("dog", "狗")
            val word5 = Word("pig", "猪猪")
            viewModel.insert(word1, word2, word3, word4, word5)
        }
        button_update.setOnClickListener {
            val word = Word("hi", "你好啊")
            word.id = 90
            viewModel.update(word)
        }
        button_clear.setOnClickListener {
            viewModel.deleteAllWords()
        }
        button_delete.setOnClickListener {
            val word = Word("hi", "你好啊")
            word.id = 90
            viewModel.deleteWords(word)
        }
    }

}
