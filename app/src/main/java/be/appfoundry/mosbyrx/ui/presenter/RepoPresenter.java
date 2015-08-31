package be.appfoundry.mosbyrx.ui.presenter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import be.appfoundry.mosbyrx.ui.view.repo.RepoView;

/**
 * Created by janvancoppenolle on 13/07/15.
 */
public interface RepoPresenter
        extends MvpPresenter<RepoView> {
        void loadRepos(boolean isRefresh);
}
