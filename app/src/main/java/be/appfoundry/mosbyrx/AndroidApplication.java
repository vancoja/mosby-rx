package be.appfoundry.mosbyrx;

import android.app.Application;

import be.appfoundry.mosbyrx.DaggerApplicationComponent;
import timber.log.Timber;


public class AndroidApplication extends Application {

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}

