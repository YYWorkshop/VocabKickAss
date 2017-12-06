package com.yyworkshop.vocabkickass

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.yyworkshop.vocabkickass.adapter.SearchResultRecyclerViewAdapter
import com.yyworkshop.vocabkickass.data.DictConstarct
import com.yyworkshop.vocabkickass.util.DictFileUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.wtf

class MainActivity : AppCompatActivity(), AnkoLogger {

    private var doubleBackToExitPressedOnce: Boolean = false
    private var searchResultViewAdapter = SearchResultRecyclerViewAdapter()

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

        rv_search_result.adapter = searchResultViewAdapter
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_search_result.layoutManager = layoutManager

//        DictFileUtil.readFile(this);
    }

    override fun onDestroy() {
        super.onDestroy()

        wtf("onDestroy")

        System.exit(0)
    }



    override fun onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            MyApplication.getInstance().isInit = false
            super.onBackPressed()
            return
        }

        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(object : Runnable {
            override fun run() {
                doubleBackToExitPressedOnce = false
            }
        }, 1000)
    }

    fun onClick(v: View) {
        var sText = et_search_vocab.text.toString()

        var selection = DictConstarct.TableVocabColumns.VOCAB + " like ?"
        val selectionArgs =arrayOf(sText+"%")

        var cusor = contentResolver.query(
                DictConstarct.VOCAB_CONTENT_URI,
                DictConstarct.PROJECTION_VOCAB,
                selection, selectionArgs, null);

        if (cusor != null && cusor.count > 0) {

            wtf("cursor size:"+cusor.count);

            cusor.moveToFirst();
            wtf(cusor.getString(cusor.getColumnIndex(DictConstarct.TableVocabColumns.VOCAB)))
            wtf(cusor.getString(cusor.getColumnIndex(DictConstarct.TableVocabColumns.DEFINITION)))

            searchResultViewAdapter.swapCursor(cusor);
        } else if (cusor.count == 0) {
            wtf("cursor size zero!!")
        }


    }
}
