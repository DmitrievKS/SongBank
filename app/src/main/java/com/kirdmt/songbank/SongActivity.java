package com.kirdmt.songbank;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SongActivity extends AppCompatActivity {

    private String songText;
    private String songName;
    private TextView songNameTextView, songTextTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        Bundle extras = getIntent().getExtras();

        songText = extras.getString("songText");
        songName = extras.getString("songName");

        setUpToolbar();

        songNameTextView = (TextView) findViewById(R.id.songName_TextView);
        songNameTextView.setText(songName);
        songTextTextView = (TextView) findViewById(R.id.songText_TextView);
        songTextTextView.setText(songText);
        songTextTextView.setMovementMethod(new ScrollingMovementMethod());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_song, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_back:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpToolbar() {

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
    }

}
