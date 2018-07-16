package bm.baseapp;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

public class BaseApp extends Application {
    private static BaseApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Utils.init(this);
    }

    public static BaseApp getApp() {
        return app;
    }
}
