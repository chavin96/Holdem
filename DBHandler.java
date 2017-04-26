package com.ifhampain.holdem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ifham Pain on 4/26/2017.
 */
public class DBHandler extends SQLiteOpenHelper {

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "holdem_local";

    // Users table name
    private static final String TABLE_USERS = "users";

    // Users table column names
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final STRING KEY_PASSWORD = "password";

    public DBHandler(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // creating tables
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE = "CREATE TABLE" + TABLE_USERS + "(" + KEY_ID + "INTEGER PRIMARY KEY," + KEY_EMAIL
                + "TEXT," + KEY_PASSWORD + "TEXT" + ")";

        db.execSQL(CREATE_USER_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

}

