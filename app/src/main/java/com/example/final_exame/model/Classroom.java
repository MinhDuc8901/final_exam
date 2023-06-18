package com.example.final_exame.model;

public class Classroom {
    private int id;
    private String class_index;
    private String name;
    private String description;

    public Classroom() {
    }

    public Classroom(int id, String class_index, String name, String description) {
        this.id = id;
        this.class_index = class_index;
        this.name = name;
        this.description = description;
    }

    public Classroom( String class_index, String name, String description) {
        this.class_index = class_index;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClass_index() {
        return class_index;
    }

    public void setClass_index(String class_index) {
        this.class_index = class_index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
