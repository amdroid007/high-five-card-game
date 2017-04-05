package com.iliaskomp.highfivecardgame.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.iliaskomp.highfivecardgame.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getSupportActionBar().setTitle(R.string.action_bar_title_info);

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, InfoActivity.class);
    }
}
