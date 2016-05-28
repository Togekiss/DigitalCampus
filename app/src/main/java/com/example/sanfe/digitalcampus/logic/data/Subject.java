package com.example.sanfe.digitalcampus.logic.data;

import com.example.sanfe.digitalcampus.logic.data.Student;

import java.io.Serializable;
import java.util.ArrayList;

public class Subject implements Serializable{


    private String subjectTitle;
    private String subjectDescription;
    private int subjectImage;
    private ArrayList<Student> subjectStudents;
    private ArrayList<String> subjectThemes;

    public Subject () {
        subjectTitle = new String();
        subjectDescription = new String();
        subjectStudents = new ArrayList<>();
        subjectThemes = new ArrayList<>();
    }

    public Subject(String subjectDescription, int subjectImage, ArrayList<Student> subjectStudents, ArrayList<String> subjectThemes, String subjectTitle) {
        this.subjectDescription = subjectDescription;
        this.subjectImage = subjectImage;
        this.subjectStudents = subjectStudents;
        this.subjectThemes = subjectThemes;
        this.subjectTitle = subjectTitle;
    }

    public Subject (String subjectTitle, String subjectDescription) {
        this.subjectTitle = subjectTitle;
        this.subjectDescription = subjectDescription;
    }

    public Subject (int subjectImage, String subjectTitle, String subjectDescription) {
        this.subjectImage = subjectImage;
        this.subjectTitle = subjectTitle;
        this.subjectDescription = subjectDescription;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public int getSubjectImage() {
        return subjectImage;
    }

    public void setSubjectImage(int subjectImage) {
        this.subjectImage = subjectImage;
    }

    public ArrayList<Student> getSubjectStudents() {
        return subjectStudents;
    }

    public void setSubjectStudents(ArrayList<Student> subjectStudents) {
        this.subjectStudents = subjectStudents;
    }

    public ArrayList<String> getSubjectThemes() {
        return subjectThemes;
    }

    public void setSubjectThemes(ArrayList<String> subjectThemes) {
        this.subjectThemes = subjectThemes;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }



}
