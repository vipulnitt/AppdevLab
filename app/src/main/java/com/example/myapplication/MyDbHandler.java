package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.data.User;
import com.example.myapplication.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
         String create ="CREATE TABLE "+Params.TABLE_NAME + "("+Params.KEY_ID+" INTEGER PRIMARY KEY,"
         + Params.KEY_NAME+" TEXT, " + Params.KEY_EMAIL+ " TEXT,"+ Params.KEY_PASSWORD+ " TEXT )";
         Log.d("vipul",create);
         db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME,user.getName());
        values.put(Params.KEY_EMAIL,user.getEmail());
        values.put(Params.KEY_PASSWORD,user.getPassword());
        db.insert(Params.TABLE_NAME,null,values);
        Log.d("vipul","successfully");
        db.close();
    }
    public User getUser(String email, String password)
    {
         User u = new User();
         SQLiteDatabase db = this.getReadableDatabase();
         String select = "SELECT * FROM "+ Params.TABLE_NAME+ " WHERE "+Params.KEY_EMAIL+"="+email+" AND "+ Params.KEY_PASSWORD+" ="+password;
         Cursor cursor = db.rawQuery(select,null);

         if(cursor.moveToFirst())
         {
             do{
                 User user = new User();
                 user.setId(Integer.parseInt(cursor.getString(0)));
                 user.setName(cursor.getString(1));
                 user.setEmail(cursor.getString(2));
                 user.setPassword(cursor.getString(3));
                 return user;
             }while(cursor.moveToNext());
         }
         return u;

    }
}
