package be.appfoundry.mvp.mosby;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by janvancoppenolle on 07/08/15.
 */
public interface BaseMvpView extends MvpView {
    void showMessage(String message);
}
