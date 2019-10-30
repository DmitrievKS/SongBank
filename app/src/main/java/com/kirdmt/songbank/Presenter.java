package com.kirdmt.songbank;

import com.google.firebase.database.DataSnapshot;
import com.kirdmt.songbank.Data.SongData;

import java.util.ArrayList;
import java.util.Collections;

public class Presenter {

    private ContractView view;
    private DataModel model = new DataModel();
    static public ArrayList<SongData> songDataArray = new ArrayList<SongData>();

    Presenter(ContractView mainView) {

        this.view = mainView;

        getDataFromFB();
    }

    public void getDataFromFB() {

        model.getFireBaseData(new ModelCallback() {
            @Override
            public void onCallBack(DataSnapshot snapshot) {

                SongData data;

                int index = 1;

                songDataArray.clear();

                for (DataSnapshot snap : snapshot.getChildren()) {

                    data = new SongData();

                    data.setSongInfo((String) snap.child("songinfo").getValue());
                    data.setSongName(index + ". " + (String) snap.child("songname").getValue());
                    data.setSongText((String) snap.child("songtext").getValue());

                    songDataArray.add(data);
                    index++;
                }

                Collections.reverse(songDataArray);
                view.setSongList(songDataArray);

            }
        });

    }

    void searchSongs(String string) {

        if (string != null & string.length() > 0) {

            int index = 0;
            ArrayList<SongData> foundSongData = new ArrayList<SongData>();

            while (index < songDataArray.size()) {

                if (songDataArray.get(index).getSongName().toLowerCase().contains(string.toLowerCase())) {
                    foundSongData.add(songDataArray.get(index));
                }
                index++;
            }

            if (foundSongData.size() > 0) {
                view.setSongList(foundSongData);
            } else {
                view.showToast(R.string.nothing_found);
            }
        }

    }
}
