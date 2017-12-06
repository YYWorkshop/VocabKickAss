package com.yyworkshop.vocabkickass.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.yyworkshop.vocabkickass.EntryActivity;
import com.yyworkshop.vocabkickass.data.DictConstarct;
import com.yyworkshop.vocabkickass.data.DictConstarct.TableDictColumns;
import com.yyworkshop.vocabkickass.data.DictConstarct.TableVocabColumns;
import com.yyworkshop.vocabkickass.model.WordModel;
import com.yyworkshop.vocabkickass.util.DictFileUtil;

import java.util.List;


public class InitializeIntentService extends IntentService {

    public static final String ACTION_INITIALIZE_DONE = "com.yyworkshop.vocabkickass.action.ACTION_INITIALIZE_DONE";
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

        List<WordModel> wordList = DictFileUtil.readFile(this);

        ContentValues[] valuesAry = new ContentValues[wordList.size()];

        for (int i = 0; i < wordList.size(); i++) {

            WordModel model = wordList.get(i);

            ContentValues values = new ContentValues();
            values.put(TableVocabColumns.DICTS_NAME, "牛津現代英漢雙解詞典");
            values.put(TableVocabColumns.DEFINITION, model.getDefinition());
            values.put(TableVocabColumns.VOCAB, model.getVocab());
            values.put(TableVocabColumns.STATUS, 0);

            valuesAry[i] = values;

//            getContentResolver().insert(DictConstarct.VOCAB_CONTENT_URI, values);
        }

        getContentResolver().bulkInsert(DictConstarct.VOCAB_CONTENT_URI, valuesAry);


        ContentValues dictValues = new ContentValues();
        dictValues.put(TableDictColumns.DICTS_NAME, "牛津現代英漢雙解詞典");
        dictValues.put(TableDictColumns.DESCRIPTION, "牛津現代英漢雙解詞典");
        dictValues.put(TableDictColumns.STATUS, 0);

        getContentResolver().insert(DictConstarct.DICT_CONTENT_URI, dictValues);


        Intent intent = new Intent(this, EntryActivity.class);
        intent.setAction(ACTION_INITIALIZE_DONE);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}
