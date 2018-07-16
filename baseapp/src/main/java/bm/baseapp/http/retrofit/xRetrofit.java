package bm.baseapp.http.retrofit;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author XJW
 * Created at 2016/10/11 11:20
 * Update at 2016/10/11 11:20
 * Update people:
 * Version:1.0
 * 说明：retrofit的简单封装 post和get
 */

public class xRetrofit {
    public static String BASE_URL = "";//基本的访问防止
    private static Retrofit.Builder builder;
    private static Application app;

//    /**
//     * @return 懒汉模式  返回实例
//     */
//    public static xRetrofit getInstance() {
//        if (null == instance)
//            synchronized (xRetrofit.class) {
//                if (null == instance)
//                    instance = new xRetrofit();
//            }
//        return instance;
//    }

    /**
     * @param application app整个应用的单例
     * @param base_URL    基本url
     */
    public static void init(Application application, String base_URL) {
        app = application;
        BASE_URL = base_URL;
    }

    private static Retrofit retrofit;

    /***
     * 得到Retrofit的实体
     *
     * @return
     */

    public static Retrofit getInstance() {

        if (retrofit == null)
            //初始化Retrofit
            builder = new Retrofit.Builder();
        retrofit = builder
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //增加返回值为String的支持
                .addConverterFactory(GsonConverterFactory.create())
                .client(getBaseOKhttpClient())   //添加自定义的okhttp客户端
                .build();
        return retrofit;
    }

    /***
     * 初始化OkHttpClient
     *
     * @return
     */
    private static OkHttpClient getBaseOKhttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(2000, TimeUnit.MILLISECONDS)//设置读取超时时间
                .writeTimeout(2000, TimeUnit.MILLISECONDS);//设置写入超时时间
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(app.getExternalCacheDir(), cacheSize);
        builder.cache(cache);
        builder.retryOnConnectionFailure(true);// 方法为设置出现错误进行重新连接。
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        OkHttpClient mOkHttpClient = builder.build();
        return mOkHttpClient;

    }

}
