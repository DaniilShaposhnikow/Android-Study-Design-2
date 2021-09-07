package com.hfad.testo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class PastaDataBaseHelper
{
    private SQLiteDatabase db;
    private static final String DB_NAME = "starbuzz";
    private static final int DB_VERSION = 1;

    public PastaDataBaseHelper(Context context)
    {
        PastaDBHelper helper= new PastaDBHelper(context);
        db=helper.getWritableDatabase();
    }

    public Pasta select(long id)
    {
        Cursor cursor = db.query("PASTA", null, "_id" + " = ?", new String[]{String.valueOf(id+1)}, null, null, null);
        cursor.moveToFirst();
        String name = cursor.getString(1);
        int image = cursor.getInt(2);
        return new Pasta(name, image);
    }

    public ArrayList<Pasta> selectAll()
    {
        Cursor cursor = db.query("PASTA", null, null, null, null, null, null);
        ArrayList<Pasta> arr = new ArrayList<Pasta>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast())
        {
            do {
                String name = cursor.getString(1);
                int image = cursor.getInt(2);
                arr.add(new Pasta(name, image));
            } while (cursor.moveToNext());
        }
        return arr;
    }

    private class PastaDBHelper extends SQLiteOpenHelper
    {
        public PastaDBHelper(Context context)
        {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE PASTA (\n" +
                    "    _id               INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    NAME              TEXT,\n" +
                    "    IMAGE_RESOURCE_ID INTEGER\n" +
                    ");");
            insertPasta(db, "Spaghetti Bolognese", R.drawable.spaghettibolognese);
            insertPasta(db, "Lasagne", R.drawable.lasagne);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {

        }

        private void insertPasta(SQLiteDatabase db, String name, int resourceId)
        {
            ContentValues pastaValues = new ContentValues();
            pastaValues.put("NAME", name);
            pastaValues.put("IMAGE_RESOURCE_ID", resourceId);
            db.insert("PASTA", null, pastaValues);
        }
    }
}
