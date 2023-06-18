package com.example.final_exame.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.final_exame.model.StudentClass;

import java.util.ArrayList;
import java.util.List;

public class DatabaseStudentClassroom extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudentClassroom";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "student_classroom";

    private static final String KEY_ID = "id";
    private static final String KEY_STUDENT_ID = "student_id";
    private static final String KEY_CLASSROOM_ID = "classroom_id";
    private static final String KEY_SEMESTER = "semester";
    private static final String KEY_CREDITS = "credits";

    public DatabaseStudentClassroom(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                " %s INTEGER,%s INTEGER, %s TEXT,%s INTEGER)", TABLE_NAME, KEY_ID,KEY_STUDENT_ID,
                KEY_CLASSROOM_ID, KEY_SEMESTER,KEY_CREDITS);
        sqLiteDatabase.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        sqLiteDatabase.execSQL(drop_students_table);

        onCreate(sqLiteDatabase);
    }

    public void addStudentClassroom (StudentClass studentClass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_ID,studentClass.getStudentId());
        values.put(KEY_CLASSROOM_ID,studentClass.getClassroomId());
        values.put(KEY_SEMESTER,studentClass.getSemester());
        values.put(KEY_CREDITS,studentClass.getCredits());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public List<StudentClass> getListStudentClass(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<StudentClass> studentClasses = new ArrayList<>();

        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query,null);
        }catch (Exception e){
            e.printStackTrace();
            return studentClasses;
        }
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            StudentClass studentClass = new StudentClass(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),
                    cursor.getString(3),cursor.getInt(4));
            studentClasses.add(studentClass);
            cursor.moveToNext();
        }
        return studentClasses;
    }
}
