package be.appfoundry.mvp.mosby;

import java.util.concurrent.Callable;

import be.appfoundry.rx.SimpleRxUtil;
import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Use:
 * <code>new RxIOSubscription&lt;ResultType@gt;.add(
 *      Observable&lt;ResultType@gt;,
 *      Subscriber&lt;ResultType@gt;
 * );</code>
 *
 * This will make sure a couple of things happen:
 * - subscribe on Schedulers.io()
 * - observe on AndroidSchedulers.mainThread()
 * - after scheduling that observable is subscribed to and added to a CompositeSubscription (1)
 * - detachView is called when the Activity is destroyed, which unsubscribes all
 *
 * This implementation differs a wee bit from mosby.rx.MvpRxPresenter (2)
 *
 * Notes:
 * (1) http://blog.danlew.net/2014/10/08/grokking-rxjava-part-4/
 * (2) https://github.com/sockeqwe/mosby/blob/master/rx/src/main/java/com/hannesdorfmann/mosby/mvp/rx/MvpRxPresenter.java
 *
 * @author Jan Van Coppenolle
 */
public abstract class BaseRxPresenter<V extends BaseMvpView> extends BasePresenter<V> {
    CompositeSubscription subscriptions;

    @Override
    public void attachView(V view) {
        super.attachView(view);
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            Timber.i("Detaching View & Unsubscribing");
            subscriptions.unsubscribe();
        }
    }

    protected class RxIOSubscription<T> extends RxSubscription<T> {
       public RxIOSubscription() {
            super(new SimpleRxUtil.IOTransformer<T>());
        }
    }

    private class RxSubscription<T> {
        Observable.Transformer<T, T> transformer;

        public RxSubscription(Observable.Transformer<T, T> transformer) {
            this.transformer = transformer;
        }

        public RxSubscription<T> add(Observable<T> observable, Subscriber<T> subscriber) {
            observable = observable.compose(transformer);
            BaseRxPresenter.this.subscriptions.add(observable.subscribe(subscriber));
            return this;
        }
    }
}
