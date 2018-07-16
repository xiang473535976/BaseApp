package bm.baseapp.util;


import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ac on 2017/3/27.
 */

public class RxUtil {
    /**
     * 执行io线程  取消注册在io   回掉主线程
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> io_main() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 执行io线程  取消注册在io   回掉主线程   没有自动重试
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> io_main_2retry() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(2, 20000))
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * @param <T>
     * @return 重试次数
     */
    public static <T> ObservableTransformer<T, T> retry() {
        return upstream -> upstream.retryWhen(new RetryWithDelay(2, 20000));

    }


    /**
     * 都在io线程
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> all_io() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(2, 2000))
                .observeOn(Schedulers.io());
    }
}
