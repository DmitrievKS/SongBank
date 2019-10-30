package com.kirdmt.songbank;

import android.content.Context;

import com.kirdmt.songbank.Data.SongData;

import java.util.ArrayList;

public interface ContractView {

    /*void showProgress(String operationExplanation);

    void hideProgress();

    void startMain();*/

    void showToast(int messageId);

    void setSongList(ArrayList<SongData> songData);

}
