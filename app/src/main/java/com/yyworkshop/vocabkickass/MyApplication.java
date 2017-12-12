package com.yyworkshop.vocabkickass;

import android.app.Application;
import android.util.Log;

/**
 * Created by hulonelyy on 2017/12/6.
 */

public class MyApplication extends Application {

    private static MyApplication application;
    private boolean isInit;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.wtf("MyApplication", "onCreate: ");

        application = this;
    }

    /**
     * Singleton to get Application instance
     *
     * @return MyApplication
     */
    public static MyApplication getInstance() {
        return application;
    }

    public boolean isInit() {
        return isInit;
    }

    public void setInit(boolean init) {
        isInit = init;
    }
}
