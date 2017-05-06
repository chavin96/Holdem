package com.example.chavin.holdem;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Ifham Pain on 5/6/2017.
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
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_GENDER = "gender";

    public DBHandler(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }
    SQLiteDatabase db = this.getWritableDatabase();
    // creating tables
    public void onCreate(SQLiteDatabase db) {

        //String CREATE_USER_TABLE = "CREATE TABLE" + TABLE_USERS + "(" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_EMAIL
                //+ "TEXT," + KEY_PASSWORD + "TEXT,"  + ")";

        db.execSQL("create table " + TABLE_USERS +" (id INTEGER PRIMARY KEY AUTOINCREMENT,email TEXT,password TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

    public boolean insertData(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_EMAIL, email);
        contentValues.put(KEY_PASSWORD, password);
        //contentValues.put(KEY_GENDER, gender);
        long result = db.insert(TABLE_USERS, null, contentValues);


        if(result == -1){
            return false;
        }
        else
            return true;

    }

}
