package be.appfoundry.mosbyrx.ui.presenter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import be.appfoundry.mosbyrx.data.entity.GitHubRepo;
import be.appfoundry.mosbyrx.ui.view.repo.RepoView;

/**
 * Created by janvancoppenolle on 13/07/15.
 */
public interface RepoPresenter
        extends MvpPresenter<RepoView> {
        List<GitHubRepo> loadRepoListOnMainThread();
        void loadRepoList();
        void loadRepoListDangerous();
        void loadRepoListLessDangerous();
        void loadRepoListSafer();
}
