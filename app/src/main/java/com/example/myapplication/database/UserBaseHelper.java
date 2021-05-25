package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.myapplication.database.UserDbSchema.*;
import androidx.annotation.Nullable;

public class UserBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userBase.db";

    public UserBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

}
    @Override
    public void onCreate(SQLiteDatabase db){// Если база не существует вызывается этот метод
        //db.execSQL("create table"+UserDbSchema.UserTable.NAME); если не импортирован пакет import static com.example.myapplication.database.UserDbSchema.*;
        db.execSQL("create table "+ UserTable.NAME+ "(" +
                "_id integer primary key autoincrement, " +
                UserTable.Cols.UUID+"," +
                UserTable.Cols.FIRSTNAME+"," +
                UserTable.Cols.LASTNAME+"," +
                UserTable.Cols.PHONE+")");
    }
    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){ // Если база существует и версии различаются, то метод вызывается этот

    }
}
