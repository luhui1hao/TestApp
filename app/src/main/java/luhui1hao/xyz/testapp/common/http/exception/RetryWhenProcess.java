package luhui1hao.xyz.testapp.common.http.exception;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;


/**
 * Created by shaoyang on 2018/1/19.
 */

public class RetryWhenProcess implements Function<Observable<? extends Throwable>, Observable<?>> {

    private long mInterval;

    public RetryWhenProcess(long interval) {

        mInterval = interval;
    }

    @Override
    public Observable<?> apply(Observable<? extends Throwable> observable) throws Exception {
        return observable.flatMap(new Function<Throwable, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                if (throwable instanceof UnknownHostException) {
                    return Observable.error(throwable);
                }
                return Observable.just(throwable).zipWith(Observable.range(1, 5), new BiFunction<Throwable, Integer, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable, Integer integer) throws Exception {
                        return integer;
                    }
                }).flatMap(new Function<Integer, Observable<? extends Long>>() {
                    @Override
                    public Observable<? extends Long> apply(Integer retryCount) throws Exception {
                        return Observable.timer(mInterval, TimeUnit.SECONDS);
                    }
                });
            }
        });
    }
}