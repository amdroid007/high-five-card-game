package com.iliaskomp.highfivecardgame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by IliasKomp on 20/02/17.
 */

public class GameActivity extends AppCompatActivity {
    private static final String GAME_FRAGMENT_TAG = "GameFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Fragment fragment = GameFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, GameActivity.class);
        return intent;
    }
}
