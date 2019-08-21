package com.kirdmt.songbank;

/**
 * Class responsible to hold the name and the message to the user
 * to send to firebase
 */
public class BaseData {


    private String songName;
    private String songText;

    public BaseData() {

    }

    public String getSongName() {
        return songName;
    }
    public String getSongText() {  return songText;   }

    public void  setSongName(String name) {

        this.songName = name;

    }
    public void setSongText(String songText) {

         this.songText = songText;
    }


}