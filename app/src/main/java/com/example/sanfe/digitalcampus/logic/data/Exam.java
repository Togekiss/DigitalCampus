package com.example.sanfe.digitalcampus.logic.data;

import java.util.Date;

/**
 * Created by Marta on 06/05/2016.
 */
public class Exam {

    private Date examDate; //both day and hour
    private String examCareer;
    private Subject examSubject;
    private String examClassroom;

    public Exam(String examCareer, String examClassroom, Date examDate, Subject examSubject) {
        this.examCareer = examCareer;
        this.examClassroom = examClassroom;
        this.examDate = examDate;
        this.examSubject = examSubject;
    }

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

    public Subject getExamSubject() {
        return examSubject;
    }

    public void setExamSubject(Subject examSubject) {
        this.examSubject = examSubject;
    }



}
