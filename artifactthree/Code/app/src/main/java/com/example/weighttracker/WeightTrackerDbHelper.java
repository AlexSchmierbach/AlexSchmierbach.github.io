package com.example.weighttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WeightTrackerDbHelper  extends SQLiteOpenHelper {

    private Context currentContext;

    private static final String SQL_CREATE_ACCOUNTS =
            "CREATE TABLE " + WeightTrackerContract.WeightTrackerAccountEntry.TABLE_NAME + " (" +
                    WeightTrackerContract.WeightTrackerAccountEntry._ID + " INTEGER PRIMARY KEY," +
                    WeightTrackerContract.WeightTrackerAccountEntry.COLUMN_EMAIL + " TEXT," +
                    WeightTrackerContract.WeightTrackerAccountEntry.COLUMN_PASSWORD + " TEXT," +
                    WeightTrackerContract.WeightTrackerAccountEntry.COLUMN_GOAL_WEIGHT + " TEXT)";
    private static final String SQL_CREATE_WEIGHTS =
            "CREATE TABLE " + WeightTrackerContract.WeightTrackerEntry.TABLE_NAME + " (" +
                    WeightTrackerContract.WeightTrackerEntry._ID + " INTEGER PRIMARY KEY," +
                    WeightTrackerContract.WeightTrackerEntry.COLUMN_DATE + " TEXT," +
                    WeightTrackerContract.WeightTrackerEntry.COLUMN_WEIGHT + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WeightTrackerContract.WeightTrackerEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WeightTracker.db";

    public WeightTrackerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.currentContext = context;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ACCOUNTS);
        db.execSQL(SQL_CREATE_WEIGHTS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long Create(String date, String weight) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WeightTrackerContract.WeightTrackerEntry.COLUMN_DATE, date);
        values.put(WeightTrackerContract.WeightTrackerEntry.COLUMN_WEIGHT, weight);

        long newRowId = db.insert(WeightTrackerContract.WeightTrackerEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(getContext(), "Error: Problem with database: Creating entry failed. Seek support.", Toast.LENGTH_SHORT).show();
        }
        return newRowId;
    }

    public ArrayList<Weight> ReadRow() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + WeightTrackerContract.WeightTrackerEntry.TABLE_NAME, null);

        ArrayList<Weight> weightArrayList = new ArrayList<>();
        while(cursor.moveToNext()) {
            weightArrayList.add(new Weight(
                    cursor.getColumnIndex("_ID"),
                    cursor.getString(1),
                    Float.parseFloat(cursor.getString(2))));
        }
        cursor.close();
        return weightArrayList;
    }

    public void Delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = WeightTrackerContract.WeightTrackerEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};
        int deletedRows = db.delete(WeightTrackerContract.WeightTrackerEntry.TABLE_NAME, selection, selectionArgs);
        if (deletedRows == 0) {
            Toast.makeText(getContext(), "Error: Problem with database: Deleting entry failed. Seek support.", Toast.LENGTH_SHORT).show();
        }
    }

    public void Update(int id, String date, String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WeightTrackerContract.WeightTrackerEntry.COLUMN_DATE, date);
        values.put(WeightTrackerContract.WeightTrackerEntry.COLUMN_WEIGHT, weight);

        String selection = WeightTrackerContract.WeightTrackerEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};

        int count = db.update(WeightTrackerContract.WeightTrackerEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        if (count == 0) {
            Toast.makeText(getContext(), "Error: Problem with database: Updating entry failed. Seek support.", Toast.LENGTH_SHORT).show();
        }
    }

    public void Update(int id, String goalWeight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WeightTrackerContract.WeightTrackerAccountEntry.COLUMN_GOAL_WEIGHT, goalWeight);

        String selection = WeightTrackerContract.WeightTrackerAccountEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};

        int count = db.update(WeightTrackerContract.WeightTrackerAccountEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        if (count == 0) {
            Toast.makeText(getContext(), "Error: Problem with database: Updating goal weight failed. Seek support.", Toast.LENGTH_LONG).show();
        }
    }

    private Context getContext() {
        return this.currentContext;
    }
}
