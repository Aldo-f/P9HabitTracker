package com.aldofieuw.android.habittracker.data;

import android.provider.BaseColumns;

public final class HabitContract {
    private HabitContract() {
    }

    public static final class HabitEntry implements BaseColumns{

        //Name of database table for habits
        public final static String TABLE_NAME = "habits";

        //Unique id number for the habit
        public final static String _ID = BaseColumns._ID;

        //Name of the habit
        public final static String COLUMN_HABIT_NAME="name";

        //Duration of the habit (minutes)
        public final static String COLUMN_HABIT_DURATION = "duration";
    }
}
