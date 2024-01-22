package com.example.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 5;
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_BEACON_ID = "BeaconID";
    private static final String COLUMN_BEACON_NAME = "BeaconName";
    private static final String COLUMN_LOCATION = "Location";
    private static final String COLUMN_PATHS = "Paths";
    public static final String TABLE_FLOOR1 = "Floor1";
    public static final String TABLE_FLOOR2 = "Floor2";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_FLOOR1 = "CREATE TABLE " + TABLE_FLOOR1 + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_BEACON_ID + " TEXT,"
                + COLUMN_BEACON_NAME + " TEXT,"
                + COLUMN_LOCATION + " TEXT,"
                + COLUMN_PATHS + " TEXT)";
        db.execSQL(CREATE_TABLE_FLOOR1);

        String CREATE_TABLE_FLOOR2 = "CREATE TABLE " + TABLE_FLOOR2 + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_BEACON_ID + " TEXT,"
                + COLUMN_BEACON_NAME + " TEXT,"
                + COLUMN_LOCATION + " TEXT,"
                + COLUMN_PATHS + " TEXT)";
        db.execSQL(CREATE_TABLE_FLOOR2);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLOOR1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLOOR2);
        onCreate(db);
    }

    // Method to insert data into Floor1
    public boolean insertDataFloor1(String beaconId, String beaconName, String location, String paths) {
        return insertData(beaconId, beaconName, location, paths, TABLE_FLOOR1);
    }

    // Method to insert data into Floor2
    public boolean insertDataFloor2(String beaconId, String beaconName, String location, String paths) {
        return insertData(beaconId, beaconName, location, paths, TABLE_FLOOR2);
    }

    // Method to insert data
    public boolean insertData(String beaconId, String beaconName, String location, String paths, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BEACON_ID, beaconId);
        contentValues.put(COLUMN_BEACON_NAME, beaconName);
        contentValues.put(COLUMN_LOCATION, location);
        contentValues.put(COLUMN_PATHS, paths);
        long result = db.insert(tableName, null, contentValues);
        db.close();
        return result != -1; // return true if data insert is successful
    }

    // Method to update data
    public boolean updateBeacon(String beaconId, String beaconName, String location, String paths, String oldFloor, String newFloor) {
        // Check if the floor has changed
        if (!oldFloor.equals(newFloor)) {
            // Floor has changed, so move the beacon to the new floor
            // Delete from old floor table
            deleteBeacon(beaconId, oldFloor);

            // Insert into new floor table
            return insertData(beaconId, beaconName, location, paths, newFloor);
        } else {
            // Floor hasn't changed, just update the existing entry
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_BEACON_ID, beaconId);
            contentValues.put(COLUMN_BEACON_NAME, beaconName);
            contentValues.put(COLUMN_LOCATION, location);
            contentValues.put(COLUMN_PATHS, paths);

            // Update the record
            int updatedRows = db.update(oldFloor, contentValues, COLUMN_BEACON_ID + "=?", new String[]{beaconId});
            db.close();
            return updatedRows > 0;
        }
    }



    public boolean deleteBeacon(String beaconId, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(tableName, COLUMN_BEACON_ID + "=?", new String[]{beaconId});
        db.close();
        return deletedRows > 0;
    }



    public ArrayList<Beacon> getAllData(String tableName) {
        ArrayList<Beacon> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(COLUMN_BEACON_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_BEACON_NAME));
                String location = cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION));
                String paths = cursor.getString(cursor.getColumnIndex(COLUMN_PATHS));
                String floor = tableName; // Floor1 or Floor2, based on the table being queried

                Beacon beacon = new Beacon(id, name, location, paths, floor);
                dataList.add(beacon);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dataList;
    }

}

