package LoginPage;

import Applicant.*;
import Recruiter.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JButton Log1, Log2, Clear, NewLogin;
    JPasswordField Pass;
    JTextField User;
    public Login(){
        setSize(550,500);
        setLocation(500,220);
        setTitle("Job Search Portal");
        setLayout(null);

        JLabel t1 = new JLabel("User Login");
        t1.setFont(new Font("Railway", Font.BOLD, 25));
        t1.setBounds(200, 30, 200, 30);
        add(t1);

        JLabel UserName = new JLabel("Username: ");
        UserName.setFont(new Font("Railway", Font.BOLD, 18));
        UserName.setBounds(100, 100, 100, 30);
        add(UserName);
        User = new JTextField();
        User.setFont(new Font("Railway", Font.BOLD, 18));
        User.setBounds(220, 100, 200, 30);
        add(User);

        JLabel Password = new JLabel("Password: ");
        Password.setFont(new Font("Railway", Font.BOLD, 18));
        Password.setBounds(100, 160, 100, 30);
        add(Password);
        Pass = new JPasswordField();
        Pass.setFont(new Font("Railway", Font.BOLD, 18));
        Pass.setBounds(220, 160, 200, 30);
        add(Pass);

        Log1 = new JButton("Login as Applicant");
        Log1.setBounds(100, 210,320,30);
        add(Log1);
        Log1.addActionListener(this);
        Log2 = new JButton("Login as Recruiter");
        Log2.setBounds(100, 250,320,30);
        add(Log2);
        Log2.addActionListener(this);
        Clear = new JButton("Clear");
        Clear.setBounds(100, 290,150,30);
        add(Clear);
        Clear.addActionListener(this);
        NewLogin = new JButton("New User");
        NewLogin.setBounds(270, 290,150,30);
        add(NewLogin);
        NewLogin.addActionListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==Clear){
            User.setText("");
            Pass.setText("");
        }
        else if(ae.getSource()==NewLogin){
            setVisible(false);
            new ApplicantRegister().setVisible(true);
        }
        else if(ae.getSource()==Log1){
            String un = User.getText();
            String pass = String.valueOf(Pass.getPassword());

            try {
                ConnL c = new ConnL();
                String query = "SELECT * FROM LoginDetails WHERE username='" + un + "' AND pass='" + pass + "'";
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    setVisible(false);
                    new ApplicantLanding(un);
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong username or password");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }

        }
        else if(ae.getSource()==Log2){
            String username = User.getText();
            String password = String.valueOf(Pass.getPassword());

            try {
                ConnL c = new ConnL();
                String query = "SELECT * FROM Recruiters WHERE usernameR='" + username + "' AND passR='" + password + "'";
                ResultSet rs = c.s.executeQuery(query);

                if(rs.next()) {
                    setVisible(false);
                    new RecruiterLanding(username).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Recruiter does not exist or password is incorrect");
                }
            } catch(Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }

        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
