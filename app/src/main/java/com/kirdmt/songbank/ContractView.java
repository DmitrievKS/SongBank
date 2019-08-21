package com.kirdmt.songbank;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public interface ContractView {

    /*void showProgress(String operationExplanation);

    void hideProgress();

    void startMain();*/

    void openSongActivity(String songName, String songText);

    Context getContext();

    void showToast(String message);

    void setSongList(ArrayList<String> songNames);

    ArrayList<String> getSavedSongList();

  }
