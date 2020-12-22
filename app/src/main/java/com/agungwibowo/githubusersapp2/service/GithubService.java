package com.agungwibowo.githubusersapp2.service;

import com.agungwibowo.githubusersapp2.model.GithubUser;
import com.agungwibowo.githubusersapp2.model.GithubUserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {
    @GET("/users/{username}")
    @Headers("Authorization: token d530a4b7749f868240ef2ddd609a24ae1f2768d2")
    public Call<GithubUser> getUser(@Path("username") String username);

    @GET("/search/users")
    @Headers("Authorization: token d530a4b7749f868240ef2ddd609a24ae1f2768d2")
    public Call<GithubUserList> getUserList(@Query("q") String filter);

    @GET("/users/{username}/followers")
    @Headers("Authorization: token d530a4b7749f868240ef2ddd609a24ae1f2768d2")
    public Call<List<GithubUser>> getFollowers(@Path("username") String followers);

    @GET("/users/{username}/following")
    @Headers("Authorization: token d530a4b7749f868240ef2ddd609a24ae1f2768d2")
    public Call<List<GithubUser>> getFollowing(@Path("username") String followers);
}
