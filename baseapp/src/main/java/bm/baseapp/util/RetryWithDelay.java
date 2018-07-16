package bm.baseapp.util;

import com.blankj.utilcode.util.LogUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Contract->Model->Presenter->Activity->Module->Component
 * author 艾晨
 * Created at 2017/5/21 10:10
 * Update at 2017/5/21 10:10
 * Update people:
 * Version:1.0
 * 说明：  //总共重试3次，重试间隔3000毫秒  .retryWhen(new RetryWithDelay(3, 3000))
 */


public class RetryWithDelay implements Function<Observable<? extends Throwable>, Observable<?>> {

    private final int maxRetries;
    private final int retryDelayMillis;
    private int retryCount;

    public RetryWithDelay(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }

    @Override
    public Observable<?> apply(Observable<? extends Throwable> attempts) {
        return attempts
                .flatMap(new Function<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> apply(Throwable throwable) {
                        if (++retryCount <= maxRetries) {
                            // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                            LogUtils.e("", "get error, it will try after " + retryDelayMillis
                                    + " millisecond, retry count " + retryCount);
                            return Observable.timer(retryDelayMillis,
                                    TimeUnit.MILLISECONDS);
                        }
                        // Max retries hit. Just pass the error along.
                        return Observable.error(throwable);
                    }
                });
    }

}