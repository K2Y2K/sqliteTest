package com.example.lidongxue.sqlitetest.english;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lidongxue.sqlitetest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lidongxue on 17-9-26.
 */

public class mActivity extends Activity {
    MyDatabaseHelper dbHelper;
    Button insert=null;
    Button search=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dict);
        dbHelper=new MyDatabaseHelper(mActivity.this,"myDict.db3",1);
        insert=findViewById(R.id.insert);
        search=findViewById(R.id.search);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word=((EditText)findViewById(R.id.word)).getText().toString();
                String detail=((EditText)findViewById(R.id.detail)).getText().toString();
                insertData(dbHelper.getReadableDatabase(),word,detail);
                Toast.makeText(mActivity.this,"添加生词成功！",Toast.LENGTH_SHORT).show();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key=((EditText)findViewById(R.id.key)).getText().toString();
                Cursor cursor=dbHelper.getReadableDatabase().rawQuery("select * from dict " +
                        " where word like ? or detail like ? " ,
                        new String[]{"%"+key+"%","%"+key+"%"});
                Bundle data =new Bundle();
                data.putSerializable("data",converCursorToList(cursor));
                Intent intent =new Intent(mActivity.this,resultActivity.class);
                intent.putExtras(data);
                startActivity(intent);

            }
        });
    }

    protected ArrayList<Map<String,String>> converCursorToList(Cursor cursor) {
        ArrayList<Map<String,String>> result=new ArrayList<Map<String, String>>();
        while (cursor.moveToNext()){
            Map<String,String> map=new HashMap<>();
            map.put("word",cursor.getString(1));
            map.put("detail",cursor.getString(2));
            result.add(map);
        }
        return result;
    }

    private void insertData(SQLiteDatabase db, String word, String detail) {
        db.execSQL("insert into dict values(null,?,?)",new String[]{word,detail});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dbHelper!=null){
            dbHelper.close();
        }
    }
}
