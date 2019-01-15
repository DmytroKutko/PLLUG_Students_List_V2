package com.example.studentlistv2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.studentlistv2.model.Student;

public class MyDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "students.db";
    public static final String TABLE_NAME = "student";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "SURNAME";
    public static final String COL4 = "SGROUP";
    public static final String COL5 = "UNIVERSITY";

    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, SURNAME TEXT, SGROUP TEXT, UNIVERSITY TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(Student student) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("NAME", student.getName());
        contentValues.put("SURNAME", student.getSurname());
        contentValues.put("SGROUP", student.getGroup());
        contentValues.put("UNIVERSITY", student.getUniversity());

        database.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getData() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        return cursor;
    }

    public void deleteAll() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public void deleteById(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COL1 + " = " + id);
    }

    public Cursor getDataById(int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL1 + " = " + id, null);
        return cursor;
    }

    public void updateStudent(Student student) {
        SQLiteDatabase database = this.getWritableDatabase();
        int id = student.getId();
        String name = student.getName();
        String surname = student.getSurname();
        String group = student.getGroup();
        String university = student.getUniversity();
        database.execSQL("UPDATE " + TABLE_NAME + " SET " +
                COL2 + " = " + name + " , " +
                COL3 + " = " + surname + " , " +
                COL4 + " = " + group + " , " +
                COL5 + " = " + university +
                " WHERE " + COL1 + " = " + id
        );
    }
}
