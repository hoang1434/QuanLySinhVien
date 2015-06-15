package com.example.hoang.qlsv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Hoang on 14/04/2015.
 */
public class Capnhat_Activity extends Activity {
    EditText edMSSV, edHoten, edLop;
    Button btnCapnhat;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_capnhat);

        db = new DatabaseHandler(this);

        edMSSV = (EditText)findViewById(R.id.edMSSV);
        edHoten = (EditText)findViewById(R.id.edHoten);
        edLop = (EditText)findViewById(R.id.edLop);
        btnCapnhat = (Button)findViewById(R.id.btnCapNhat);

        Bundle bdLaydl = new Bundle();
        bdLaydl = getIntent().getBundleExtra("goiDL");
        final String mssv = bdLaydl.getString("mssv");

        edMSSV.setText(mssv);
        Sinhvien sv = new Sinhvien();
        sv = db.laySinhvienTheoMa(mssv);

        edHoten.setText(sv.getHoten());
        edLop.setText(sv.getLop());

        btnCapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sinhvien sv = new Sinhvien();
                sv.setMSSV(edMSSV.getText().toString());
                sv.setHoten(edHoten.getText().toString());
                sv.setLop(edLop.getText().toString());

                if(db.capnhatSinhVien(sv) != -1){
                    Toast.makeText(getApplicationContext(), "CẬP NHẬT THÀNH CÔNG!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "CẬP NHẬT THẤT BẠI!", Toast.LENGTH_SHORT).show();
                }
                Intent iHienthi = new Intent(Capnhat_Activity.this,Hienthi_danhsach_Activity.class);
                startActivity(iHienthi);
            }
        });


    }
}
