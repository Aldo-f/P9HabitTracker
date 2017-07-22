package com.aldofieuw.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.aldofieuw.android.habittracker.data.HabitContract.HabitEntry;

public class HabitDbHelper extends SQLiteOpenHelper{

    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    //Name of the DB file
    private static final String DATABASE_NAME = "habits.db";

    //DB version
    private static  final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String with SQL statement to create habit table
        String SQL_CREATE_HABIT_TABLE = "CREATE TABLE "+ HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_DURATION +" INTEGER NOT NULL);";

        //Execute SQL statement
        db.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DB is still version 1, so nothing to do here.
    }
}
