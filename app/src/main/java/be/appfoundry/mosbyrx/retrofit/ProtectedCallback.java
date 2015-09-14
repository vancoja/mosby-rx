package be.appfoundry.mosbyrx.retrofit;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by janvancoppenolle on 02/09/15.
 */
interface ProtectedCallback<T> {
    void safeSuccess(T t, Response response);
    void safeFailure(RetrofitError error);
}
