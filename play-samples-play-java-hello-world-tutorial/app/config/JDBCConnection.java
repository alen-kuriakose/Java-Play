package config;

import java.sql.*;



public class JDBCConnection {
    Connection con = null;
    Statement statment = null;
    PreparedStatement prepareStat = null;
    ResultSet result = null;

    public Connection jdbc() throws ClassNotFoundException, SQLException {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/announcement?autoReconnect=true&useSSL=false","root","Welcome@123");
        } catch (SQLException e) {
            System.out.println("error :" +e.getMessage());
            return null;
        }
    }


}
