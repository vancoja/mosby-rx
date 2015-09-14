package be.appfoundry.mvp.mosby;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import be.appfoundry.mosbyrx.R;
import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by janvancoppenolle on 25/06/15.
 */
public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends MvpActivity<V, P>
        implements BaseMvpView {
    protected abstract void createComponent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        createComponent();
        Icepick.restoreInstanceState(this, savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getDelegate().setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

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
