/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

public class User {

    private String FirstName;
    private String LastName;
    private String UserName;
    private String Password;
    private Date BirthDay;
    private int TagID;
    private int GenderID;
    private int ProvinceID;
    private int DistrictID;
    private int WardID;
    private String Picture;

    public User() {
    }

    public User(String FirstName, String LastName, String UserName, String Password, Date BirthDay, int TagID, int GenderID, int ProvinceID, int DistrictID, int WardID, String Picture) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.UserName = UserName;
        this.Password = Password;
        this.BirthDay = BirthDay;
        this.TagID = TagID;
        this.GenderID = GenderID;
        this.ProvinceID = ProvinceID;
        this.DistrictID = DistrictID;
        this.WardID = WardID;
        this.Picture = Picture;
    }

    // Getters và setters
    public String getPicture() {
        return Picture;
    }

    public void setPicture(String Picture) {
        this.Picture = Picture;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Date getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(Date BirthDay) {
        this.BirthDay = BirthDay;
    }

    public int getTagID() {
        return TagID;
    }

    public void setTagID(int TagID) {
        this.TagID = TagID;
    }

    public int getGenderID() {
        return GenderID;
    }

    public void setGenderID(int GenderID) {
        this.GenderID = GenderID;
    }

    public int getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(int ProvinceID) {
        this.ProvinceID = ProvinceID;
    }

    public int getDistrictID() {
        return DistrictID;
    }

    public void setDistrictID(int DistrictID) {
        this.DistrictID = DistrictID;
    }

    public int getWardID() {
        return WardID;
    }

    public void setWardID(int WardID) {
        this.WardID = WardID;
    }
}
