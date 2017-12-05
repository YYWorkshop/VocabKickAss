package com.yyworkshop.vocabkickass

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.yyworkshop.vocabkickass.util.DictFileUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.wtf

class MainActivity : AppCompatActivity(), AnkoLogger {

    private var doubleBackToExitPressedOnce: Boolean = false

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_dictionary -> {
//                message.setText(R.string.navi_title_dictionary)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_new_words -> {
//                message.setText(R.string.navi_title_new_words)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
//                message.setText(R.string.navi_title_setting)
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

    override fun onDestroy() {
        super.onDestroy()

        wtf("onDestroy")

        System.exit(0)
    }



//    override fun onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed()
//            return;
//        }
//
//        doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        Handler().postDelayed({
//
//            fun run() {
//                doubleBackToExitPressedOnce=false
//            }
//        }, 2000)
//    }
}
