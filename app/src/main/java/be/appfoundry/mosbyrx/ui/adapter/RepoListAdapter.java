package be.appfoundry.mosbyrx.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import be.appfoundry.mosbyrx.R;
import be.appfoundry.mosbyrx.data.entity.GitHubRepo;
import butterknife.ButterKnife;
import butterknife.Bind;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoViewHolder> {

    public static class RepoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.repo_name) TextView repoName;
        @Bind(R.id.repo_description) TextView repoDescription;
        @Bind(R.id.repo_url) TextView repoUrl;

        public RepoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(GitHubRepo repo) {
            repoName.setText(repo.getName());
            repoDescription.setText(repo.getDescription());
            repoUrl.setText(repo.getUrl());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    List<GitHubRepo> repos;

    @Inject
    public RepoListAdapter() {
        super();
    }

    public void setRepos(List<GitHubRepo> gitHubRepos) {
        this.repos = gitHubRepos;
        notifyDataSetChanged();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getItemCount() {
        return repos == null ? 0 : repos.size();
    }

    @Override
    public void onBindViewHolder(RepoViewHolder viewHolder, int i) {
        viewHolder.bind(repos.get(i));
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.view_repo_row, viewGroup, false);
        return new RepoViewHolder(itemView);
    }
}
