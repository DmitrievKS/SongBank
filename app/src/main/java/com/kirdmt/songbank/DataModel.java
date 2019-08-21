package com.kirdmt.songbank;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DataModel {

    private static boolean fireBaseFirstStart = true;

    private DatabaseReference mReference;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    DataModel() {

        fireBaseInit();

    }

    public void getFireBaseData(final ModelCallback fireBaseCallback) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        if (fireBaseFirstStart) {
            database.setPersistenceEnabled(true);
            fireBaseFirstStart = false;
        }

        mReference.orderByChild("songname").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                fireBaseCallback.onCallBack(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }

    private void fireBaseInit() {

        if (fireBaseFirstStart) {
            database.setPersistenceEnabled(true);
            fireBaseFirstStart = false;
        }

        mReference = database.getReference("bank");
        mReference.keepSynced(true);
    }

}
