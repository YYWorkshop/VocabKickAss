package com.yyworkshop.vocabkickass.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.yyworkshop.vocabkickass.data.DBConstarct.TableDictsColumns;

public class DictsContentProvider extends ContentProvider {

    private DBHelper dbHelper;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int CODE_MATCH_DICTS = 100;
    private static final int CODE_MATCH_DICTS_WITH_ID = 101;


    public DictsContentProvider() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {

            case CODE_MATCH_DICTS_WITH_ID:
                long id = ContentUris.parseId(uri);
                selection = TableDictsColumns._ID+"=?";
                selectionArgs = new String[]{String .valueOf(id)};
                return db.delete(DBConstarct.TABLE_DICTS, selection, selectionArgs);

            case CODE_MATCH_DICTS:
                return db.delete(DBConstarct.TABLE_DICTS, selection, selectionArgs);

        }

        return 0;

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

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = -1;

        switch (uriMatcher.match(uri)) {

            case CODE_MATCH_DICTS:
                id = db.insert(DBConstarct.TABLE_DICTS, null, values);
                break;

        }

        if (id != -1) {
            return ContentUris.withAppendedId(DBConstarct.DICTS_CONTENT_URI, id);
        } else {
            return null;
        }

    }

    @Override
    public boolean onCreate() {
        this.dbHelper = new DBHelper(getContext());
        uriMatcher.addURI(DBConstarct.CONTENT_AUTHORITY, DBConstarct.TABLE_DICTS, CODE_MATCH_DICTS);
        uriMatcher.addURI(DBConstarct.CONTENT_AUTHORITY, DBConstarct.TABLE_DICTS+"/#", CODE_MATCH_DICTS_WITH_ID);
        return false;
    }

    @Override
    @Nullable
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        switch (uriMatcher.match(uri)) {

            case CODE_MATCH_DICTS:
                return db.query(DBConstarct.TABLE_DICTS, projection, selection, selectionArgs, null, null, sortOrder);

            case CODE_MATCH_DICTS_WITH_ID:
                long id = ContentUris.parseId(uri);
                selection = TableDictsColumns._ID+"=?";
                selectionArgs = new String[]{String .valueOf(id)};
                return db.query(DBConstarct.TABLE_DICTS,projection, selection, selectionArgs, null, null, sortOrder);
        }

        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {

            case CODE_MATCH_DICTS_WITH_ID:
                long id = ContentUris.parseId(uri);
                selection = TableDictsColumns._ID+"=?";
                selectionArgs = new String[]{String .valueOf(id)};
                return db.update(DBConstarct.TABLE_DICTS, values, selection, selectionArgs);
            case CODE_MATCH_DICTS:
                return db.update(DBConstarct.TABLE_DICTS, values, selection, selectionArgs);
        }

        return 0;
    }
}
