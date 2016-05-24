package com.example.sanfe.digitalcampus.logic.data;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Marta on 17/05/2016.
 */
public class Singleton {

    private static Singleton mInstance = null;

    private ArrayList<Student> studentList;
    private ArrayList<Subject> subjectList;
    private ArrayList<Exam> examList;
    private ArrayList<String> classroomList;
    private ArrayList<String> careerList;

    private Singleton () {
        studentList = new ArrayList<Student>();
        subjectList = new ArrayList<Subject>();
        examList = new ArrayList<Exam>();
        classroomList = new ArrayList<String>();
        careerList = new ArrayList<String>();
    }

    public static Singleton getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton();
        }
        return mInstance;
    }

    public ArrayList<String> getCareerList() {
        return careerList;
    }

    public void setCareerList(ArrayList<String> careerList) {
        this.careerList = careerList;
    }

    public ArrayList<String> getClassroomList() {
        return classroomList;
    }

    public void setClassroomList(ArrayList<String> classroomList) {
        this.classroomList = classroomList;
    }

    public ArrayList<Exam> getExamList() {
        return examList;
    }

    public void setExamList(ArrayList<Exam> examList) {
        this.examList = examList;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public ArrayList<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(ArrayList<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public void addStudent (Student student) {
        this.studentList.add(student);
    }

    public void removeStudent(Student student) {
        this.studentList.remove(student);
    }

    public void addSubject (Subject subject) {
        this.subjectList.add(subject);
    }

    public void removeSubject (Subject subject) {
        this.subjectList.remove(subject);
    }

    public void addExam (Exam exam) {
        this.examList.add(exam);
    }

    public void removeExam (Exam exam) {
        this.examList.remove(exam);
    }

}
