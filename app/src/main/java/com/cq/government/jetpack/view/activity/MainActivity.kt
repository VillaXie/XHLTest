package com.cq.government.jetpack.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.cq.government.jetpack.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var bottomNavigationView: BottomNavigationView? = null//对应该界面中的底部菜单
    private var navController: NavController? = null//对应该界面中的fragment
    private var appBarConfiguration: AppBarConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        navController = Navigation.findNavController(this, R.id.fragment)
        appBarConfiguration = AppBarConfiguration.Builder(bottomNavigationView!!.menu)
            .build()//关联BottomNavigationView中的menu
//        NavigationUI.setupActionBarWithNavController(this, navController!!, appBarConfiguration!!)//关联ActionBar随着底部导航的变化
        NavigationUI.setupWithNavController(
            bottomNavigationView!!,
            navController!!
        )//关联fragment随着底部导航的变化
    }
}
