package com.example.studentlistv2.model;

public class Student {
    private int id;
    private String name;
    private String surname;
    private String group;
    private String university;

    public Student() {
    }

    public Student(String name, String surname, String group, String university) {
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.university = university;
    }

    public Student(int id, String name, String surname, String group, String university) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.university = university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
