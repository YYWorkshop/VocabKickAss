package com.yyworkshop.vocabkickass.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

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


}
