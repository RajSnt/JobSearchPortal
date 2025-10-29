package LoginPage;
import java.sql.*;

public class ConnL {
    public Connection c;
    public Statement s;
    public ConnL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // optional but recommended
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/JobSearchPortal", "root", "Raj2712@1.");
            s = c.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
