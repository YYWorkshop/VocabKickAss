package com.yyworkshop.vocabkickass

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.yyworkshop.vocabkickass.adapter.SearchResultRecyclerViewAdapter
import com.yyworkshop.vocabkickass.data.DictConstarct
import com.yyworkshop.vocabkickass.model.VocabModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.wtf

class TEMPMainActivity : AppCompatActivity(), AnkoLogger, SearchResultRecyclerViewAdapter.OnClickAdapterItemListener {

    private var doubleBackToExitPressedOnce: Boolean = false
    private var searchResultViewAdapter = SearchResultRecyclerViewAdapter()

    /**
     * Listener to manipulate tab selections
     */
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

        // Dictionary Tab
            R.id.navigation_dictionary -> {
//                message.setText(R.string.navi_title_dictionary)
                val myIntent = Intent(this, VocabDefinitionActivity::class.java)
//                myIntent.putExtra(VocabDefinitionActivity.EXTRA_VACAB_MODEL, null)
                startActivity(myIntent)
                wtf("Dictionary Tab")
                return@OnNavigationItemSelectedListener true
            }

        // New Words Tab
            R.id.navigation_new_words -> {
//                message.setText(R.string.navi_title_new_words)
                val myIntent = Intent(this, EntryActivity::class.java)
//                myIntent.putExtra(VocabDefinitionActivity.EXTRA_VACAB_MODEL, null)
                startActivity(myIntent)
                wtf("New Word Tab")
                return@OnNavigationItemSelectedListener true
            }

        // Settings Tab
            R.id.navigation_settings -> {
//                message.setText(R.string.navi_title_setting)
                val myIntent = Intent(this, LoginActivity::class.java)
//                myIntent.putExtra(VocabDefinitionActivity.EXTRA_VACAB_MODEL, null)
                startActivity(myIntent)
                wtf("Settings Tab")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        info("onCreate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tmp)

        // Set listener for BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        rv_search_result.adapter = searchResultViewAdapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_search_result.layoutManager = layoutManager

        searchResultViewAdapter.onClickAdapterItemListener = this

    }

    override fun onDestroy() {
        super.onDestroy()

        wtf("onDestroy")

        System.exit(0)
    }

    /**
     * Handling onBaackPressed
     */
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

        // Get context form searchEditText
        val sText = et_search_vocab.text.toString()

        val selection = DictConstarct.TableVocabColumns.VOCAB + " like ?"
        val selectionArgs = arrayOf(sText + "%")

        val cusor = contentResolver.query(
                DictConstarct.VOCAB_CONTENT_URI,
                DictConstarct.PROJECTION_VOCAB,
                selection, selectionArgs, null);

        if (cusor != null && cusor.count > 0) {

            wtf("cursor size:${cusor.count}");

            cusor.moveToFirst();
            wtf(cusor.getString(cusor.getColumnIndex(DictConstarct.TableVocabColumns.VOCAB)))
            wtf(cusor.getString(cusor.getColumnIndex(DictConstarct.TableVocabColumns.DEFINITION)))

            searchResultViewAdapter.swapCursor(cusor);
        } else if (cusor.count == 0) {
            wtf("cursor size zero!!")
        }

    }

    override fun OnClickAdapterItem(v: View?, model: VocabModel?) {

        val myIntent = Intent(this, VocabDefinitionActivity::class.java)
        myIntent.putExtra(VocabDefinitionActivity.EXTRA_VACAB_MODEL, model)
        startActivity(myIntent)

    }
}
