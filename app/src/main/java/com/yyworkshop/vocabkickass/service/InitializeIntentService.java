package com.yyworkshop.vocabkickass.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.yyworkshop.vocabkickass.EntryActivity;
import com.yyworkshop.vocabkickass.data.DictConstarct;
import com.yyworkshop.vocabkickass.data.DictConstarct.TableDictColumns;


public class InitializeIntentService extends IntentService {

    private static final String ACTION_INITIALIZE_APP = "com.yyworkshop.vocabkickass.action.ACTION_INITIALIZE_APP";

    private static final String EXTRA_PARAM1 = "com.yyworkshop.vocabkickass.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.yyworkshop.vocabkickass.extra.PARAM2";

    public InitializeIntentService() {
        super("InitializeIntentService");
    }


    public static void startActionInitializeApp(Context context) {
        Intent intent = new Intent(context, InitializeIntentService.class);
        intent.setAction(ACTION_INITIALIZE_APP);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.wtf("InitializeIntentService", "onHandleIntent => " + intent.getAction());

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INITIALIZE_APP.equals(action)) {
                handleActionInitializeApp();
            }
        }
    }

    private void handleActionInitializeApp() {

        ContentValues values = new ContentValues();
        values.put(TableDictColumns.DICTS_NAME, "牛津現代英漢雙解詞典");
        values.put(TableDictColumns.DESCRIPTION, "牛津現代英漢雙解詞典");
        values.put(TableDictColumns.STATUS, 0);

        getContentResolver().insert(DictConstarct.DICT_CONTENT_URI, values);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, EntryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}
