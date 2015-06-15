package com.example.hoang.qlsv;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoang on 14/04/2015.
 */
public class Hienthi_danhsach_Activity extends Activity {
    List<Sinhvien> list;
    DatabaseHandler db;
    ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhsach);

        db = new DatabaseHandler(this);
        list = new ArrayList<Sinhvien>();
        list = db.LayListSV();

        lv = (ListView)findViewById(R.id.listSV);
        Xuly_custom_listview adapter = new Xuly_custom_listview(this, R.layout.layout_danhsach_custom, list);
        lv.setAdapter(adapter);
    }
}
