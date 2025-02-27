package com.example.weighttracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class WeightTrackerContract {

    private WeightTrackerContract() {}

    public static class WeightTrackerEntry implements BaseColumns {
        public static final String TABLE_NAME = "weights";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_WEIGHT = "weight";
    }

    public static class WeightTrackerAccountEntry implements BaseColumns {
        public static final String TABLE_NAME = "accounts";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_GOAL_WEIGHT = "goal";
    }
}
