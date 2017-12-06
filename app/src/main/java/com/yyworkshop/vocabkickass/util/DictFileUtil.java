package com.yyworkshop.vocabkickass.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yyworkshop.vocabkickass.model.WordModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * Created by hulonelyy on 2017/12/3.
 */

public class DictFileUtil {

    public static final String GZIP_ENCODE_UTF_8 = "UTF-8";
    private static final Gson gson = new Gson();

    public static List<WordModel> readFile(Context context) {

        String[] files = new String[0];
        try {
            files = context.getAssets().list("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < files.length; i++) {
            Log.wtf("DictFileUtil", "readFile: " + files[i]);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            InputStream is = context.getAssets().open("oxford-big5.json.gzip");

//            InputStream is = context.getResources().getAssets().open("oxford-big5.json.gzip");

//            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
//            while ((len = is.read(buffer)) != -1) {
//                outSteam.write(buffer, 0, len);
//            }
//
//            outSteam.close();
//            is.close();
//
//            byte[] dataAry = outSteam.toByteArray();
//
//            GZIPInputStream ungzip = new GZIPInputStream(new ByteArrayInputStream(dataAry));

            GZIPInputStream ungzip = new GZIPInputStream(is);

            while ((len = ungzip.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

            String json = out.toString(GZIP_ENCODE_UTF_8);

            Type listType = new TypeToken<ArrayList<WordModel>>(){}.getType();
            List<WordModel> wordModelList =  gson.fromJson(json, listType);

            WordModel wordModel = wordModelList.get(1000);

            Log.wtf("DictFileUtil", "readFile: " + wordModel.getVocab());
            Log.wtf("DictFileUtil", "readFile: " + wordModel.getDefinition());

            return wordModelList;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
