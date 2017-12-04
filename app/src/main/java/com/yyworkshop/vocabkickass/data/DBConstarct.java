package com.yyworkshop.vocabkickass.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by hulonelyy on 2017/12/4.
 */

public class DBConstarct {

    public static final String TABLE_DICTS = "dicts";

    public static class TableDictsColumns implements BaseColumns {

        public static final String DICTS_NAME = "dicts_name";
        public static final String VOCAB = "vocab";
        public static final String DEFINITION = "definition";
        public static final int STATUS = 0;

    }

    public static final String CONTENT_AUTHORITY = "com.yyworkshop.vocabkickass.data.DictsContentProvider";

    public static final Uri DICTS_CONTENT_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
            .authority(CONTENT_AUTHORITY)
            .appendPath(TABLE_DICTS)
            .build();


}
