package com.yyworkshop.vocabkickass.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yyworkshop.vocabkickass.data.DBConstarct.TableDictsColumns;

/**
 * Created by hulonelyy on 2017/12/4.
 */

public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "dicts.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_DICTS = String.format("CREATE TABLE %s"
                    +" (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",
            DBConstarct.TABLE_DICTS,
            TableDictsColumns._ID,
            TableDictsColumns.DICTS_NAME,
            TableDictsColumns.VOCAB,
            TableDictsColumns.DEFINITION,
            TableDictsColumns.STATUS
    );

    private Context context;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_DICTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DBConstarct.TABLE_DICTS);
        onCreate(db);
    }
}
