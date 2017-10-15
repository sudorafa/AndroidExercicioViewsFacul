package com.example.orafa.androidexercicioviewsfacul.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.orafa.androidexercicioviewsfacul.model.User;

/**
 * Created by oRafa on 12/10/2017.
 */

public class DUser extends SQLiteOpenHelper {
    public DUser(Context context) {
        super(context, "rafa", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user(id integer primary key, name TEXT, email TEXT, nick TEXT, password TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table user;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void save(User user) {
        ContentValues cv = getContentValues(user);
        SQLiteDatabase db = getWritableDatabase();
        if (user.getIdUser() == null) {
            db.insert("user", null, cv);
        } else {
            update(user);
        }
    }

    @NonNull
    private ContentValues getContentValues(User user) {
        ContentValues cv = new ContentValues();
        cv.put("name", user.getName());
        cv.put("email", user.getEmail());
        cv.put("nick", user.getNick());
        cv.put("password", user.getPassword());
        return cv;
    }

    public User update(User user) {
        //Montar CV
        ContentValues cv = getContentValues(user);
        //SQLiteDatabase
        SQLiteDatabase db = getWritableDatabase();
        //update
        String[] params = {user.getIdUser().toString()};
        int retorno = db.update("user", cv, "id = ?", params);
        if (retorno > 0) {
            return user;
        }
        return null;
    }

    public User findUser() {
        String sql = "select * from user;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToNext()) {
            User user = new User();

            user.setIdUser(cursor.getInt(cursor.getColumnIndex("id")));
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            user.setNick(cursor.getString(cursor.getColumnIndex("nick")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));

            return user;
        } else{
            return null;
        }
    }
}
