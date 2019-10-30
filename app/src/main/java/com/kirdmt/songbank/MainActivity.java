package com.kirdmt.songbank;

import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.kirdmt.songbank.Data.SongData;
import com.kirdmt.songbank.adapter.CardAdapter;

import java.util.ArrayList;


//in the future versions:


//TODO -3. реализовать константы.
//TODO -1. Вывод ошибки на экран!
//TODO 1. internet access cheking;
//TODO 6. realise accords view and tonal changer
//TODO 7. Songs description
//TODO 8. improve Pattern


public class MainActivity extends AppCompatActivity implements ContractView, SearchView.OnQueryTextListener {

    private CardAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    String savedSearchValue = null;
    ArrayList<String> savedSongNamesValue = null;
    boolean submitted = false;

    Presenter presenter;
    private SearchView searchView;

    @Override
    public void onBackPressed() {
        if (searchView.getQuery().toString().length() > 0) {

            backButtonFunction();

        } else {
            finish();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setUpToolbar();

        presenter = new Presenter(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        if (searchView.getQuery() != null & searchView.getQuery().toString().length() > 0) {
            savedInstanceState.putString("savedSearchValue", searchView.getQuery().toString());
        }
        savedInstanceState.putBoolean("submitted", submitted);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.get("savedSearchValue") != null) {
            savedSearchValue = savedInstanceState.get("savedSearchValue").toString();
            submitted = savedInstanceState.getBoolean("submitted");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);

        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        if (savedSearchValue != null) {
            searchView.setQuery(savedSearchValue, false);
            searchView.setIconifiedByDefault(false);

            if (submitted) {
                onQueryTextSubmit(savedSearchValue);
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_back:

                backButtonFunction();
                return true;

            case R.id.menu_info:

                String title = getString(R.string.about_app) + " " + getString(R.string.app_version);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(title)
                        .setMessage(getResources().getString(R.string.about))
                        // .setIcon(R.drawable.ic_android_cat)
                        .setCancelable(true)
                        .setNegativeButton(getString(R.string.write_letter),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();

                                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                                        emailIntent.setData(Uri.parse(getString(R.string.mail_to)));
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

    @Override
    public boolean onQueryTextSubmit(String text) {

        submitted = true;
        presenter.searchSongs(text);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    /*@Override
    public void openSongActivity(String songName, String songText) {

        Intent someSongActivity = new Intent(getApplicationContext(), SongActivity.class);

        someSongActivity.putExtra("songText", songText);
        someSongActivity.putExtra("songName", songName);

        startActivity(someSongActivity);

    }*/

    /*@Override
    public Context getContext() {
        return this.getApplicationContext();
    }*/

    @Override
    public void showToast(int messageId) {

        Toast.makeText(MainActivity.this, getString(messageId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setSongList(ArrayList<SongData> songData) {

        adapter = new CardAdapter(songData, getApplicationContext());
        recyclerView.setAdapter(null);
        recyclerView.setAdapter(adapter);

    }

    private void backButtonFunction() {

        submitted = false;
        searchView.setQuery("", false);
        searchView.clearFocus();

        recyclerView.setAdapter(null);
        presenter.getDataFromFB();
    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
    }

}