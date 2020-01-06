package com.example.examorder;

public class Student {
    private int ID;
    private String name;
    private int sgroup;
    private String subject;
    private int position;
    private int mark;

    public Student(int ID, String name, int sgroup, String subject, int position, int mark){
        this.ID = ID;
        this.name = name;
        this.sgroup = sgroup;
        this.subject = subject;
        this.position = position;
        this.mark = mark;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSgroup() {
        return this.sgroup;
    }

    public void setSgroup(int sgroup) {
        this.sgroup = sgroup;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMark() {
        return this.mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
