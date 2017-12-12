package com.yyworkshop.vocabkickass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.yyworkshop.vocabkickass.data.DictConstarct;
import com.yyworkshop.vocabkickass.model.VocabModel;

public class VocabDefinitionActivity extends AppCompatActivity {

    public static final String EXTRA_VACAB_MODEL = "EXTRA_VACAB_MODEL";
    private VocabModel vocabModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_definition);

        vocabModel = getIntent().getParcelableExtra(EXTRA_VACAB_MODEL);

        TextView tvVocabTitle = findViewById(R.id.tv_vocab_title);
        TextView tvDictName = findViewById(R.id.tv_dict_name);
        TextView tvVocabDef = findViewById(R.id.tv_vocab_definition);

        tvVocabTitle.setText(vocabModel.getVocab());
        tvVocabDef.setText(vocabModel.getDefinition());
        tvDictName.setText(vocabModel.getDictsName());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vocab_definition, menu);

        MenuItem item = menu.findItem(R.id.menu_item_note_add_new_vocab);
        if (vocabModel.getStatus() == 1) {
            item.setEnabled(false);
            item.setIcon(R.drawable.ic_done_black_24dp);
        }


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_item_note_add_new_vocab:
                if (DictConstarct.addNewWord(getContentResolver(), vocabModel)) {
                    item.setIcon(R.drawable.ic_done_black_24dp);
                } else {
                    Log.wtf("VocabDefinitionActivity", "onOptionsItemSelected: add note fail");
                }
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
