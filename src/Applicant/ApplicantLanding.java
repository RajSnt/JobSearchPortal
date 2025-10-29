package Applicant;

import LoginPage.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class ApplicantLanding extends JFrame implements ActionListener {
    String un;
    JButton search, status, delete, back;
    public ApplicantLanding(String UserName){
        this.un = UserName;
        setSize(600,400);
        setLocation(500,200);
        setTitle("Applicant Dashboard");
        setLayout(null);
        JLabel t1 = new JLabel("Welcome "+un);
        t1.setFont(new Font("Railway", Font.BOLD, 25));
        t1.setBounds(135,20,350,30);
        add(t1);
        JLabel t2 = new JLabel("Applicant Menu");
        t2.setFont(new Font("Railway", Font.BOLD, 20));
        t2.setBounds(230,75,175,30);
        add(t2);
        search = new JButton("Search Jobs");
        search.setFont(new Font("Railway", Font.BOLD, 17));
        search.setBounds(150, 120, 300,30);
        add(search);
        search.addActionListener(this);
        status = new JButton("Check application Status");
        status.setFont(new Font("Railway", Font.BOLD, 17));
        status.setBounds(150, 160, 300,30);
        add(status);
        status.addActionListener(this);
        delete = new JButton("Delete account");
        delete.setFont(new Font("Railway", Font.BOLD, 17));
        delete.setBounds(150, 200, 300,30);
        add(delete);
        delete.addActionListener(this);
        back = new JButton("<--Back to Login Page");
        back.setBounds(50,260,180,30);
        add(back);
        back.addActionListener(this);


        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==back){
            setVisible(false);
            new Login().setVisible(true);
        }
        else if(ae.getSource()==search){
            String username = un;
            setVisible(false);
            new Search1(username).setVisible(true);
        }
        else if(ae.getSource()==status){
            String username = un;
            new ApplicantStatus(username).setVisible(true);
        }
        else if(ae.getSource() == delete) {
            String user = un;
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete your account? All your data will be removed.",
                    "Confirm Account Deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if(confirm == JOptionPane.YES_OPTION) {
                try {
                    ConnA c = new ConnA();
                    String apNo = "";
                    String getApNo = "SELECT ApNo FROM LoginDetails WHERE username='" + user + "'";
                    ResultSet rs = c.s.executeQuery(getApNo);
                    if(rs.next()) {
                        apNo = rs.getString("ApNo");
                    }

                    if(!apNo.isEmpty()) {
                        c.s.executeUpdate("DELETE FROM applied WHERE username='" + user + "'");
                        c.s.executeUpdate("DELETE FROM apinfo1 WHERE ApNo='" + apNo + "'");
                        c.s.executeUpdate("DELETE FROM apinfo2 WHERE ApNo='" + apNo + "'");
                        c.s.executeUpdate("DELETE FROM LoginDetails WHERE username='" + user + "'");
                    }

                    JOptionPane.showMessageDialog(this, "Account and all related data deleted successfully!");
                    setVisible(false);
                    new Login().setVisible(true);

                } catch(Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error during deletion: " + e.getMessage());
                }
            }
        }


    }

    public static void main(String[] args) {
        new ApplicantLanding("");
    }
}
