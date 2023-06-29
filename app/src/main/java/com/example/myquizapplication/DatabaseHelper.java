package com.example.myquizapplication;

import static android.app.DownloadManager.COLUMN_ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
                "email TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_SCORE + " INTEGER DEFAULT 0)";
        sqLiteDatabase.execSQL(createTableUsers);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists allusers");
    }

    public Boolean insertData(String email, String passwort){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("passwort", passwort);
        long result = MyDatabase.insert(TABLE_USERS,null,contentValues);

        if (result == -1){
            return false;
        }
        else {
            return true;
        }

    }

    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ?", new String[]{email});

        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkEmailpassword(String email, String passwort) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{email, passwort});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
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
