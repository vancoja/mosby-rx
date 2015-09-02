package be.appfoundry.mvp.mosby.retrofit;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by janvancoppenolle on 02/09/15.
 */
public abstract class SafeCallback<T> implements Callback<T>, ProtectedCallback<T> {
    public SafeCallback() {
    }

    @Override
    public final void success(T t, Response response) {
        try {
            safeSuccess(t, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void failure(RetrofitError error) {
        try {
            safeFailure(error);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
