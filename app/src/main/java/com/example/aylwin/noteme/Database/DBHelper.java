package com.example.aylwin.noteme.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.aylwin.noteme.Model.Catatan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aylwin on 8/9/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Catatan.db";

    private static final String TABLE_CATATAN_NAME= "Catatan";
    private static final String[] COLUMN = {"ID", "NAMA", "NOTE", "DATE"};
    private Context con;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
       // this.con=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE " + TABLE_CATATAN_NAME +
                    "(" + COLUMN[0] + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " " + COLUMN[1] + " VARCHAR(255)," +
                    " " + COLUMN[2] + " VARCHAR(500))");
        } catch (Exception e) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_CATATAN_NAME);
        onCreate(db);
    }

    public List<Catatan> getAllBarang(Context context){
       List<Catatan> data = new ArrayList<>();
        try {
            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CATATAN_NAME, null);
            while (cursor.moveToNext()) {
                Catatan b = new Catatan();
                b.setId(cursor.getInt(cursor.getColumnIndex(COLUMN[0])));
                b.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN[1])));
                b.setNote(cursor.getString(cursor.getColumnIndex(COLUMN[2])));
                data.add(b);
            }



        }catch (Exception e)
        {
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
       return data;
    }

    public long insertBarang(Catatan cat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN[1], cat.getTitle());
        values.put(COLUMN[2], cat.getNote());
        return db.insert(TABLE_CATATAN_NAME, null, values);
    }


    public int updateBarang(Catatan cat, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN[1], cat.getTitle());
        values.put(COLUMN[2], cat.getNote());
        return db.update(TABLE_CATATAN_NAME,
                values,
                COLUMN[0] + " = ?",
                new String[]{id + ""});
    }
    public int deleteBarang(Catatan cat){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_CATATAN_NAME,
                COLUMN[0] + " = ?",
                new String[]{cat.getId()+""});
    }
}
