package com.agungwibowo.githubusersapp2.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    public static Retrofit build() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(Base.GITHUB_BASE_URL);
        //converter
        builder.addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        return retrofit;
    }
}
