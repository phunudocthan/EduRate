/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DBConnect.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Acer
 */
public class StatisticDAO {
    public ResultSet getAll(){
        Connection conn = DBConnect.getConnection();
        ResultSet rs = null;
        if (conn != null) {
            try {
                Statement st = conn.createStatement();
                rs = st.executeQuery("SELECT [SchoolID], [SchoolName], [ReviewScore] FROM [EduRate].[dbo].[School]"
                                    + "ORDER BY [ReviewScore] DESC;");
            } catch (Exception ex) {
                rs = null;
            }
        }
        return rs;
    }
}
