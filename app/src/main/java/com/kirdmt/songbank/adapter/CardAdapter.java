package com.kirdmt.songbank.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kirdmt.songbank.Data.SongData;
import com.kirdmt.songbank.R;
import com.kirdmt.songbank.SongActivity;

import java.util.List;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {


    private Context mainContext;
    private List<SongData> songDataList;
    private String songNameStr, songTextStr, songInfoStr;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;


        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public CardAdapter(List<SongData> songDataList, Context context) {

        this.songDataList = songDataList;
        mainContext = context;

    }

    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);

        return new ViewHolder(cv);

    }

    @Override
    public void onBindViewHolder(final CardAdapter.ViewHolder holder, final int position) {

        songNameStr = songDataList.get(position).getSongName();
        songTextStr = songDataList.get(position).getSongText();
        songInfoStr = songDataList.get(position).getSongInfo();

        //Log.d("SongInfoTAG", "songInfo is: " + songInfoStr);

        final CardView cardView = holder.cardView;
        TextView songName = (TextView) cardView.findViewById(R.id.song_name_text);
        TextView songInfo = (TextView) cardView.findViewById(R.id.song_info_text);

        if (songInfoStr.length() > 101) {
            String bufSongInfoStr;
            bufSongInfoStr = songInfoStr.substring(0, 99);
            songInfoStr = bufSongInfoStr;
        }

        songName.setText(songNameStr);
        songInfo.setText(songInfoStr);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                songNameStr = songDataList.get(position).getSongName();
                songTextStr = songDataList.get(position).getSongText();

                Intent someSongActivity = new Intent(mainContext, SongActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                someSongActivity.putExtra("songText", songTextStr);
                someSongActivity.putExtra("songName", songNameStr);
                mainContext.startActivity(someSongActivity);

            }
        });

    }

    @Override
    public int getItemCount() {
        return songDataList.size();
    }

}
