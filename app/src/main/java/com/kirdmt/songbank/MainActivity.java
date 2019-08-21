package com.kirdmt.songbank;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;


//in the future versions:


//TODO -3. реализовать константы.
//TODO -1. Вывод ошибки на экран!
//TODO 1. internet access cheking;

//TODO 6. realise accords view and tonal changer
//TODO 7. Songs description
//TODO 8. improve Pattern


public class MainActivity extends AppCompatActivity implements ContractView, SearchView.OnQueryTextListener {

    String savedSearchValue = null;
    ArrayList<String> savedSongNamesValue = null;
    boolean submitted = false;

    private ArrayAdapter<String> adapter;

    Presenter presenter;

    private ListView listView;
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

        presenter = new Presenter(this);

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

        //if(savedInstanceState.get("searchViewValue") != null & savedInstanceState.get("searchViewValue").toString().length() > 0)
        if (savedInstanceState.get("savedSearchValue") != null) {

            savedSearchValue = savedInstanceState.get("savedSearchValue").toString();

            submitted = savedInstanceState.getBoolean("submitted");

            // Log.d("TAG", "savedSearchValue is: " + savedSearchValue);
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

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("О приложении: 1.8 версия.")
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

    @Override
    public void openSongActivity(String songName, String songText) {

        Intent someSongActivity = new Intent(getApplicationContext(), SongActivity.class);

        someSongActivity.putExtra("songText", songText);
        someSongActivity.putExtra("songName", songName);

        startActivity(someSongActivity);

    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }

    @Override
    public void showToast(String message) {

        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setSongList(ArrayList<String> songNames) {

        if (adapter != null) {
            adapter = null;
            listView = null;

        }

        adapter = new ArrayAdapter<String>(this, R.layout.list_my_text, songNames);

        listView = (ListView) findViewById(R.id.item);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {

                String value = (String) parent.getItemAtPosition(position);
                value = value.substring(1);
                value = value.substring(0, value.indexOf(" "));
                int realPosition = Integer.parseInt(value) - 1;

                presenter.getSongData(realPosition);

            }
        });

    }

    @Override
    public ArrayList<String> getSavedSongList() {
        return savedSongNamesValue;
    }

    private void backButtonFunction() {

        submitted = false;

        searchView.setQuery("", false);
        searchView.clearFocus();
        adapter.clear();

        presenter.getDataFromFB();
    }

}


