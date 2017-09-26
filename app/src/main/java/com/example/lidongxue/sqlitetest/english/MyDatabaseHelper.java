package com.example.lidongxue.sqlitetest.english;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lidongxue on 17-9-26.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    final String CREATE_TABLE_SQL="create table dict(_id integer primary key autoincrement, "+" word , detail)";
    public MyDatabaseHelper(Context context, String name,  int version) {
        super(context, name, null, version);
        Log.d("--------tag--------","继承SQLiteOpenHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //第一次使用数据库是自动建表
        db.execSQL(CREATE_TABLE_SQL);
        Log.d("--------tag--------","第一次使用数据库自动建表");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("--------onUpdate Called--------"+i+"---->"+i1);
    }
}
