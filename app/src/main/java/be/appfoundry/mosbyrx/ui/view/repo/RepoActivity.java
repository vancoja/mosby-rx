package be.appfoundry.mosbyrx.ui.view.repo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;

import java.util.List;

import be.appfoundry.mosbyrx.R;
import be.appfoundry.mosbyrx.core.AndroidApplication;
import be.appfoundry.mosbyrx.core.mvp.BaseActivity;
import be.appfoundry.mosbyrx.data.domain.GitHubRepo;
import be.appfoundry.mosbyrx.ui.adapter.RepoListAdapter;
import be.appfoundry.mosbyrx.ui.presenter.RepoPresenter;
import butterknife.InjectView;

public class RepoActivity
        extends BaseActivity<RepoView, RepoPresenter>
        implements RepoView, SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.recyclerView) RecyclerView recyclerView;
    @InjectView(R.id.contentView) SwipeRefreshLayout contentView;

    RepoComponent component;
    RepoListAdapter adapter;

    @Override
    protected void injectDependencies() {
        component = DaggerRepoComponent.builder()
                .applicationComponent(((AndroidApplication) getApplication()).getComponent())
                .build();
    }

    @Override
    public RepoPresenter createPresenter() {
        return component.presenter();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        setSupportActionBar(toolbar);

        adapter = component.adapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // see http://stackoverflow.com/questions/26858692/swiperefreshlayout-setrefreshing-not-showing-indicator-initially
        int offset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
        contentView.setProgressViewOffset(false, 0, offset);
        contentView.setOnRefreshListener(this);

        loadData(false);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // RepoView impl

    @Override
    public void showRepos(List<GitHubRepo> gitHubRepos) {
        adapter.setRepos(gitHubRepos);
        contentView.setRefreshing(false);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // SwipeRefreshLayout.OnRefreshListener impl

    @Override
    public void onRefresh() {
        loadData(true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    void loadData(boolean isRefresh) {
        getPresenter().loadRepos(isRefresh);
        if (!isRefresh) {
            contentView.setRefreshing(true);
        }
    }
}