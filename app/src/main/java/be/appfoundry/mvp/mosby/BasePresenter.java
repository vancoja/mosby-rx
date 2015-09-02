package be.appfoundry.mvp.mosby;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by janvancoppenolle on 25/06/15.
 */
public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    V view;

    public V getView() {
        return view;
    }

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView(boolean retainInstance) {
        this.view = null;
    }
}
