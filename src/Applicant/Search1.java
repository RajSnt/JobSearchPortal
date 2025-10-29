package Applicant;
import LoginPage.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Search1 extends JFrame implements ActionListener{
    JButton se, ds, pb, back;
    String un;
    Search1(String username){
        this.un =  username;
        setSize(600,400);
        setLocation(500,200);
        setTitle("Job Search");
        setLayout(null);
        JLabel t1 = new JLabel("Available Job fields");
        t1.setFont(new Font("Railway", Font.BOLD, 25));
        t1.setBounds(190,50,350,30);
        add(t1);
        se = new JButton("Software Engineering");
        se.setFont(new Font("Railway", Font.BOLD, 17));
        se.setBounds(150, 120, 300,30);
        add(se);
        se.addActionListener(this);
        ds = new JButton("Data Science Engineering");
        ds.setFont(new Font("Railway", Font.BOLD, 17));
        ds.setBounds(150, 160, 300,30);
        add(ds);
        ds.addActionListener(this);
        pb = new JButton("Production Based");
        pb.setFont(new Font("Railway", Font.BOLD, 17));
        pb.setBounds(150, 200, 300,30);
        add(pb);
        pb.addActionListener(this);
        back = new JButton("<--Back to Dashboard");
        back.setBounds(50,260,180,30);
        add(back);
        back.addActionListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        String username = un;
        if(ae.getSource()==back){
            setVisible(false);
            new ApplicantLanding("").setVisible(true);
        }
        else if(ae.getSource()==se){
            setVisible(false);
            new ApplySE(un).setVisible(true);
        }
        else if(ae.getSource()==ds){
            setVisible(false);
            new ApplyDS(un).setVisible(true);
        }
        else if(ae.getSource()==pb){
            setVisible(false);
            new ApplyPB(un).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Search1("");
    }
}
