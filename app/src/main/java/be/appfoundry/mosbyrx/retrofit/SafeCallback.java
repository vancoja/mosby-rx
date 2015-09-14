package be.appfoundry.mosbyrx.retrofit;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * As we know we're not protected from NullPointerExceptions if we handle the result
 * and the Activity is long gone, instead of avoiding the Exception, we're going to
 * catch it and pretend it never happened... Ugh...
 *
 * @author Jan Van Coppenolle
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
