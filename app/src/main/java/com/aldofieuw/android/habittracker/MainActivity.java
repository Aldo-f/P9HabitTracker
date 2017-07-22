package com.aldofieuw.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aldofieuw.android.habittracker.data.HabitDbHelper;

import static com.aldofieuw.android.habittracker.data.HabitContract.HabitEntry;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);

        insertHabit("Walking",30);
        insertHabit("Making dinner", 45);

        Cursor habit = readHabit();
    }

    private void insertHabit(String habitName, int habitDuration) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, habitName);
        values.put(HabitEntry.COLUMN_HABIT_DURATION, habitDuration);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    private Cursor readHabit() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        StringBuilder currentRowInfo = new StringBuilder();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_DURATION};

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        try {
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int durationColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DURATION);

            while (cursor.moveToNext()){
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentDuration = cursor.getInt(durationColumnIndex);

                currentRowInfo.append(
                          currentId + " - "
                        + currentName + " - "
                        + currentDuration);
            }
         }finally {
            cursor.close();
        }
        return cursor;
    }
}
