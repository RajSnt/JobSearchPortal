import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    Conn(){
        try {
            c= DriverManager.getConnection("jdbc:mysql:///JobSearchPortal", "root", "Raj2712@1.");
            s = c.createStatement();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}