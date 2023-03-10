package dao;

import config.JDBCConnection;
import models.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao extends JDBCConnection {
    public String addUser(User user) throws ClassNotFoundException, SQLException {
        try {
            PreparedStatement prepareStat = null;
            prepareStat = this.jdbc().prepareStatement("insert into user values(?,?)");
            prepareStat.setString(1,user.getUserName());
            prepareStat.setString(2,user.getPassword());
            prepareStat.executeUpdate();
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
}
