package com.example.mywallet;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Timestamp;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "wallet.db";
    public static final String TABLE_WALLET = "wallet";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_AMOUNT = "_amount";
    public static final String COLUMN_REASON = "_reason";
    public static final String COLUMN_TYPE = "_type";
    public static final String COLUMN_TIMESTAMP = "_time";

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int DATABASE_VERSION) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_WALLET + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TYPE + " TEXT , "
                + COLUMN_AMOUNT + " TEXT , "
                + COLUMN_REASON + " TEXT , "
                + COLUMN_TIMESTAMP + " TEXT ); ";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_WALLET);
        onCreate(sqLiteDatabase);
    }

    public void addEntry(String transactionType, String amount, String reason) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_TYPE, transactionType);
        values.put(COLUMN_AMOUNT, amount);
        values.put(COLUMN_REASON, reason);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        values.put(COLUMN_TIMESTAMP, timestamp.toString());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_WALLET, null, values);
        db.close();
    }

    public String getTotal(){
        String transactionType;
        Integer currentAmount,totalAmount=0;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT "+COLUMN_TYPE+", "+COLUMN_AMOUNT+" FROM " + TABLE_WALLET;

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_TYPE)) != null) {
                currentAmount= Integer.parseInt(c.getString(c.getColumnIndex(COLUMN_AMOUNT)));
                transactionType=c.getString(c.getColumnIndex(COLUMN_TYPE));

                if(transactionType.equals("credit")) {
                    totalAmount+=currentAmount;
                }
                else {
                    totalAmount-=currentAmount;
                }
            }
            c.moveToNext();
        }
        return totalAmount.toString();
    }

    public String databaseToString() {
        String DBString = "";
        Integer amount;
        String type, reason;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_WALLET;

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_TIMESTAMP)) != null) {
                DBString += String.format("%10.10s", c.getString(c.getColumnIndex(COLUMN_TIMESTAMP)));
                DBString += "   ";

                // get all details

                DBString += (c.getString(c.getColumnIndex(COLUMN_TYPE)) + " ");
                DBString += (c.getString(c.getColumnIndex(COLUMN_AMOUNT)) + " ");
                DBString += (c.getString(c.getColumnIndex(COLUMN_REASON)) + " ");

                DBString += "\n";

            }
            c.moveToNext();
        }
        return DBString;
    }



}
