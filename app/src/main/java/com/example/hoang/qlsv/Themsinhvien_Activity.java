package com.example.hoang.qlsv;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Hoang on 14/04/2015.
 */

public class Themsinhvien_Activity extends Activity {

    DatabaseHandler db;
    Button btnThem;
    EditText addMSSV, addHoten, addLop;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themsv);
        db = new DatabaseHandler(this);

        btnThem = (Button)findViewById(R.id.btnThem);
        addMSSV = (EditText)findViewById(R.id.edMSSV);
        addHoten = (EditText)findViewById(R.id.edHoten);
        addLop = (EditText)findViewById(R.id.edLop);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sinhvien sv = new Sinhvien();
                sv.setMSSV(addMSSV.getText().toString());
                sv.setHoten(addHoten.getText().toString());
                sv.setLop(addLop.getText().toString());
                db.AddData(sv);
                finish();
            }
        });


    }
}
