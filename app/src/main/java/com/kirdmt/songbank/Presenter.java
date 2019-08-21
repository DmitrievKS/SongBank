package com.kirdmt.songbank;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class Presenter {

    private ContractView view;
    private DataModel model = new DataModel();
    static public ArrayList<BaseData> BaseDataArray = new ArrayList<BaseData>();
    static final ArrayList<String> SongNames = new ArrayList<String>();

    Presenter(ContractView mainView) {

        this.view = mainView;

      /*  if(view.getSavedSongList() != null)
        {
            view.setSongList(view.getSavedSongList());
        }
        else{getDataFromFB();}*/

        getDataFromFB();


    }

    public void getDataFromFB() {



        if (SongNames.size() > 0) {
            view.setSongList(SongNames);

            return;
        }

        model.getFireBaseData(new ModelCallback() {
            @Override
            public void onCallBack(DataSnapshot snapshot) {


                BaseData data;

                int index = 1;

                for (DataSnapshot snap : snapshot.getChildren()) {

                    data = new BaseData();

                    data.setSongName(" " + index + " " + (String) snap.child("songname").getValue());
                    data.setSongText((String) snap.child("songtext").getValue());

                    BaseDataArray.add(data);

                    if (data.getSongName().length() <= 30) {

                        SongNames.add(data.getSongName());

                    } else {

                        SongNames.add(data.getSongName().substring(0, 30) + "...");
                    }

                    index++;

                }

                view.setSongList(SongNames);


            }
        });

    }

    public void getSongData(int value) {

        String songText = BaseDataArray.get(value).getSongText();
        String songName = BaseDataArray.get(value).getSongName();

        view.openSongActivity(songName, songText);

    }


    void searchSongs(String string) {

        if (string != null) {

            int index = 0;

            ArrayList<String> foundSongList = new ArrayList<String>();

            while (index < SongNames.size()) {
                if (SongNames.get(index).toLowerCase().contains(string.toLowerCase())) {
                    foundSongList.add(SongNames.get(index));
                    //Log.d("TAG", "SongName is: " + SongNames.get(index));
                }
                index++;
            }

            if (foundSongList.size() > 0) {

                view.setSongList(foundSongList);

            } else {

                view.showToast("Ничего не найдено");
            }
        }


    }
}
