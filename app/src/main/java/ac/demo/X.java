package ac.demo;

import bm.baseapp.BaseApp;
import bm.baseapp.http.retrofit.xRetrofit;

/**
 * Created by ac on 2017/3/25.
 */

public class X {
    private static AppService appinstance;

    public static AppService getAppService() {
        if (null == appinstance)
            synchronized (X.class) {
                if (null == appinstance) {
                    xRetrofit.init(BaseApp.getApp(), "基本地址");
                    appinstance = xRetrofit.getInstance().create(AppService.class);
                }
            }
        return appinstance;
    }
}
