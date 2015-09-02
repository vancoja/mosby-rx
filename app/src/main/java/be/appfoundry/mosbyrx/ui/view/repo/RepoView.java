package be.appfoundry.mosbyrx.ui.view.repo;

import java.util.List;

import be.appfoundry.mvp.mosby.BaseMvpView;
import be.appfoundry.mosbyrx.data.entity.GitHubRepo;

/**
 * Created by janvancoppenolle on 13/07/15.
 */
public interface RepoView extends BaseMvpView {
    void showRepos(List<GitHubRepo> gitHubRepos);
    void hideLoadingIndicator();
}
