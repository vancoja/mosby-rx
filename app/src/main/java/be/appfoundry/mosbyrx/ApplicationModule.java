package be.appfoundry.mosbyrx;

import android.app.Application;
import android.content.Context;

import be.appfoundry.mosbyrx.data.service.GitHubAPI;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@Module
public class ApplicationModule {
    final Application application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    public Context provideContext() {
        return application;
    }

    @Provides
    public GitHubAPI provideGitHubAPI() {
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(GitHubAPI.URI).build();
        return adapter.create(GitHubAPI.class);
    }
}

