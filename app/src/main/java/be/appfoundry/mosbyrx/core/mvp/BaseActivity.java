package be.appfoundry.mosbyrx.core.mvp;

import android.content.pm.ActivityInfo;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import be.appfoundry.mosbyrx.R;

/**
 * Created by janvancoppenolle on 25/06/15.
 */
public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends MvpActivity<V, P>
        implements BaseMvpView {
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void showMessage(String message) {
        View container = findViewById(R.id.container);
        if (container != null) {
            Snackbar.make(container, message, Snackbar.LENGTH_LONG).show();
        }
    }
}
