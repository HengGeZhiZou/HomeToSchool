package com.hts.hometoschool.pojo;

import android.app.Application;



public class HTSApp extends Application {

    private Students students;
    private Teachers teachers;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }

    public String checkRole(){
        if (students!=null){
            return "student";
        }
        else if (teachers!=null){
            return "teacher";
        }
        else {
            return null;
        }
    }
    public void logout(){
        this.setTeachers(null);
        this.setStudents(null);
    }

}
