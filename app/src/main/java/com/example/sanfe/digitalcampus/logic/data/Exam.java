package com.example.sanfe.digitalcampus.logic.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marta on 06/05/2016.
 */
public class Exam implements Serializable{

    private Date examDate; //both day and hour
    private String examCareer;
    private String examSubject;
    private String examClassroom;

    public Exam(String examCareer, String examClassroom, Date examDate, String examSubject) {
        this.examCareer = examCareer;
        this.examClassroom = examClassroom;
        this.examDate = examDate;
        this.examSubject = examSubject;
    }

    public Exam(){}

    public String getExamCareer() {
        return examCareer;
    }

    public void setExamCareer(String examCareer) {
        this.examCareer = examCareer;
    }

    public String getExamClassroom() {
        return examClassroom;
    }

    public void setExamClassroom(String examClassroom) {
        this.examClassroom = examClassroom;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getExamSubject() {
        return examSubject;
    }

    public void setExamSubject(String examSubject) {
        this.examSubject = examSubject;
    }



}
