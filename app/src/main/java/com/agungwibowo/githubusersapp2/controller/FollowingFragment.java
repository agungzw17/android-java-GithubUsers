package com.agungwibowo.githubusersapp2.controller;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.agungwibowo.githubusersapp2.R;
import com.agungwibowo.githubusersapp2.adapter.FollowersFragmentAdapter;
import com.agungwibowo.githubusersapp2.model.GithubUser;
import com.agungwibowo.githubusersapp2.service.GithubService;
import com.agungwibowo.githubusersapp2.service.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowingFragment extends Fragment {
    private ProgressBar progressBar;
    private ListView listView;
    public FollowingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_following, container, false);
        if (getArguments() != null) {
            listView = v.findViewById(R.id.listFollowing);
            progressBar = v.findViewById(R.id.progressBar);
            showLoading(true);
            String extraUsername = getArguments().getString("username");
            System.out.println("Following Fragment username " + extraUsername);
            GithubService githubService = ServiceGenerator.build().create(GithubService.class);
            Call<List<GithubUser>> callAsync = githubService.getFollowing(extraUsername);
            callAsync.enqueue(new Callback<List<GithubUser>>() {
                @Override
                public void onResponse(Call<List<GithubUser>> call, Response<List<GithubUser>> response) {
                    if (response.isSuccessful()) {
                        List<GithubUser> results = response.body();

                        FollowersFragmentAdapter adapter = new FollowersFragmentAdapter(getActivity().getApplication(), 0, results);
                        listView.setAdapter(adapter);
                        showLoading(false);
                    }

                }

                @Override
                public void onFailure(Call<List<GithubUser>> call, Throwable t) {

                }
            });

        }

        // Inflate the layout for this fragment
        return v;
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
