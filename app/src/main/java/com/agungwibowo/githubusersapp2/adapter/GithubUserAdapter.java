package com.agungwibowo.githubusersapp2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agungwibowo.githubusersapp2.R;
import com.agungwibowo.githubusersapp2.controller.DetailActivity;
import com.agungwibowo.githubusersapp2.model.GithubUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.GithubUserViewHolder>{
    private Context context;
    private List<GithubUser> listGithubUser;

    public GithubUserAdapter(@NonNull Context context,List<GithubUser> data) {
        this.context = context;
        this.listGithubUser = data;
    }

    public List<GithubUser> getListGithubUser() {
        return listGithubUser;
    }

    public void setListGithubUser(ArrayList<GithubUser> listGithubUser) {
        this.listGithubUser = listGithubUser;
    }

    @NonNull
    @Override
    public GithubUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_github_users,parent,  false);
        return new GithubUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GithubUserAdapter.GithubUserViewHolder holder, final int position) {
        GithubUser githubUser = listGithubUser.get(position);

        Picasso.get().load(githubUser.getAvatarUrl()).into(holder.imageViewUser);
        holder.textViewName.setText(githubUser.getLogin());
        holder.textViewBio.setText(githubUser.getHtmlUrl());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailActivity = new Intent(context, DetailActivity.class);
                detailActivity.putExtra(DetailActivity.EXTRA_USERNAME, listGithubUser.get(position));
                context.startActivity(detailActivity);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (getListGithubUser() != null) {
            return getListGithubUser().size();
        } else {
            System.out.println("X-RateLimit-Limit empty");
            return 0;
        }
    }


    public class GithubUserViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewUser;
        TextView textViewBio;
        TextView textViewName;
        RelativeLayout relativeLayout;

        public GithubUserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.txt_name);
            textViewBio = itemView.findViewById(R.id.txt_bio);
            imageViewUser = itemView.findViewById(R.id.author_image);
            relativeLayout = itemView.findViewById(R.id.card_view);
        }
    }
}
