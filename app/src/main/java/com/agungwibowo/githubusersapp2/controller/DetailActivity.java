package com.agungwibowo.githubusersapp2.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.agungwibowo.githubusersapp2.R;
import com.agungwibowo.githubusersapp2.adapter.GithubUserAdapter;
import com.agungwibowo.githubusersapp2.adapter.SectionPagerAdapter;
import com.agungwibowo.githubusersapp2.model.GithubUser;
import com.agungwibowo.githubusersapp2.model.GithubUserList;
import com.agungwibowo.githubusersapp2.service.GithubService;
import com.agungwibowo.githubusersapp2.service.ServiceGenerator;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private  TextView name, username, company, location, repository, follower, following;
    public static final String EXTRA_USERNAME = "extra_username";
    private static final String TAG = MainActivity.class.getSimpleName();
    private SectionPagerAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ImageView imageView = findViewById(R.id.profile_github_user);
        name = findViewById(R.id.name_github);
        username = findViewById(R.id.username);
        company = findViewById(R.id.company);
        location = findViewById(R.id.location);
        repository = findViewById(R.id.item_repositories);
        follower = findViewById(R.id.item_follower);
        following = findViewById(R.id.item_following);
        progressBar = findViewById(R.id.progressBar);

        GithubUser githubUser = getIntent().getParcelableExtra(EXTRA_USERNAME);
        String extraUsername = githubUser.getLogin();

        SectionPagerAdapter sectionsPagerAdapter = new SectionPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        showLoading(true);

        final FollowerFragment followerFragment = new FollowerFragment();
        final FollowingFragment followingFragment  = new FollowingFragment();
        FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction t = manager.beginTransaction();
        getSupportActionBar().setElevation(0);



        GithubService githubService = ServiceGenerator.build().create(GithubService.class);
        Call<GithubUser> callAsync = githubService.getUser(extraUsername);
        callAsync.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                if (response.isSuccessful()) {
                    GithubUser results = response.body();
                    name.setText(results.getName());
                    username.setText(results.getLogin());
                    company.setText(results.getCompany());
                    location.setText(results.getLocation());
                    follower.setText(results.getFollowers());
                    following.setText(results.getFollowing());
                    repository.setText(results.getPublic_repos());
                    Picasso.get().load(results.getAvatarUrl()).into(imageView);

                    Bundle bundle = new Bundle();
                    bundle.putString("username", results.getLogin());
                    followerFragment.setArguments(bundle);
                    followingFragment.setArguments(bundle);
                    t.add(R.id.follower_fragment, followerFragment);
                    t.add(R.id.following_fragment, followingFragment);
                    t.commit();
                    showLoading(false);
                } else {
                    Toast.makeText(DetailActivity.this,
                            "Request not Successful",
                            Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {
                Toast.makeText(DetailActivity.this,
                        "Request failed. Check your internet connection",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
