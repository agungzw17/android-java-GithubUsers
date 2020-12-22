package com.agungwibowo.githubusersapp2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubUserList {
    @SerializedName("items")
    @Expose
    private List<GithubUser> items = null;

    public List<GithubUser> getItems() {
        return items;
    }

    public void setItems(List<GithubUser> items) {
        this.items = items;
    }
}
