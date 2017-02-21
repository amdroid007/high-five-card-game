package com.iliaskomp.highfivecardgame.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.iliaskomp.highfivecardgame.R;

//TODO settings activity: time per card. random/default, show description?
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStartGame = (Button) findViewById(R.id.button_start_game);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(GameActivity.newIntent(getApplicationContext()));
            }
        });

        Button buttonSettings = (Button) findViewById(R.id.button_settings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(SettingsActivity.newIntent(getApplicationContext()));
            }
        });
    }
}
