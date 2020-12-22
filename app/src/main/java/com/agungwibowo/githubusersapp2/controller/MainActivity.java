package com.agungwibowo.githubusersapp2.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.agungwibowo.githubusersapp2.R;
import com.agungwibowo.githubusersapp2.adapter.GithubUserAdapter;
import com.agungwibowo.githubusersapp2.model.GithubUserList;
import com.agungwibowo.githubusersapp2.service.GithubService;
import com.agungwibowo.githubusersapp2.service.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private GithubUserAdapter adapter;
    private RecyclerView rvGithubUser;
    private EditText edtUsername;
    private ProgressBar progressBar;
    private Button btnUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsername = findViewById(R.id.editUsername);
        progressBar = findViewById(R.id.progressBar);
        btnUsername = findViewById(R.id.btnUsername);

        btnUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                System.out.println("NAMANYAAAA" + username);
                rvGithubUser = findViewById(R.id.rv_github_user);
                rvGithubUser.setHasFixedSize(true);
                progressBar = findViewById(R.id.progressBar);

                GithubService githubService = ServiceGenerator.build().create(GithubService.class);
                Call<GithubUserList> callAsync = githubService.getUserList(username);
                showLoading(true);
                callAsync.enqueue(new Callback<GithubUserList>() {
                    @Override
                    public void onResponse(Call<GithubUserList> call, Response<GithubUserList> response) {
                        if (response.isSuccessful()) {
                            GithubUserList userList = response.body();
                            prepareData(userList);
                            showLoading(false);
                        } else {

                            Toast.makeText(MainActivity.this,
                                    "Request not Successful",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubUserList> call, Throwable t) {
                        Toast.makeText(MainActivity.this,
                                "Request failed. Check your internet connection",
                                Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


    }

    private void prepareData(GithubUserList githubUser) {
        GithubUserAdapter adapter = new GithubUserAdapter(getApplicationContext(), githubUser.getItems());
        rvGithubUser.setAdapter(adapter);

    }


    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_setting:
                Intent moveIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(moveIntent);
                break;
        }
    }
}
