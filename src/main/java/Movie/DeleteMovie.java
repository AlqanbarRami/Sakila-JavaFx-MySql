package Movie;

import Boards.MainBoard;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

public class DeleteMovie extends MainBoard {  // extend from MainBoard to get url,user,pass

    public void MovieDelete(int id) {
        // Jobba här Jag har redan skickat id när man trycker på knappen
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            String FKeyOff = ("SET FOREIGN_KEY_CHECKS=0"); //Off
            String Delete = ("DELETE FROM film Where film_id="+id );
            String FKeyOn = ("SET FOREIGN_KEY_CHECKS=1"); // Key On
            conn.createStatement().executeUpdate(FKeyOff);
            conn.createStatement().executeUpdate(Delete);
            conn.createStatement().executeUpdate(FKeyOn);
            System.out.println("Deleted Now");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
