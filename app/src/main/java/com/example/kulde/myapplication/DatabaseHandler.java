package com.example.kulde.myapplication;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "carnew";

    // Contacts table name
    private static final String TABLE_CAR = "cardetails";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "Carname";
    private static final String KEY_YEAR = "Year";
    private static final String KEY_MILES_CAR_ADDED = "MilesCA";

    private static final String KEY_UTC = "UTC";
    private static final String KEY_MILES_FUEL_ADDED = "MilesFA";
    private static final String KEY_FUEL_QTY = "FuelQty";
    private static final String KEY_PAID_AMOUNT = "Amount";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CAR_TABLE = "CREATE TABLE "+ TABLE_CAR +"("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_NAME + " TEXT,"
                    + KEY_YEAR + " INTEGER,"
                    + KEY_MILES_CAR_ADDED + " DOUBLE,"
                    + KEY_UTC + " TEXT,"
                    + KEY_MILES_FUEL_ADDED + " DOUBLE,"
                    + KEY_FUEL_QTY + " DOUBLE,"
                    + KEY_PAID_AMOUNT + " DOUBLE"
                    + ");";

        db.execSQL(CREATE_CAR_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addCar(CarDetails cardetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, cardetails.getCarname()); // Car Name
        values.put(KEY_YEAR, cardetails.getYear());
        values.put(KEY_MILES_CAR_ADDED, cardetails.getMilesCA());
        values.put(KEY_UTC, cardetails.getUTC());
        values.put(KEY_MILES_FUEL_ADDED, cardetails.getMilesFA());
        values.put(KEY_FUEL_QTY, cardetails.getFuelQty());
        values.put(KEY_PAID_AMOUNT, cardetails.getAmount());


        // Inserting Row
        try {

            Log.d("Insert:", "Inserting.. ......");
            db.insert(TABLE_CAR, null, values);}
        catch (Exception e1){
            Log.d("KAKA","Exception");
                Log.d("KAKA",e1.getMessage());

        }
        Log.d("Insert:", "Inserted congrats");
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

    }

    // Getting single contact
    CarDetails getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CAR, new String[] { KEY_ID,
                        KEY_NAME, "KEY_YEAR", "KEY_MILES_CAR_ADDED",KEY_UTC,"KEY_MILES_FUEL_ADDED","KEY_FUEL_QTY","KEY_PAID_AMOUNT" }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        CarDetails card = new CarDetails(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Integer.parseInt(cursor.getString(2)),Double.parseDouble(cursor.getString(3)),cursor.getString(4),Double.parseDouble(cursor.getString(5)),Double.parseDouble(cursor.getString(6)),Double.parseDouble(cursor.getString(7)));
        // return contact
        return card;
    }

    // Getting All Contacts
    public List<CarDetails> getAllContacts() {
        List<CarDetails> cardetailsList = new ArrayList<CarDetails>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CAR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CarDetails cardetails = new CarDetails();
                cardetails.setId(Integer.parseInt(cursor.getString(0)));
                cardetails.setCarname(cursor.getString(1));
                cardetails.setYear(Integer.parseInt(cursor.getString(2)));
                cardetails.setMilesCA(Double.parseDouble(cursor.getString(3)));
                cardetails.setUTC((cursor.getString(4)));
                cardetails.setMilesFA(Double.parseDouble(cursor.getString(5)));
                cardetails.setFuelQty(Double.parseDouble(cursor.getString(6)));
                cardetails.setAmount(Double.parseDouble(cursor.getString(7)));
                // Adding car to list
                cardetailsList.add(cardetails);
            } while (cursor.moveToNext());
        }

        // return contact list
        return cardetailsList;
    }
    // Getting All Contacts
    public List<CarDetails> getSpecificContacts(int id) {
        List<CarDetails> cardetailsList = new ArrayList<CarDetails>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CAR+" WHERE id="+id;
        Log.d("Reading: from selected ", selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CarDetails cardetails = new CarDetails();
                cardetails.setId(Integer.parseInt(cursor.getString(0)));
                cardetails.setCarname(cursor.getString(1));
                cardetails.setYear(Integer.parseInt(cursor.getString(2)));
                cardetails.setMilesCA(Double.parseDouble(cursor.getString(3)));
                cardetails.setUTC((cursor.getString(4)));
                cardetails.setMilesFA(Double.parseDouble(cursor.getString(5)));
                cardetails.setFuelQty(Double.parseDouble(cursor.getString(6)));
                cardetails.setAmount(Double.parseDouble(cursor.getString(7)));
                // Adding car to list
                cardetailsList.add(cardetails);
            } while (cursor.moveToNext());
        }

        // return contact list
        return cardetailsList;
    }
    // Updating single contact
    public int updateContact(CarDetails cardetails) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, cardetails.getCarname()); // Car Name
        values.put(KEY_YEAR, cardetails.getYear());
        values.put(KEY_MILES_CAR_ADDED, cardetails.getMilesCA());
        values.put(KEY_UTC, cardetails.getCarname());
        values.put(KEY_MILES_FUEL_ADDED, cardetails.getMilesFA());
        values.put(KEY_FUEL_QTY, cardetails.getFuelQty());
        values.put(KEY_PAID_AMOUNT, cardetails.getAmount());

        // updating row
        return db.update(TABLE_CAR, values, KEY_ID + " = ?",
                new String[] { String.valueOf(cardetails.getId()) });
    }

    // Deleting single contact
    public void deleteContact(CarDetails cardetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CAR, KEY_ID + " = ?", new String[]{String.valueOf(cardetails.getId())});
        db.close();
    }
    // Deleting single contact
    public void deleteContactbyname(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CAR, KEY_NAME + " = ?", new String[] { String.valueOf(name) });
        db.close();
    }
    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CAR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}