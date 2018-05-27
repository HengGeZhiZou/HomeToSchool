package com.hts.hometoschool.pojo;


import android.app.Application;

import java.io.Serializable;

public class Students implements Serializable {
    private String stuNum;
    private String stuName;
    private String stuInstitute;
    private String stuSex;
    private String stuGrade;
    private String stuMajor;
    private String stuClass;
    private String password;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }


    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }


    public String getStuInstitute() {
        return stuInstitute;
    }

    public void setStuInstitute(String stuInstitute) {
        this.stuInstitute = stuInstitute;
    }


    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }


    public String getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(String stuGrade) {
        this.stuGrade = stuGrade;
    }


    public String getStuMajor() {
        return stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor = stuMajor;
    }


    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Students students = (Students) o;

        if (stuNum != null ? !stuNum.equals(students.stuNum) : students.stuNum != null) return false;
        if (stuName != null ? !stuName.equals(students.stuName) : students.stuName != null) return false;
        if (stuInstitute != null ? !stuInstitute.equals(students.stuInstitute) : students.stuInstitute != null)
            return false;
        if (stuSex != null ? !stuSex.equals(students.stuSex) : students.stuSex != null) return false;
        if (stuGrade != null ? !stuGrade.equals(students.stuGrade) : students.stuGrade != null) return false;
        if (stuMajor != null ? !stuMajor.equals(students.stuMajor) : students.stuMajor != null) return false;
        if (stuClass != null ? !stuClass.equals(students.stuClass) : students.stuClass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stuNum != null ? stuNum.hashCode() : 0;
        result = 31 * result + (stuName != null ? stuName.hashCode() : 0);
        result = 31 * result + (stuInstitute != null ? stuInstitute.hashCode() : 0);
        result = 31 * result + (stuSex != null ? stuSex.hashCode() : 0);
        result = 31 * result + (stuGrade != null ? stuGrade.hashCode() : 0);
        result = 31 * result + (stuMajor != null ? stuMajor.hashCode() : 0);
        result = 31 * result + (stuClass != null ? stuClass.hashCode() : 0);
        return result;
    }


}
