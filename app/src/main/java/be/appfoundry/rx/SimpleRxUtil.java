package be.appfoundry.rx;

import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by janvancoppenolle on 25/08/15.
 */
public class SimpleRxUtil {

    // from http://beust.com/weblog/2015/06/01/easy-sqlite-on-android-with-rxjava/
    public static <T> Observable<T> makeObservable(final Callable<T> func) {
        return Observable.create(
                new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        try {
                            subscriber.onNext(func.call());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public static class IOTransformer<T> implements Observable.Transformer<T, T> {
        @Override public Observable<T> call(Observable<T> observable) {
            return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    }
}
