package com.kirdmt.songbank;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class SongActivity extends AppCompatActivity {


    String songText;
    String songName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        Bundle extras = getIntent().getExtras();

        songText = extras.getString("songText");
        songName = extras.getString("songName");

        TextView TestView = (TextView) findViewById(R.id.TextView_field_1);
        if (songName.length() >= 32) {
            songName = songName.substring(0, 30) + "...";
        }

        TestView.setText(songName);


        TextView TestView2 = (TextView) findViewById(R.id.TextView_field_2);
        TestView2.setText(songText);
        TestView2.setMovementMethod(new ScrollingMovementMethod());


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

            case R.id.menu_info:

                AlertDialog.Builder builder = new AlertDialog.Builder(SongActivity.this);
                builder.setTitle("О приложении: 1.8 версия.")
                        .setMessage(getResources().getString(R.string.about))
                        .setCancelable(true)
                        .setNegativeButton("Написать письмо",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                                        emailIntent.setData(Uri.parse("mailto:LLirikNN@gmail.com"));
                                        startActivity(emailIntent);
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
