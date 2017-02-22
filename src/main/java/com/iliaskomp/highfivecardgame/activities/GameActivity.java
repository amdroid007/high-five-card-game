package com.iliaskomp.highfivecardgame.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.iliaskomp.highfivecardgame.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        getSupportActionBar().setTitle(R.string.action_bar_title_game);

        Fragment fragment = GameFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, GameActivity.class);
    }
}
