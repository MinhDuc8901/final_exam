package com.example.final_exame.model;

public class StudentClass {
    private int id;
    private int studentId;
    private int classroomId;
    private String semester;// năm học vd như 2019-2020
    private int credits;// số tín chỉ

    public StudentClass() {
    }

    public StudentClass(int id, int studentId, int classroomId, String semester, int credits) {
        this.id = id;
        this.studentId = studentId;
        this.classroomId = classroomId;
        this.semester = semester;
        this.credits = credits;
    }
    public StudentClass( int studentId, int classroomId, String semester, int credits) {
        this.studentId = studentId;
        this.classroomId = classroomId;
        this.semester = semester;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
