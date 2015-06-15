package com.example.hoang.qlsv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoang on 10/04/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "QLSV";
    public static final String TABLE_NAME = "Sinhvien";
    public static final String KEY_MSSV = "MSSV";
    public static final String KEY_HOTEN = "Hoten";
    public static final String KEY_LOP = "Lop";
    Context context;
    String dataPath;
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
        dataPath = context.getFilesDir().getParent() + "/databases/" + DATABASE_NAME;
        Log.d("KetNoi", "Chạy chương trình");
        createDatabase();
    }

  @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase openDatabase(){
        return SQLiteDatabase.openDatabase(dataPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void copyDatabase(){
        Log.d("KetNoi", "Đang copy database");
        try{
            InputStream is = context.getAssets().open(DATABASE_NAME);
            OutputStream os = new FileOutputStream(dataPath);
            byte[] buffer = new byte[1024];
            int length = 0;
            while((length = is.read(buffer)) > 0){
                os.write(buffer,0,length);
            }
            os.flush();
            os.close();
            is.close();
            Log.d("KetNoi", "Đã copy database");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void createDatabase(){
        boolean kt = KiemtraDB();
        if(kt){
            Log.d("KetNoi", "Máy đã có database");
        }else{
            Log.d("KetNoi", "Máy chưa có database, tiến hành copy");
            this.getWritableDatabase();
            copyDatabase();
        }
    }

    public boolean KiemtraDB(){
        Log.d("KetNoi", "Đang kiểm tra database");
        SQLiteDatabase kiemtraDB = null;
        try{
            kiemtraDB = SQLiteDatabase.openDatabase(dataPath,null, SQLiteDatabase.OPEN_READONLY);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(kiemtraDB != null){
            kiemtraDB.close();
        }
        return kiemtraDB != null ? true : false;
    }

    public List<Sinhvien> LayListSV() {
        List<Sinhvien> list = new ArrayList<Sinhvien>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Sinhvien sv=new Sinhvien();
            sv.setMSSV(cursor.getString(cursor.getColumnIndex(KEY_MSSV)));
            sv.setHoten(cursor.getString(cursor.getColumnIndex(KEY_HOTEN)));
            sv.setLop(cursor.getString(cursor.getColumnIndex(KEY_LOP)));

            list.add(sv);
            cursor.moveToNext();
        }
        return list;
    }

    public void AddData(Sinhvien sv){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues vl=new ContentValues();
        vl.put(KEY_MSSV,sv.getMSSV());
        vl.put(KEY_HOTEN, sv.getHoten());
        vl.put(KEY_LOP, sv.getLop());

        if(db.insert(TABLE_NAME, null, vl)!=-1){
            Toast.makeText(context, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

    public void xoaSinhVien(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        if(db.delete(TABLE_NAME, KEY_MSSV + "=?", new String[]{String.valueOf(id)})!=-1){
            Toast.makeText(context, "Xóa sinh viên thành công!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public int capnhatSinhVien(Sinhvien sv){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_MSSV, sv.getMSSV());
        values.put(KEY_HOTEN,sv.getHoten());
        values.put(KEY_LOP,sv.getLop());


        return db.update(TABLE_NAME, values, KEY_MSSV + " =? ", new String[]{String.valueOf(sv.getMSSV())});
    }

    public Sinhvien laySinhvienTheoMa(String mssv){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="select * from " + TABLE_NAME + " where " + KEY_MSSV + " = " + mssv;
        Cursor cursor = db.rawQuery(sql, null);
        Sinhvien  sv=new Sinhvien();
        if(cursor.moveToFirst()){
            sv.setHoten(cursor.getString(cursor.getColumnIndex(KEY_HOTEN)));
            sv.setLop(cursor.getString(cursor.getColumnIndex(KEY_LOP)));
        }
        return sv;
    }
}
