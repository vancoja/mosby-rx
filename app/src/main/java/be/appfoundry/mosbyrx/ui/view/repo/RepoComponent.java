package be.appfoundry.mosbyrx.ui.view.repo;

import be.appfoundry.mosbyrx.ActivityScoped;
import be.appfoundry.mosbyrx.ApplicationComponent;
import be.appfoundry.mosbyrx.ui.adapter.RepoListAdapter;
import be.appfoundry.mosbyrx.ui.presenter.RepoPresenterImpl;
import dagger.Component;

/**
 * Created by janvancoppenolle on 29/06/15.
 */
@ActivityScoped @Component(dependencies = ApplicationComponent.class)
public interface RepoComponent {
    RepoPresenterImpl presenter();
    RepoListAdapter adapter();
}
