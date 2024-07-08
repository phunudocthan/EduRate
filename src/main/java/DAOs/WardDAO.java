package DAOs;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Bang
 */

import DBConnect.DBConnect;
import Models.Ward;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WardDAO {
    public List<Ward> getWardsByDistrictId(int districtId) throws SQLException {
        List<Ward> wards = new ArrayList<>();
        String query = "SELECT WardID, WardName FROM Ward WHERE DistrictID = ?";
        DBConnect db=new DBConnect();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, districtId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ward ward = new Ward(rs.getInt("WardID"), rs.getString("WardName"), districtId);
                    wards.add(ward);
                }
            }
        }
        return wards;
    }
}