package com.yyworkshop.vocabkickass

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.yyworkshop.vocabkickass.util.DictFileUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainActivity : AppCompatActivity(), AnkoLogger {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_dictionary -> {
                message.setText(R.string.navi_title_dictionary)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_new_words -> {
                message.setText(R.string.navi_title_new_words)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                message.setText(R.string.navi_title_setting)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        info("onCreate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

//        DictFileUtil.readFile(this);
    }
}
