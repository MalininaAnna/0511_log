package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.UserBaseHelper;
import com.example.myapplication.database.UserDbSchema;
import com.example.secondapp.UserCursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Users {
    /*private String name;
    private String phone;*/
    private static Users users;
    private SQLiteDatabase database;
    private Context context;
    private List<String> userList;

    public static Users get(Context context){
        if (users == null){
            users = new Users(context);
        }
        return users;
    }

    private Users(Context context) {
        this.context = context.getApplicationContext();
        this.database = new UserBaseHelper(context).getWritableDatabase();
    }

    public void addUser(User user){ // Метод добавления пользователя в БД
        ContentValues values = getContentValues(user);
        database.insert(UserDbSchema.UserTable.NAME,null,values);
    }

    private static ContentValues getContentValues(User user){
        ContentValues values = new ContentValues();
        values.put(UserDbSchema.UserTable.Cols.UUID, user.getUuid().toString());
        values.put(UserDbSchema.UserTable.Cols.FIRSTNAME, user.getUserName());
        values.put(UserDbSchema.UserTable.Cols.LASTNAME, user.getUserLastName());
        values.put(UserDbSchema.UserTable.Cols.PHONE, user.getPhone());
        return values;
    }
    private UserCursorWrapper queryUsers(){
        Cursor cursor = database.query(UserDbSchema.UserTable.NAME,null,null,null,null,null,null);
        return new UserCursorWrapper(cursor);
    }

    public List<String> getUserList(){
        userList = new ArrayList<>();
        UserCursorWrapper cursorWrapper = queryUsers();
        try{
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()){
                User user = cursorWrapper.getUser();
                userList.add(user.getUserName()+" "+user.getUserLastName());
                cursorWrapper.moveToNext();
            }
        }finally {
            cursorWrapper.close();
        }

        /*for (int i = 0; i < 100; i++) {
            this.userList.add("Человек_"+i);
        }*/
        return this.userList;
    }
}

