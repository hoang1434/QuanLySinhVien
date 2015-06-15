package com.example.hoang.qlsv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Hoang on 14/04/2015.
 */
public class Xuly_custom_listview extends ArrayAdapter<Sinhvien> {
    Context context;
    int res;
    List<Sinhvien> sv;
    public Xuly_custom_listview(Context context, int resource, List<Sinhvien> objects) {
        super(context, resource, objects);
        this.context=context;
        this.res = resource;
        this.sv = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View viewrow = inflater.inflate(R.layout.layout_danhsach_custom,parent,false);

        TextView txtMSSV = (TextView)viewrow.findViewById(R.id.txtMSSV);
        TextView txtHoten = (TextView)viewrow.findViewById(R.id.txtHoten);
        TextView txtLop = (TextView)viewrow.findViewById(R.id.txtLop);
        final Button btnSua = (Button)viewrow.findViewById(R.id.btnSua);
        final Button btnXoa = (Button) viewrow.findViewById(R.id.btnXoa);

        Sinhvien getSinhvien=sv.get(position);
        txtMSSV.setText(getSinhvien.getMSSV());
        txtHoten.setText(getSinhvien.getHoten());
        txtLop.setText(getSinhvien.getLop());
        btnXoa.setTag(getSinhvien.getMSSV());
        btnSua.setTag(getSinhvien.getMSSV());

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCapnhat = new Intent(context, Capnhat_Activity.class);
                Bundle bdTruyenDL = new Bundle();

                bdTruyenDL.putString("mssv",btnSua.getTag().toString());
                iCapnhat.putExtra("goiDL",bdTruyenDL);
                context.startActivity(iCapnhat);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                DatabaseHandler db = new DatabaseHandler(context);
                String mssv = btnXoa.getTag().toString();
                db.xoaSinhVien(mssv);

                viewrow.animate().alpha(0).setDuration(2000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        sv.remove(position);
                        notifyDataSetChanged();
                        viewrow.setAlpha(1);
                    }
                });
            }
        });

        return viewrow;
    }
}
