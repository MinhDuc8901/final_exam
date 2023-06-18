package com.example.final_exame.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.final_exame.model.Classroom;
import com.example.final_exame.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DatabaseClassroom extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudentClassroom";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "classrooms";

    private static final String KEY_ID = "id";
    private static final String KEY_CLASS_ID = "class_index";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";

    public DatabaseClassroom(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT,%s TEXT, %s TEXT)", TABLE_NAME, KEY_ID,KEY_CLASS_ID, KEY_NAME, KEY_DESCRIPTION);
        sqLiteDatabase.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        sqLiteDatabase.execSQL(drop_students_table);

        onCreate(sqLiteDatabase);
    }

    public void addClassroom(Classroom classroom){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CLASS_ID,classroom.getClass_index());
        values.put(KEY_NAME,classroom.getName());
        values.put(KEY_DESCRIPTION,classroom.getDescription());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public List<Classroom> getListClassroom(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Classroom> classrooms = new ArrayList<>();
        String query = "Select * from "+ TABLE_NAME;
        Cursor cursor = null;
        try{
            cursor = db.rawQuery(query,null);
        }catch (Exception e){
            e.printStackTrace();
            return classrooms;
        }
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            Classroom classroom = new Classroom(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            classrooms.add(classroom);
            cursor.moveToNext();
        }
        return classrooms;
    }

    public List<Classroom> getClassrooms(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Classroom> classrooms = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = null;
        try{
             cursor = db.rawQuery(query,null);
        }catch (Exception e){
            e.printStackTrace();
            return classrooms;
        }
        cursor.moveToFirst();


        while(cursor.isAfterLast() == false){
            Classroom classroom = new Classroom(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3));
            classrooms.add(classroom);
            cursor.moveToNext();
        }
        return classrooms;
    }

    public Classroom getClassroom(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+ TABLE_NAME + " where "  + KEY_ID + " = " + id;
        Cursor cursor = null;
        try{
            cursor = db.rawQuery(query,null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        cursor.moveToFirst();
        Classroom classroom = new Classroom(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                cursor.getString(3));
        return classroom;
    }

}
