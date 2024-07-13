/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author Bang
 */
public class School {

    private String SchoolID;
    private String SchoolName;
    private Date EstablishedDate;
    private int TotalStudents;
    private String Website;
    private float ReviewScore;
    private int provinceID;
    private int districtID;
    private int wardID;
    private int SchoolTypeID;
    private String Picture;
    private String Description;
    private int ReviewCount;
    private int ScoreTotal;

    public School() {
    }

    public School(String SchoolID, String SchoolName, Date EstablishedDate, int TotalStudents, String Website,int provinceID, int districtID, int wardID, int SchoolTypeID, String Picture, String Description,float ReviewScore, int ReviewCount, int ScoreTotal) {
        this.SchoolID = SchoolID;
        this.SchoolName = SchoolName;
        this.EstablishedDate = EstablishedDate;
        this.TotalStudents = TotalStudents;
        this.Website = Website;
        this.ReviewScore = ReviewScore;
        this.provinceID = provinceID;
        this.districtID = districtID;
        this.wardID = wardID;
        this.SchoolTypeID = SchoolTypeID;
        this.Picture = Picture;
        this.Description = Description;
        this.ReviewCount = ReviewCount;
        this.ScoreTotal = ScoreTotal;
    }

    public String getSchoolID() {
        return SchoolID;
    }

    public void setSchoolID(String SchoolID) {
        this.SchoolID = SchoolID;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String SchoolName) {
        this.SchoolName = SchoolName;
    }

    public Date getEstablishedDate() {
        return EstablishedDate;
    }

    public void setEstablishedDate(Date EstablishedDate) {
        this.EstablishedDate = EstablishedDate;
    }

    public int getTotalStudents() {
        return TotalStudents;
    }

    public void setTotalStudents(int TotalStudents) {
        this.TotalStudents = TotalStudents;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String Website) {
        this.Website = Website;
    }

    public float getReviewScore() {
        return ReviewScore;
    }

    public void setReviewScore(float ReviewScore) {
        this.ReviewScore = ReviewScore;
    }

    public int getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

    public int getWardID() {
        return wardID;
    }

    public void setWardID(int wardID) {
        this.wardID = wardID;
    }

    public int getSchoolTypeID() {
        return SchoolTypeID;
    }

    public void setSchoolTypeID(int SchoolTypeID) {
        this.SchoolTypeID = SchoolTypeID;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String Picture) {
        this.Picture = Picture;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getReviewCount() {
        return ReviewCount;
    }

    public void setReviewCount(int ReviewCount) {
        this.ReviewCount = ReviewCount;
    }

    public int getScoreTotal() {
        return ScoreTotal;
    }

    public void setScoreTotal(int ScoreTotal) {
        this.ScoreTotal = ScoreTotal;
    }

}
