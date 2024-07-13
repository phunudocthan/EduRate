/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DBConnect.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Bang
 */
public class SchoolDAO extends DBConnect{

     public boolean updateRatingInDatabase(String schoolId, int rating) {
        DBConnect db = new DBConnect();
        String getData = "Select ReviewCount,ScoreTotal from School WHERE SchoolID=?;";
        String updateData = "UPDATE School\n"
                + "SET ReviewCount=?, ScoreTotal = ?,ReviewScore=?\n"
                + "WHERE SchoolID=?;";
        int rowsUpdated=0;
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(getData);
            stmt.setString(1, schoolId);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                int count=rs.getInt("ReviewCount")+1;
                int totalScore=rs.getInt("ScoreTotal")+rating;
                int ReviewScore=totalScore/count;
                
            PreparedStatement st = conn.prepareStatement(updateData);
            st.setInt(1, count);
            st.setInt(2, totalScore);
            st.setInt(3, ReviewScore);
            st.setString(4, schoolId);
            rowsUpdated=st.executeUpdate();
            }
            stmt.close();
            conn.close();

            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
