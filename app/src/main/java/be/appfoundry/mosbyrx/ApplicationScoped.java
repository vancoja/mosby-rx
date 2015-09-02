package be.appfoundry.mosbyrx;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by janvancoppenolle on 08/07/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface ApplicationScoped {
}
