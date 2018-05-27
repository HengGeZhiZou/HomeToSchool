package com.hts.hometoschool.pojo;


import java.io.Serializable;

public class Project implements Serializable {
    private String proId;
    private String stuId;
    private String proName;
    private String proTeac;
    private String proTeamer;
    private String proType;
    private String proIntroduce;
    private String proPic;
    private int proGood;

    @Override
    public String toString() {
        return "Project{" +
                "proId='" + proId + '\'' +
                ", stuId='" + stuId + '\'' +
                ", proName='" + proName + '\'' +
                ", proTeac='" + proTeac + '\'' +
                ", proTeamer='" + proTeamer + '\'' +
                ", proType='" + proType + '\'' +
                ", proIntroduce='" + proIntroduce + '\'' +
                ", proPic='" + proPic + '\'' +
                ", proGood=" + proGood +
                '}';
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProTeac() {
        return proTeac;
    }

    public void setProTeac(String proTeac) {
        this.proTeac = proTeac;
    }

    public String getProTeamer() {
        return proTeamer;
    }

    public void setProTeamer(String proTeamer) {
        this.proTeamer = proTeamer;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getProIntroduce() {
        return proIntroduce;
    }

    public void setProIntroduce(String proIntroduce) {
        this.proIntroduce = proIntroduce;
    }

    public String getProPic() {
        return proPic;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic;
    }

    public int getProGood() {
        return proGood;
    }

    public void setProGood(int proGood) {
        this.proGood = proGood;
    }
}
