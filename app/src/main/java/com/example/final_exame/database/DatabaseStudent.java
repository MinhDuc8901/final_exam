package com.example.final_exame.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.final_exame.model.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseStudent extends SQLiteOpenHelper {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String DATABASE_NAME = "StudentClassroom";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_BIRTH = "birth";
    private static final String KEY_PLACE = "place";
    private static final String KEY_YEAR_STUDENT = "year_stu";

    public DatabaseStudent(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT,%s DATE, %s TEXT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_BIRTH, KEY_PLACE, KEY_YEAR_STUDENT);
        sqLiteDatabase.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        sqLiteDatabase.execSQL(drop_students_table);

        onCreate(sqLiteDatabase);
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_BIRTH, dateFormat.format(student.getBirth()));
        values.put(KEY_PLACE, student.getPlace());
        values.put(KEY_YEAR_STUDENT,student.getYear_stu());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Student> getListStudent() throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Student> students = new ArrayList<>();
        String query = "Select * from "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            Student student = new Student(cursor.getInt(0),cursor.getString(1),dateFormat.parse(cursor.getString(2)),cursor.getString(3),cursor.getInt(4));
            students.add(student);
            cursor.moveToNext();
        }
        return students;
    }

    public Student getStudent(int id) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+ TABLE_NAME + " where " + KEY_ID + " = " + id;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        Student student = new Student(cursor.getInt(0),cursor.getString(1),dateFormat.parse(cursor.getString(2)),cursor.getString(3),cursor.getInt(4));
        return student;
    }

}
