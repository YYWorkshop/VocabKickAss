package com.yyworkshop.vocabkickass.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yyworkshop.vocabkickass.data.DictConstarct.TableDictColumns;
import com.yyworkshop.vocabkickass.data.DictConstarct.TableVocabColumns;

/**
 * Created by hulonelyy on 2017/12/4.
 */

public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "dicts.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_DICT = String.format("CREATE TABLE %s"
                    +" (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s INTEGER)",
            DictConstarct.TABLE_DICT,
            TableDictColumns._ID,
            TableDictColumns.DICTS_NAME,
            TableDictColumns.DESCRIPTION,
            TableDictColumns.STATUS
    );

    private static final String SQL_CREATE_TABLE_VOCAB = String.format("CREATE TABLE %s"
                    +" (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",
            DictConstarct.TABLE_VOCAB,
            TableVocabColumns._ID,
            TableVocabColumns.DICTS_NAME,
            TableVocabColumns.VOCAB,
            TableVocabColumns.DEFINITION,
            TableVocabColumns.STATUS
    );

    private Context context;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_DICT);
        db.execSQL(SQL_CREATE_TABLE_VOCAB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DictConstarct.TABLE_DICT);
        db.execSQL("DROP TABLE IF EXISTS " + DictConstarct.TABLE_VOCAB);
        onCreate(db);
    }
}
