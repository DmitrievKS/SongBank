package com.kirdmt.songbank.legacy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.kirdmt.songbank.R;


public class SongActivityLegacy extends AppCompatActivity {

/*
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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_song);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               *//* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*//*
                AlertDialog.Builder builder = new AlertDialog.Builder(SongActivity.this);
                builder.setTitle("О приложении:")
                        .setMessage(getResources().getString(R.string.about))
                        // .setIcon(R.drawable.ic_android_cat)
                        .setCancelable(true)
                        .setNegativeButton("Написать письмо",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  dialog.cancel();

                                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                                        emailIntent.setData(Uri.parse("mailto:LLirikNN@gmail.com"));
                                        startActivity(emailIntent);
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });


    }*/

}
