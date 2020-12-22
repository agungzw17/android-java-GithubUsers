package com.agungwibowo.githubusersapp2.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.agungwibowo.githubusersapp2.R;

public class SettingsActivity extends AppCompatActivity {
    private TextView txtLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        txtLanguage = findViewById(R.id.language);
        txtLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setLanguange = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(setLanguange);
            }
        });
    }
}
