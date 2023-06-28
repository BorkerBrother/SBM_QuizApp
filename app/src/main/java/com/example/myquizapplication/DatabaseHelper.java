package com.example.myquizapplication;

import static android.app.DownloadManager.COLUMN_ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "SuperBioMarktTest";
    private static final int DB_VERSION = 1;
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_SCORE = "score";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Erstellt die Tabelle für Benutzer
        String createTableUsers = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_SCORE + " INTEGER DEFAULT 0)";;
        sqLiteDatabase.execSQL(createTableUsers);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Methode zum Aktualisieren des Scores eines Benutzers
    public void updateUserScore(String username, int newScore) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, newScore);

        String whereClause = COLUMN_USERNAME + " = ?";
        String[] whereArgs = {username};

        db.update(TABLE_USERS, values, whereClause, whereArgs);
        db.close();
    }
}
