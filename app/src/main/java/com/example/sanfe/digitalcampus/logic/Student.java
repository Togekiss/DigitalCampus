package com.example.sanfe.digitalcampus.logic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Marta on 06/05/2016.
 */
public class Student {

    private String studentName;
    private Date studentBirthdate;
    private String studentCareer;
    private String  studentGender;
    private int studentImage;
    private ArrayList<Subject> studentSubjects;

    public Student(Date studentBirthdate, String studentCareer, String studentGender, int studentImage, String studentName, ArrayList<Subject> studentSubjects) {
        this.studentBirthdate = studentBirthdate;
        this.studentCareer = studentCareer;
        this.studentGender = studentGender;
        this.studentImage = studentImage;
        this.studentName = studentName;
        this.studentSubjects = studentSubjects;
    }

    public Date getStudentBirthdate() {
        return studentBirthdate;
    }

    public void setStudentBirthdate(Date studentBirthdate) {
        this.studentBirthdate = studentBirthdate;
    }

    public String getStudentCareer() {
        return studentCareer;
    }

    public void setStudentCareer(String studentCareer) {
        this.studentCareer = studentCareer;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public int getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(int studentImage) {
        this.studentImage = studentImage;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public ArrayList<Subject> getStudentSubjects() {
        return studentSubjects;
    }

    public void setStudentSubjects(ArrayList<Subject> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }

}
