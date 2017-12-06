package com.yyworkshop.vocabkickass.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class DictsContentProvider extends ContentProvider {

    private DBHelper dbHelper;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int CODE_MATCH_DICT = 100;
    private static final int CODE_MATCH_DICT_WITH_ID = 101;
    private static final int CODE_MATCH_VOCAB = 102;
    private static final int CODE_MATCH_VOCAB_WITH_ID = 103;



    public DictsContentProvider() {

    }

    @Override
    public boolean onCreate() {
        this.dbHelper = new DBHelper(getContext());
        uriMatcher.addURI(DictConstarct.CONTENT_AUTHORITY, DictConstarct.TABLE_DICT, CODE_MATCH_DICT);
        uriMatcher.addURI(DictConstarct.CONTENT_AUTHORITY, DictConstarct.TABLE_DICT+"/#", CODE_MATCH_DICT_WITH_ID);
        uriMatcher.addURI(DictConstarct.CONTENT_AUTHORITY, DictConstarct.TABLE_VOCAB, CODE_MATCH_VOCAB);
        uriMatcher.addURI(DictConstarct.CONTENT_AUTHORITY, DictConstarct.TABLE_VOCAB+"/#", CODE_MATCH_VOCAB_WITH_ID);
        return false;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

//        switch (uriMatcher.match(uri)) {
//
//            case CODE_MATCH_DICT_WITH_ID:
//                long id = ContentUris.parseId(uri);
//                selection = TableVocabColumns._ID+"=?";
//                selectionArgs = new String[]{String .valueOf(id)};
//                return db.delete(DictConstarct.TABLE_DICT, selection, selectionArgs);
//
//            case CODE_MATCH_DICT:
//                return db.delete(DictConstarct.TABLE_DICT, selection, selectionArgs);
//
//            case CODE_MATCH_VOCAB_WITH_ID:
//                long id = ContentUris.parseId(uri);
//                selection = TableVocabColumns._ID+"=?";
//                selectionArgs = new String[]{String .valueOf(id)};
//                return db.delete(DictConstarct.TABLE_DICT, selection, selectionArgs);
//
//        }
//
//        return 0;

        throw new UnsupportedOperationException("Not yet implemented");

    }

    private int delete(String table, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(table, selection, selectionArgs);
    }

    private int deleteById(String table, long id) {
        String selection = BaseColumns._ID+"=?";
        String[] selectionArgs = new String[]{String .valueOf(id)};
        return delete(table, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    @Nullable
    public Uri insert(Uri uri, ContentValues values) {

        Log.wtf("DictsContentProvider", "insert uri => "+uri);

        long id = -1;

        switch (uriMatcher.match(uri)) {

            case CODE_MATCH_DICT:
                id = insert(DictConstarct.TABLE_DICT, values);
                break;
            case CODE_MATCH_VOCAB:
                id = insert(DictConstarct.TABLE_VOCAB, values);

        }

        if (id != -1) {
            return ContentUris.withAppendedId(DictConstarct.DICT_CONTENT_URI, id);
        } else {
            return null;
        }

    }

    private long insert(String table, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.insert(table, null, values);
    }

    @Override
    @Nullable
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        switch (uriMatcher.match(uri)) {

            case CODE_MATCH_DICT:
                return query(DictConstarct.TABLE_DICT, projection, selection, selectionArgs, sortOrder);

            case CODE_MATCH_DICT_WITH_ID:
                return queryById(DictConstarct.TABLE_DICT, ContentUris.parseId(uri), projection);

            case CODE_MATCH_VOCAB:
                return query(DictConstarct.TABLE_VOCAB, projection, selection, selectionArgs, sortOrder);

            case CODE_MATCH_VOCAB_WITH_ID:
                return queryById(DictConstarct.TABLE_VOCAB, ContentUris.parseId(uri), projection);
        }

        return null;
    }

    private Cursor query(String table, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query(table, projection, selection, selectionArgs, null, null, sortOrder);
    }

    private Cursor queryById(String table, long id, String[] projection) {
        String selection = BaseColumns._ID+"=?";
        String[] selectionArgs = new String[]{String .valueOf(id)};
        return query(table, projection, selection, selectionArgs, null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        switch (uriMatcher.match(uri)) {
//
//            case CODE_MATCH_DICT_WITH_ID:
//                long id = ContentUris.parseId(uri);
//                selection = TableVocabColumns._ID+"=?";
//                selectionArgs = new String[]{String .valueOf(id)};
//                return db.update(DictConstarct.TABLE_DICT, values, selection, selectionArgs);
//            case CODE_MATCH_DICT:
//                return db.update(DictConstarct.TABLE_DICT, values, selection, selectionArgs);
//        }
//
//        return 0;

        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {

        Log.wtf("DictsContentProvider", "bulkInsert uri =>" + uri);

        switch (uriMatcher.match(uri)) {

            case CODE_MATCH_DICT:
                return bulkInsert(DictConstarct.TABLE_DICT, values);

            case CODE_MATCH_VOCAB:
                return bulkInsert(DictConstarct.TABLE_VOCAB, values);
        }

        return 0;
    }

    private int bulkInsert(String tableName, ContentValues[] values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            int count = values.length;
            for (int i = 0; i < count; i++) {
                if (db.insert(tableName, null, values[i]) < 0) {
                    return 0;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return values.length;
    }
}
