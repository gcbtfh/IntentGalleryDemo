package com.example.tonghung.intentgallerydemo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import com.example.tonghung.intentgallerydemo.object.Picture;

import java.io.ByteArrayOutputStream;

/**
 * Created by tonghung on 21/12/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context/*, String name, SQLiteDatabase.CursorFactory factory, int version*/) {
        super(context, "BitmapSQLite.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Picture_tb(id_pic integer primary key autoincrement, name_pic " +
                "text not null, bitmap_pic blob not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Picture_tb");
        onCreate(db);
    }

    public long insertPic(Picture picture){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name_pic", picture.getName());
        contentValues.put("bitmap_pic", bitmap2Bytes(picture.getBitmap()));

        return db.insert("Picture_tb", null, contentValues);
    }

    public Cursor selectPic(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("select * from Picture_tb", null);
    }

    private byte[] bitmap2Bytes(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
