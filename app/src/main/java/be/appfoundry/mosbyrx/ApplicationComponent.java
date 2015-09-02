package be.appfoundry.mosbyrx;

import android.content.Context;

import be.appfoundry.mosbyrx.data.service.GitHubAPI;
import dagger.Component;

@ApplicationScoped @Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context context();
    GitHubAPI gitHubAPI();
}
