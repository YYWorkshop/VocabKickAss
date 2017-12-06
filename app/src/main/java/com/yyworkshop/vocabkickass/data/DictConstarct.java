package com.yyworkshop.vocabkickass.data;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import com.yyworkshop.vocabkickass.model.VocabModel;

/**
 * Created by hulonelyy on 2017/12/4.
 */

public class DictConstarct {

    public static final String TABLE_DICT = "dict";
    public static final String TABLE_VOCAB = "vocab";

    public static class TableDictColumns implements BaseColumns {

        public static final String DICTS_NAME = "dicts_name";
        public static final String DESCRIPTION = "description";
        public static final String STATUS = "status";

    }

    public static class TableVocabColumns implements BaseColumns {

        public static final String DICTS_NAME = "dicts_name";
        public static final String VOCAB = "vocab";
        public static final String DEFINITION = "definition";
        public static final String STATUS = "status";

    }

    public static final String CONTENT_AUTHORITY = "com.yyworkshop.vocabkickass.data.DictsContentProvider";

    public static final Uri DICT_CONTENT_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
            .authority(CONTENT_AUTHORITY)
            .appendPath(TABLE_DICT)
            .build();

    public static final Uri VOCAB_CONTENT_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
            .authority(CONTENT_AUTHORITY)
            .appendPath(TABLE_VOCAB)
            .build();


    public static String[] PROJECTION_COUNT = new String[]{"count(1) AS _count"};

    public static String[] PROJECTION_VOCAB = new String[]{
            TableVocabColumns._ID,
            TableVocabColumns.VOCAB,
            TableVocabColumns.DEFINITION,
            TableVocabColumns.STATUS,
            TableVocabColumns.DICTS_NAME
    };

    public static VocabModel getVocabModel(Cursor cursor) {

        VocabModel model = new VocabModel();
        long id = cursor.getLong(cursor.getColumnIndex(TableVocabColumns._ID));
        String vocab = cursor.getString(cursor.getColumnIndex(TableVocabColumns.VOCAB));
        String def = cursor.getString(cursor.getColumnIndex(TableVocabColumns.DEFINITION));
        int stat = cursor.getInt(cursor.getColumnIndex(TableVocabColumns.STATUS));
        String dicName = cursor.getString(cursor.getColumnIndex(TableVocabColumns.DICTS_NAME));

        model.setId(id);
        model.setVocab(vocab);
        model.setDefinition(def);
        model.setStatus(stat);
        model.setDictsName(dicName);

        return model;

    }

}
