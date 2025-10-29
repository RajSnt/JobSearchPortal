package Applicant;

import java.sql.*;

public class ConnA {
    Connection c;
    Statement s;
    ConnA(){
        try {
            c= DriverManager.getConnection("jdbc:mysql:///JobSearchPortal", "root", "Raj2712@1.");
            s = c.createStatement();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
