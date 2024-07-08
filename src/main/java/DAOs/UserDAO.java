package DAOs;

import DBConnect.DBConnect;
import Models.MD5Hashing;
import Models.User;
import static Models.VerifyCode.generateVerificationCode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DBConnect {

    public int addUser(User user) {
        String insertUserSQL = "INSERT INTO [User] (FirstName, LastName, UserName, Password, BirthDay, TagID, GenderID, ProvinceID, DistrictID, WardID, Picture, RoleID) "
                + "VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, 1)";
        try ( Connection conn = getConnection();  PreparedStatement insert = conn.prepareStatement(insertUserSQL)) {
            String endCodePass = MD5Hashing.hash(user.getPassword());
            insert.setString(1, user.getFirstName());
            insert.setString(2, user.getLastName());
            insert.setString(3, user.getUserName());
            insert.setString(4, endCodePass);
            insert.setDate(5, user.getBirthDay());
            insert.setInt(6, user.getTagID());
            insert.setInt(7, user.getGenderID());
            insert.setInt(8, user.getProvinceID());
            insert.setInt(9, user.getDistrictID());
            insert.setInt(10, user.getWardID());
            insert.setString(11, user.getPicture());

            return insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getUserId(String userName) {
        String selectUserSQL = "SELECT UserID FROM [User] WHERE username = ?";
        try ( Connection conn = getConnection();  PreparedStatement selectID = conn.prepareStatement(selectUserSQL)) {

            selectID.setString(1, userName);
            try ( ResultSet rs = selectID.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("UserID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int addEmail(String email, String userId) {
        String insertEmailSQL = "INSERT INTO Email (EmailAddress, UserID, VerificationCode) VALUES (?, ?, ?)";
        try ( Connection conn = getConnection();  PreparedStatement insertEmail = conn.prepareStatement(insertEmailSQL)) {

            insertEmail.setString(1, email);
            insertEmail.setString(2, userId);
            insertEmail.setString(3, generateVerificationCode()); // Assuming you have a method to generate a verification code
            return insertEmail.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int addPhone(String phone, String userId) {
        String insertPhoneSQL = "INSERT INTO Phone (PhoneNum, UserID) VALUES (?, ?)";
        try ( Connection conn = getConnection();  PreparedStatement insertPhone = conn.prepareStatement(insertPhoneSQL)) {

            insertPhone.setString(1, phone);
            insertPhone.setString(2, userId);
            return insertPhone.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int CheckExitsValue(User user, String phone, String email) {
        int count = 0;
        String selectUserName = "SELECT UserName FROM [User] WHERE UserName = ?";
        String selectPhone = "SELECT PhoneNum FROM Phone WHERE PhoneNum = ?";
        String selectEmail = "SELECT EmailAddress FROM Email WHERE EmailAddress = ?";

        try ( Connection conn = getConnection();  PreparedStatement stUserName = conn.prepareStatement(selectUserName);  PreparedStatement stPhone = conn.prepareStatement(selectPhone);  PreparedStatement stEmail = conn.prepareStatement(selectEmail)) {

            stUserName.setString(1, user.getUserName());
            try ( ResultSet userRS = stUserName.executeQuery()) {
                if (userRS.next()) {
                    count = 1;
                }
            }

            stPhone.setString(1, phone);
            try ( ResultSet phoneRS = stPhone.executeQuery()) {
                if (phoneRS.next()) {
                    count = 2;
                }
            }

            stEmail.setString(1, email);
            try ( ResultSet emailRS = stEmail.executeQuery()) {
                if (emailRS.next()) {
                    count = 3;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public ResultSet getUserbyId(String userid) throws SQLException {
        ResultSet rs = null;
        String sql = "Select us.FirstName,us.LastName,us.BirthDay,tc.TagID,tc.TagCategory,g.GenderID,g.Gender,p.ProvinceID,p.ProvinceName,ds.DistrictID,ds.DistrictName,w.WardID,w.WardName,us.Picture,ph.PhoneNum,e.EmailAddress from [User] us join Province p on us.ProvinceID=p.ProvinceID\n"
                + "                        Join District ds on us.DistrictID= ds.DistrictID\n"
                + "						join Ward w on  us.WardID=w.WardID\n"
                + "						Join Gender g on us.GenderID=g.GenderID\n"
                + "						join TagCategory tc on us.TagID=tc.TagID\n"
                + "						Left Join Phone ph on us.UserID =ph.UserID\n" 
                + "                                             left Join Email e on us.UserID=e.UserID\n"
                + "                                              where us.UserName=?";
        Connection conn = DBConnect.getConnection();
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, userid);
        rs = st.executeQuery();
        return rs;
    }

}
