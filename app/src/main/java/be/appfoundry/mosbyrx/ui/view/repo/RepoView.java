package be.appfoundry.mosbyrx.ui.view.repo;

import java.util.List;

import be.appfoundry.mosbyrx.core.mvp.BaseMvpView;
import be.appfoundry.mosbyrx.data.domain.GitHubRepo;

/**
 * Created by janvancoppenolle on 13/07/15.
 */
public interface RepoView extends BaseMvpView {
    void showRepos(List<GitHubRepo> gitHubRepos);
}
