package com.kirdmt.songbank.Data;

/**
 * Class responsible to hold the name and the message to the user
 * to send to firebase
 */
public class SongData {


    private String songName;
    private String songText;
    private String songInfo;

    //public SongData(String songName, String songText) {
    public SongData() {

    /*    this.songName = songName;
        this.songText = songText;*/

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


    public String getSongInfo() {
        return songInfo;
    }

    public void setSongInfo(String songInfo) {
        this.songInfo = songInfo;
    }
}