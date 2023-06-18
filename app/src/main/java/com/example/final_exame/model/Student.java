package com.example.final_exame.model;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private int id;
    private String name;
    private Date birth;
    private String place;
    private int year_stu;

    public Student() {
    }

    public Student(int id, String name, Date birth, String place, int year_stu) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.place = place;
        this.year_stu = year_stu;
    }

    public Student( String name, Date birth, String place, int year_stu) {
        this.name = name;
        this.birth = birth;
        this.place = place;
        this.year_stu = year_stu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getYear_stu() {
        return year_stu;
    }

    public void setYear_stu(int year_stu) {
        this.year_stu = year_stu;
    }
}
