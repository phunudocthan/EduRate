/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DBConnect.DBConnect;
import Models.School;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Bang
 */
public class SchoolDAO extends DBConnect {

    public boolean updateRatingInDatabase(String schoolId, int rating) {
        DBConnect db = new DBConnect();
        String getData = "Select ReviewCount,ScoreTotal from School WHERE SchoolID=?;";
        String updateData = "UPDATE School\n"
                + "SET ReviewCount=?, ScoreTotal = ?,ReviewScore=ROUND(?, 1)\n"
                + "WHERE SchoolID=?;";
        int rowsUpdated = 0;
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(getData);
            stmt.setString(1, schoolId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("ReviewCount") + 1;
                int totalScore = rs.getInt("ScoreTotal") + rating;
                float ReviewScore = (float) totalScore / count;

                PreparedStatement st = conn.prepareStatement(updateData);
                st.setInt(1, count);
                st.setInt(2, totalScore);
                st.setFloat(3, ReviewScore);
                st.setString(4, schoolId);
                rowsUpdated = st.executeUpdate();
            }
            stmt.close();
            conn.close();

            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int updateSchoolByID(String id, School school) {
        int count = 0;
        DBConnect db = new DBConnect();
        String sql = "UPDATE School\n"
                + "SET SchoolName = ?, \n"
                + "    EstablishedDate = ?, \n"
                + "    TotalStudents = ?,          \n"
                + "    Website =?, \n"
                + "    ProvinceID = ?,                \n"
                + "    DistrictID = ?,               \n"
                + "    WardID = ?,                    \n"
                + "    Picture = ?,    \n"
                + "    [Description] =? \n"
                + "WHERE SchoolID = ?;  ";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, school.getSchoolName());
            stmt.setDate(2, school.getEstablishedDate());
            stmt.setInt(3, school.getTotalStudents());
            stmt.setString(4, school.getWebsite());
            stmt.setInt(5, school.getProvinceID());
            stmt.setInt(6, school.getDistrictID());
            stmt.setInt(7, school.getWardID());
            stmt.setString(8, school.getPicture());
            stmt.setString(9, school.getDescription());
            stmt.setString(10, id);

            count = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public ResultSet getSchoolByID(String id) {
        Connection conn = DBConnect.getConnection();
        ResultSet rs = null;
        try {
            String sql = "SELECT \n"
                    + "    s.SchoolID,\n"
                    + "    s.SchoolName,\n"
                    + "    s.EstablishedDate,\n"
                    + "    s.TotalStudents,\n"
                    + "    s.Website,\n"
                    + "    s.ReviewScore,\n"
                    + "    s.Picture,\n"
                    + "    s.[Description],\n"
                    + "    st.TypeID,\n"
                    + "    st.[Type],\n"
                    + "    p.ProvinceID,\n"
                    + "    p.ProvinceName,\n"
                    + "    ds.DistrictID,\n"
                    + "    ds.DistrictName,\n"
                    + "    w.WardID,\n"
                    + "    w.WardName\n"
                    + "FROM \n"
                    + "    School s\n"
                    + "JOIN \n"
                    + "    Province p ON s.ProvinceID = p.ProvinceID\n"
                    + "JOIN \n"
                    + "    Ward w ON s.WardID = w.WardID\n"
                    + "JOIN \n"
                    + "    District ds ON s.DistrictID = ds.DistrictID\n"
                    + "JOIN \n"
                    + "    SchoolType st ON s.SchoolTypeID = st.TypeID\n"
                    + "WHERE \n"
                    + "    s.SchoolID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int Addnew(School school) {
        int count = 0;
        DBConnect db = new DBConnect();
        String sql = "INSERT INTO [EduReview].[dbo].[School] \n"
                + "(\n"
                + "    [SchoolID],\n"
                + "    [SchoolName],\n"
                + "    [EstablishedDate],\n"
                + "    [TotalStudents],\n"
                + "    [Website],\n"
                + "    [ReviewScore],\n"
                + "    [ProvinceID],\n"
                + "    [DistrictID],\n"
                + "    [WardID],\n"
                + "    [SchoolTypeID],\n"
                + "    [Picture],\n"
                + "    [Description],\n"
                + "    [ReviewCount],\n"
                + "    [ScoreTotal]\n"
                + ")\n"
                + "VALUES \n"
                + "(\n"
                + "    ?,  \n"
                + // SchoolID
                "    ?,  \n"
                + // SchoolName
                "    ?,  \n"
                + // EstablishedDate
                "    ?,  \n"
                + // TotalStudents
                "    ?,  \n"
                + // Website
                "    ?,  \n"
                + // ReviewScore (if calculated in Java code)
                "    ?,  \n"
                + // ProvinceID
                "    ?,  \n"
                + // DistrictID
                "    ?,  \n"
                + // WardID
                "    ?,  \n"
                + // SchoolTypeID
                "    ?,  \n"
                + // Picture
                "    ?,  \n"
                + // Description
                "    ?,  \n"
                + // ReviewCount (if available)
                "    ?   \n"
                + // ScoreTotal (if available)
                ")";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, school.getSchoolID());
            stmt.setString(2, school.getSchoolName());
            stmt.setDate(3, school.getEstablishedDate());  // Assuming EstablishedDate is a java.util.Date
            stmt.setInt(4, school.getTotalStudents());
            stmt.setString(5, school.getWebsite());
            // Calculate or set ReviewScore based on your logic
            stmt.setFloat(6, school.getReviewScore());
            stmt.setInt(7, school.getProvinceID());
            stmt.setInt(8, school.getDistrictID());
            stmt.setInt(9, school.getWardID());
            stmt.setInt(10, school.getSchoolTypeID());
            stmt.setString(11, school.getPicture());
            stmt.setString(12, school.getDescription());
            stmt.setInt(13, school.getReviewCount());
            stmt.setInt(14, school.getScoreTotal());

            count = stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int Delete(String id) {
        int count = 0;
        DBConnect db = new DBConnect();
        String sql = "DELETE FROM [EduReview].[dbo].[School]\n"
                + "WHERE SchoolID = ?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id); // Set parameter for SchoolID
            count = stmt.executeUpdate(); // Execute the delete statement
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
