package models;

import java.sql.SQLException;

public class AnnouncementDao extends JDBCConnection {
    public String testConnection(Announcement announcement) throws ClassNotFoundException, SQLException {
        try {

            prepareStat = this.jdbc().prepareStatement("insert into announcement values(?,?,?,?)");
            prepareStat.setString(1,announcement.getId());
            prepareStat.setString(2,announcement.getSessionId());
            prepareStat.setString(3,announcement.getHeading());
            prepareStat.setString(4,announcement.getDate());
            prepareStat.executeUpdate();
            return "Success";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }


}
