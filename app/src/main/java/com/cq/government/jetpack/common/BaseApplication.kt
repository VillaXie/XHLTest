package com.cq.government.jetpack.common

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 *
 * @Description:    手动写描述
 * @Author:         user
 * @CreateDate:     2020/4/17 10:01
 * @version         版本号
 *
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}