package com.hfad.testo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper {

    private SQLiteDatabase db;
    private final int DB_VERSION = 1;
    private final String DB_NAME = "starbuzz"; // Имя б

    public DBHelper(Context context) {
        PizzaDBHelper helper=new PizzaDBHelper(context);
        db=helper.getWritableDatabase();
    }

    public void insertDrink(String name, int resourceId) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("name", name);
        drinkValues.put("image", resourceId);
        db.insert("Pizza", null, drinkValues);
    }

    public int update(Pizza md) {
        ContentValues cv = new ContentValues();
        cv.put("name", md.getName());
        cv.put("image", md.getImageResourceId());
        return db.update("Pizza", cv,"id_pizza" + " = ?", new String[]{String.valueOf(md.getId())});
    }

    public void deleteAll()
    {
        db.delete("Pizza", null, null);
    }

    public void delete(long id) {
        db.delete("Pizza", "id_pizza" + " = ?", new String[]{String.valueOf(id)});
    }

    public Pizza select(long id) {
        Cursor cursor = db.query("Pizza", null, "id_pizza" + " = ?", new String[]{String.valueOf(id+1)}, null, null, null);
        cursor.moveToFirst();
        String name = cursor.getString(1);
        int image = cursor.getInt(2);
        return new Pizza(id, name, image);
    }

    public ArrayList<Pizza> selectAll() {
        Cursor cursor = db.query("Pizza", null, null, null, null, null, null);
        ArrayList<Pizza> arr = new ArrayList<Pizza>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast())
        {
            do {
                long id = cursor.getLong(0);
                String name = cursor.getString(1);
                int image = cursor.getInt(2);
                arr.add(new Pizza(id, name, image));
            } while (cursor.moveToNext());
        }
        return arr;
    }
    private class PizzaDBHelper extends SQLiteOpenHelper
    {
        public PizzaDBHelper(@Nullable Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE Pizza (" +
                    " id_pizza INTEGER   PRIMARY KEY AUTOINCREMENT," +
                    " name     TEXT (50)," +
                    " image    INTEGER)");

            insertDrink(sqLiteDatabase, "res", R.drawable.diavolo);
            insertDrink(sqLiteDatabase, "red", R.drawable.funghi);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Pizza");
            onCreate(sqLiteDatabase);
        }

        private void insertDrink(SQLiteDatabase db, String name, int resourceId) {
            ContentValues drinkValues = new ContentValues();
            drinkValues.put("name", name);
            drinkValues.put("image", resourceId);
            db.insert("Pizza", null, drinkValues);
        }
    }
}
