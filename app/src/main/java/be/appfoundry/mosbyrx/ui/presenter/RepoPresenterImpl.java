package be.appfoundry.mosbyrx.ui.presenter;

import java.util.List;

import javax.inject.Inject;

import be.appfoundry.mosbyrx.data.entity.GitHubRepo;
import be.appfoundry.mosbyrx.data.service.GitHubAPI;
import be.appfoundry.mosbyrx.ui.view.repo.RepoView;
import be.appfoundry.mvp.mosby.BaseRxPresenter;
import be.appfoundry.mvp.mosby.retrofit.SafeCallback;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Subscriber;

/**
 * Created by janvancoppenolle on 19/06/15.
 */
public class RepoPresenterImpl
        extends BaseRxPresenter<RepoView>
        implements RepoPresenter {

    GitHubAPI gitHubAPI;

    @Inject
    public RepoPresenterImpl(GitHubAPI gitHubAPI) {
        this.gitHubAPI = gitHubAPI;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public List<GitHubRepo> loadRepoListOnMainThread() {
        /*
            Android (in StrictMode) will keep us from being silly and will
            throw an android.os.NetworkOnMainThreadException
         */
        return gitHubAPI.getReposOnMainThread();
    }

    @Override
    public void loadRepoList() {
        new RxIOSubscription<List<GitHubRepo>>().add(
                gitHubAPI.getRepos(),
                new Subscriber<List<GitHubRepo>>() {
                    @Override
                    public void onNext(List<GitHubRepo> gitHubRepos) {
                        getView().showRepos(gitHubRepos);
                    }

                    @Override
                    public void onCompleted() {
                        getView().hideLoadingIndicator();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showMessage(e.getMessage());
                        getView().hideLoadingIndicator();
                    }
                }
        );
    }

    @Override
    public void loadRepoListDangerous() {
        gitHubAPI.getRepos(new Callback<List<GitHubRepo>>() {
            @Override
            public void success(List<GitHubRepo> gitHubRepos, Response response) {
                getView().showRepos(gitHubRepos);
                getView().hideLoadingIndicator();
            }

            @Override
            public void failure(RetrofitError e) {
                getView().showMessage(e.getMessage());
                getView().hideLoadingIndicator();
            }
        });
    }

    @Override
    public void loadRepoListLessDangerous() {
        gitHubAPI.getRepos(new SafeCallback<List<GitHubRepo>>() {
            @Override
            public void safeSuccess(List<GitHubRepo> gitHubRepos, Response response) {
                getView().showRepos(gitHubRepos);
                getView().hideLoadingIndicator();
            }

            @Override
            public void safeFailure(RetrofitError e) {
                getView().showMessage(e.getMessage());
                getView().hideLoadingIndicator();
            }
        });
    }
}
