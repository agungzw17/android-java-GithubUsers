package com.agungwibowo.githubusersapp2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.agungwibowo.githubusersapp2.R;
import com.agungwibowo.githubusersapp2.model.GithubUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FollowingFragmentAdapter extends ArrayAdapter<GithubUser> {

    private Context context;
    private List<GithubUser> data;

    public FollowingFragmentAdapter(@NonNull Context context, int resource, @NonNull List<GithubUser> data) {
        super(context, resource, data);

        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        GithubUser gu = data.get(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.item_github_following, parent, false);

        TextView textUserName = (TextView) convertView.findViewById(R.id.txt_username);
        ImageView imageViewGithubUser = (ImageView) convertView.findViewById(R.id.author_image);

        Picasso.get().load(gu.getAvatarUrl()).into(imageViewGithubUser);
        textUserName.setText(gu.getLogin());
        return convertView;
    }

}
