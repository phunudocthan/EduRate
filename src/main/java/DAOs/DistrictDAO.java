/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

/**
 *
 * @author Bang
 */


import DBConnect.DBConnect;
import Models.District;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DistrictDAO {
  

    public List<District> getDistrictsByProvinceId(int provinceId)throws SQLException {
        List<District> districts = new ArrayList<>();
        String query = "SELECT DistrictID, DistrictName FROM District WHERE ProvinceID = ?";
        DBConnect db=new DBConnect();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, provinceId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    District district = new District(rs.getInt("DistrictID"), rs.getString("DistrictName"), provinceId);
                    districts.add(district);
                }
            }
        }
        return districts;
    }
}