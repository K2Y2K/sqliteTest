package com.example.lidongxue.sqlitetest.english;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.lidongxue.sqlitetest.R;

import java.util.List;
import java.util.Map;

/**
 * Created by lidongxue on 17-9-26.
 */

public class resultActivity extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        ListView listView=findViewById(R.id.show_dict);
        Intent intent =getIntent();
        Bundle data=intent.getExtras();
       // @SuppressWarnings("unchecked");   该语句报错
        List<Map<String,String>> list=(List<Map<String,String>>)data.getSerializable("data");
        SimpleAdapter adapter =new SimpleAdapter(resultActivity.this,list,R.layout.line_dict,
                new String[]{"word","detail"},new int[]{R.id.word_d,R.id.detail_d});
        listView.setAdapter(adapter);
    }
}
