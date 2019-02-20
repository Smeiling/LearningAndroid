package com.smeiling.learning.retrofit.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * @Author: Smeiling
 * @Date: 2019-01-08 16-41
 * @Description:
 */
public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Object>> listRepos(@Path("user") String user);

    @GET
    Call<Object> getTime(@Url String url);
}
