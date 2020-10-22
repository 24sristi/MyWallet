package com.example.mywallet;

import android.content.ContentValues;
import android.content.Context;
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

    public void addcredit(String amount, String reason) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_TYPE, "credit");
        values.put(COLUMN_AMOUNT, amount);
        values.put(COLUMN_REASON, reason);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        values.put(COLUMN_TIMESTAMP, timestamp.toString());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_WALLET, null, values);
        db.close();
    }


}
