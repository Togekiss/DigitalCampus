package com.example.sanfe.digitalcampus.logic.data;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Student implements Serializable{

    private String studentName;
    private Date studentBirthdate;
    private String studentCareer;
    private String  studentGender;
    private int studentImage;
    private ArrayList<String> studentSubjects;

    public Student(Date studentBirthdate, String studentCareer, String studentGender, int studentImage, String studentName, ArrayList<String> studentSubjects) {
        this.studentBirthdate = studentBirthdate;
        this.studentCareer = studentCareer;
        this.studentGender = studentGender;
        this.studentImage = studentImage;
        this.studentName = studentName;
        this.studentSubjects = studentSubjects;
    }

    public Student (int studentImage, String studentName) {
        this.studentImage = studentImage;
        this.studentName = studentName;
    }

    public Student () {}

    public Date getStudentBirthdate() {
        return studentBirthdate;
    }

    public int getStudentAge () {
        Date date = new Date();
        Long time = date.getTime() / 1000 - studentBirthdate.getTime() / 1000;

        return Math.round(time) / 31536000;
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

    public ArrayList<String> getStudentSubjects() {
        return studentSubjects;
    }

    public void setStudentSubjects(ArrayList<String> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }

    public void addStudentSubject(String subject) {
        this.studentSubjects.add(subject);
    }

    public void removeStudentSubject (String subject) {
        this.studentSubjects.remove(subject);
    }

}
