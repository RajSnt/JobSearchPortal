package Recruiter;
import LoginPage.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class RecruiterLanding extends JFrame implements ActionListener{
    String un;
    JButton check,update,back;
    public RecruiterLanding(String username){
        this.un = username;
        setSize(600,400);
        setLocation(500,200);
        setTitle("Recruiter Dashboard");
        setLayout(null);
        JLabel t1 = new JLabel("Welcome "+un);
        t1.setFont(new Font("Railway", Font.BOLD, 25));
        t1.setBounds(135,20,350,30);
        add(t1);
        JLabel t2 = new JLabel("Recruiter Menu");
        t2.setFont(new Font("Railway", Font.BOLD, 20));
        t2.setBounds(230,75,175,30);
        add(t2);
        check = new JButton("Check Applications");
        check.setFont(new Font("Railway", Font.BOLD, 17));
        check.setBounds(150, 120, 300,30);
        add(check);
        check.addActionListener(this);
        update = new JButton("Update applicant status");
        update.setFont(new Font("Railway", Font.BOLD, 17));
        update.setBounds(150, 160, 300,30);
        add(update);
        update.addActionListener(this);

        back = new JButton("<--Back to Login Page");
        back.setBounds(50,260,180,30);
        add(back);
        back.addActionListener(this);


        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae){
        String u = un;
        if(ae.getSource()==check){
            setVisible(false);
            new ViewApplicants(u).setVisible(true);
        }
        else if(ae.getSource()==update){
            setVisible(false);
            new EditStatus(u).setVisible(true);
        }
        else if(ae.getSource()==back){
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new RecruiterLanding("");
    }
}
