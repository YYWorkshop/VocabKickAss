package com.yyworkshop.vocabkickass;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yyworkshop.vocabkickass.data.DictConstarct;
import com.yyworkshop.vocabkickass.service.InitializeIntentService;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        startInit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void startInit() {

        String[] projection = new String[]{};

        Cursor cursor = getContentResolver().query(DictConstarct.DICT_CONTENT_URI, DictConstarct.PROJECTION_COUNT, null, null, null);

        cursor.moveToFirst();
        int count = cursor.getInt(cursor.getColumnIndex(DictConstarct.TableDictColumns._COUNT));

        Log.wtf("EntryActivity", "count =>" + count);

        if (count == 0) {
            TextView tv = findViewById(R.id.tv_init_msg);
            ProgressBar progressBar = findViewById(R.id.progressBar_init);

            tv.setText("Initializing");
            progressBar.setVisibility(View.VISIBLE);

            InitializeIntentService.startActionInitializeApp(this);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.wtf("EntryActivity", "onNewIntent =>");

    }
}
