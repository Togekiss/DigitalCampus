package com.example.sanfe.digitalcampus.logic.dataManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.logic.data.Exam;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.data.Student;
import com.example.sanfe.digitalcampus.logic.data.Subject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Marta on 29/05/2016.
 */
public class SharedPreferencesManager {

    private static Context context;
    //private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static Gson gson;

    public SharedPreferencesManager(Context context) {
        this.context = context;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    public static void loadSingleton(Resources res) {

        String students = PreferenceManager.getDefaultSharedPreferences(context).getString("StudentsJSON", "");
        Student[] studentArray = gson.fromJson(students, Student[].class);
        ArrayList<Student> studentList = new ArrayList<Student>(Arrays.asList(studentArray));
        Singleton.getInstance().setStudentList(studentList);

        String subjects = PreferenceManager.getDefaultSharedPreferences(context).getString("SubjectsJSON", "");
        ArrayList<Subject> subjectList = new ArrayList<Subject>(Arrays.asList(gson.fromJson(subjects, Subject[].class)));
        Singleton.getInstance().setSubjectList(subjectList);

        String exams = PreferenceManager.getDefaultSharedPreferences(context).getString("ExamsJSON", "");
        ArrayList<Exam> examList = new ArrayList<Exam>(Arrays.asList(gson.fromJson(exams, Exam[].class)));
        Singleton.getInstance().setExamList(examList);

        ArrayList<String> classrooms = new ArrayList<String>(Arrays.asList(res.getStringArray(R.array.classrooms_array)));
        Singleton.getInstance().setClassroomList(classrooms);

        ArrayList<String> careers = new ArrayList<String>(Arrays.asList(res.getStringArray(R.array.careers_array)));
        Singleton.getInstance().setCareerList(careers);

    }


    public static void updateSubjectsJSON() {

        String subjects = gson.toJson(Singleton.getInstance().getSubjectList());
        editor.putString("SubjectsJSON", subjects);
        editor.apply();

    }

    public static void updateStudentsJSON() {

        String students = gson.toJson(Singleton.getInstance().getStudentList());
        editor.putString("StudentsJSON", students);
        editor.apply();

    }

    public static void updateExamsJSON() {

        String exams = gson.toJson(Singleton.getInstance().getExamList());
        editor.putString("ExamsJSON", exams);
        editor.apply();

    }

    public static void setRememberMe(boolean checked) {
        editor.putBoolean("rememberMe", checked);
        editor.apply();
    }

    public static boolean isRememberMe() {
         return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("rememberMe", false);
    }

    public static void setFirstTimeLaunched() {
        editor.putBoolean("firstTimeLaunched", false);
        editor.apply();
    }

    public static boolean isFirstTimeLaunched() {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("firstTimeLaunched", true);
    }

}
