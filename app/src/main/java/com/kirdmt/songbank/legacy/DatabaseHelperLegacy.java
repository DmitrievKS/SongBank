package com.kirdmt.songbank.legacy;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by user on 21.02.2018.
 */

class DatabaseHelperLegacy extends SQLiteOpenHelper {

    private static String DB_PATH; // полный путь к базе данных
    private static String DB_NAME = "DBSongBankSVRnew2.db";
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "Main"; // название таблицы в бд
    // названия столбцов
    static final String COLUMN_NUMBER = "Number";
    static final String COLUMN_NAME = "Name";
    static final String COLUMN_TEXT = "Text";
    private Context myContext;

    DatabaseHelperLegacy(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        DB_PATH =context.getFilesDir().getPath() + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    void create_db(){  //before using this method - nessesery if( DB existing)

        InputStream myInput = null;
        OutputStream myOutput = null;

        try {

            File file = new File(DB_PATH);
            if (!file.exists()) {
                this.getReadableDatabase();
                //получаем локальную бд как поток
                myInput = myContext.getAssets().open(DB_NAME);
                // Путь к новой бд
                String outFileName = DB_PATH;

                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch(IOException ex){
            Log.d("DatabaseHelper", ex.getMessage());
        }
    }
    public SQLiteDatabase open()throws SQLException {

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public int getCountCompareResult(long countForCompare, SQLiteDatabase db)
    {
        int CountCompareResult = 0;

        if (DatabaseUtils.queryNumEntries(db, "Main") != countForCompare)
        {
            CountCompareResult = (int)(countForCompare - DatabaseUtils.queryNumEntries(db, "Main"));
        }


       // DatabaseUtils.queryNumEntries(db, "Main");
       // userCursor = db.query("Main", new String[]{"Name", "Number", "Text"}, null, null, null, null, "Name ASC");
       // System.out.println("getCountCompareResult and countForCompare is " + countForCompare);
        //System.out.println("getCountCompareResult and DatabaseUtils.queryNumEntries is " + DatabaseUtils.queryNumEntries(db, "Main"));

return CountCompareResult;
    }
    public long getProfilesCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE);
        db.close();
        return count;
    }

  public void addRow(String songName, String songText,SQLiteDatabase db) { //работает но почему-то добавляет постфактум.

     // System.out.println(" i try add new row tomy DataBase" );
      //System.out.println("getCountCompareResult and DatabaseUtils.queryNumEntries is " + DatabaseUtils.queryNumEntries(db, "Main"));
      db.execSQL("insert into " + "Main" + " (" + "Name" + ", " + "Text" + ") values('" + songName + "', '" + songText + "')"); //работает
     // mDatabase.execSQL("INSERT INTO test (data) VALUES ('" + sString1 + "');");

      //return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
  }

}