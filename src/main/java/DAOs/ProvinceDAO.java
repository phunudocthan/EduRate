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
import Models.Province;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDAO {
    public List<Province> getAllProvinces() throws SQLException {
        List<Province> provinces = new ArrayList<>();
        String query = "SELECT ProvinceID, ProvinceName FROM Province";
        DBConnect db=new DBConnect();
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Province province = new Province(rs.getInt("ProvinceID"), rs.getString("ProvinceName"));
                provinces.add(province);
            }
        }
        return provinces;
    }
}