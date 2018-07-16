package bm.baseapp.http.retrofit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ac on 2017/3/25.
 */

public class RxSchedulers {
    public static <T> ObservableTransformer<T, T> io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io());
            }

        };
    }

    public static <T> ObservableTransformer<T, T> all_io() {
        return new ObservableTransformer<T, T>() {

            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
            }
        };
    }
}
