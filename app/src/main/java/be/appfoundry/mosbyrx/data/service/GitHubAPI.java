package be.appfoundry.mosbyrx.data.service;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import rx.Observable;
import be.appfoundry.mosbyrx.data.entity.GitHubRepo;

/**
 * Created by janvancoppenolle on 31/08/15.
 */
public interface GitHubAPI {
    String URI = "https://api.github.com";

    @GET("/users/google/repos")
    List<GitHubRepo> getReposSync();

    @GET("/users/google/repos")
    void getReposAsync(Callback<List<GitHubRepo>> result);

    @GET("/users/google/repos")
    Observable<List<GitHubRepo>> getReposRx();
}
