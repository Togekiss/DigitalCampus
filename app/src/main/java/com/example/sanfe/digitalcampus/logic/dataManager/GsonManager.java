package com.example.sanfe.digitalcampus.logic.dataManager;

import android.content.res.AssetManager;
import android.content.res.Resources;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.logic.data.Exam;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.data.Student;
import com.example.sanfe.digitalcampus.logic.data.Subject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Usar sólo en caso de primera ejecución
 * para sacar datos de los archivos y
 * cargarlos en SharedPreferences.
 * Created by Marta on 10/05/2016.
 */
public class GsonManager {

    public static void loadSingleton(Resources res, AssetManager assets) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        try {
            String students = loadJSONFromAsset(assets.open("students.json"));
            Student[] studentArray = gson.fromJson(students, Student[].class);
            ArrayList<Student> studentList = new ArrayList<Student>(Arrays.asList(studentArray));
            Singleton.getInstance().setStudentList(studentList);

            String subjects = loadJSONFromAsset(assets.open("subjects.json"));
            ArrayList<Subject> subjectList = new ArrayList<Subject>(Arrays.asList(gson.fromJson(subjects, Subject[].class)));
            Singleton.getInstance().setSubjectList(subjectList);

            String exams = loadJSONFromAsset(assets.open("exams.json"));
            ArrayList<Exam> examList = new ArrayList<Exam>(Arrays.asList(gson.fromJson(exams, Exam[].class)));
            Singleton.getInstance().setExamList(examList);

        } catch (IOException e) {}

        ArrayList<String> classrooms = new ArrayList<String>(Arrays.asList(res.getStringArray(R.array.classrooms_array)));
        Singleton.getInstance().setClassroomList(classrooms);

        ArrayList<String> careers = new ArrayList<String>(Arrays.asList(res.getStringArray(R.array.careers_array)));
        Singleton.getInstance().setCareerList(careers);

    }

    private static String loadJSONFromAsset(InputStream is) {
        String json = null;
        try {
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
