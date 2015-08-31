package be.appfoundry.mosbyrx.data.service;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;
import be.appfoundry.mosbyrx.data.domain.GitHubRepo;

/**
 * Created by janvancoppenolle on 31/08/15.
 */
public interface GitHubAPI {
    public static final String URI = "https://api.github.com";

    @GET("/users/google/repos")
    Observable<List<GitHubRepo>> getRepos();
}
