package com.example.hoang.qlsv;

/**
 * Created by Hoang on 10/04/2015.
 */
public class Sinhvien {
    private String MSSV;
    private String Hoten;
    private String Lop;

    public Sinhvien() {
    }

    public Sinhvien(String lop, String MSSV, String hoten) {
        Lop = lop;
        this.MSSV = MSSV;
        Hoten = hoten;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String lop) {
        Lop = lop;
    }
}
