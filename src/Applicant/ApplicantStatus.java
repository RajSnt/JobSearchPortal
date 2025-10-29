package Applicant;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ApplicantStatus extends JFrame {
    public ApplicantStatus(String username) {
        setTitle("Your Application Status");
        setSize(600, 400);
        setLocation(500, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 15));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(20, 20, 540, 320);
        add(scroll);

        try {
            ConnA c = new ConnA();
            String query = "SELECT * FROM applied WHERE username='" + username + "'";
            ResultSet rs = c.s.executeQuery(query);

            StringBuilder info = new StringBuilder("Your Applications:\n\n");
            while (rs.next()) {
                String apNo = rs.getString("ApNo");
                String position = rs.getString("position");
                String qualification = rs.getString("qualification");
                String degree = rs.getString("degree");
                String stat = rs.getString("stat");
                info.append("ApNo: ").append(apNo).append("\n");
                info.append("Position: ").append(position).append("\n");
                info.append("Qualification: ").append(qualification).append("\n");
                info.append("Degree: ").append(degree).append("\n");
                info.append("Status: ").append(stat).append("\n");
                info.append("-----------------------------\n");
            }

            area.setText(info.toString());

        } catch (Exception e) {
            area.setText("Error fetching status: " + e.getMessage());
            e.printStackTrace();
        }

        setVisible(true);
    }
}
