package be.appfoundry.mosbyrx.ui.presenter;

import java.util.List;

import javax.inject.Inject;

import be.appfoundry.mosbyrx.core.mvp.BaseRxPresenter;
import be.appfoundry.mosbyrx.data.domain.GitHubRepo;
import be.appfoundry.mosbyrx.data.service.GitHubAPI;
import be.appfoundry.mosbyrx.ui.view.repo.RepoView;
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


    @Override
    public void loadRepos(boolean isRefresh) {
        new RxIOSubscription<List<GitHubRepo>>().add(
                gitHubAPI.getRepos(),
                new Subscriber<List<GitHubRepo>>() {
                    @Override
                    public void onCompleted() {
                        getView().showMessage("Success");
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showMessage("Error");
                    }

                    @Override
                    public void onNext(List<GitHubRepo> gitHubRepos) {
                        getView().showRepos(gitHubRepos);
                    }
                }
        );
    }
}
