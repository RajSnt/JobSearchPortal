package LoginPage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;

public class ApplicantRegister3 extends JFrame implements ActionListener{
    JTextField user, pass;
    JButton back, save;
    String adh;
    String col;
    String quali;
    ApplicantRegister3(String aadhar, String college, String qualification){
        this.adh = aadhar;
        this.col = college;
        this.quali = qualification;
        setSize(600,500);
        setLocation(500,200);
        setTitle("Applicant Registration");
        setLayout(null);

        JLabel t1 = new JLabel("Applicant Login Details");
        t1.setFont(new Font("Railway", Font.BOLD, 25));
        t1.setBounds(135,20,350,30);
        add(t1);

        JLabel username = new JLabel("Username:");
        username.setFont(new Font("Railway", Font.BOLD, 15));
        username.setBounds(50,70,100,30);
        add(username);
        JLabel instruc1 = new JLabel("**(Minimum 6 characters long)");
        instruc1.setFont(new Font("Railway", Font.BOLD, 10));
        instruc1.setBounds(50,100,180,15);
        add(instruc1);
        user = new JTextField();
        user.setFont(new Font("Railway", Font.PLAIN, 15));
        user.setBounds(170, 70, 300, 30);
        add(user);
        JLabel password = new JLabel("Password:");
        password.setFont(new Font("Railway", Font.BOLD, 15));
        password.setBounds(50,130,100,30);
        add(password);
        pass = new JTextField();
        pass.setFont(new Font("Railway", Font.PLAIN, 15));
        pass.setBounds(170, 130, 300, 30);
        add(pass);
        JLabel instruc2 = new JLabel("**(Remember or Note it down)");
        instruc2.setFont(new Font("Railway", Font.BOLD, 10));
        instruc2.setBounds(50,160,180,15);
        add(instruc2);

        back = new JButton("<--Back");
        back.setBounds(50,370,150,30);
        add(back);
        back.addActionListener(this);
        save = new JButton("Save and Proceed");
        save.setBounds(370,370,150,30);
        add(save);
        save.addActionListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        String aadhar = adh;
        String college = col;
        String qualification = quali;
        if(ae.getSource()==back){
            setVisible(false);
            new ApplicantRegister2("").setVisible(true);
        }
        else if(ae.getSource()==save){
            try {
                Random random = new Random();
                String ApNo;
                while (true) {
                    ApNo = String.format("%04d", random.nextInt(10000)); // "0000" to "9999"
                    ConnL c = new ConnL();
                    ResultSet checkRs = c.s.executeQuery("SELECT ApNo FROM LoginDetails WHERE ApNo='" + ApNo + "'");
                    if (!checkRs.next()) break;
                }
                ConnL c = new ConnL();

                String update1 = "UPDATE apinfo1 SET ApNo='" + ApNo + "' WHERE aadhar='" + aadhar + "'";
                c.s.executeUpdate(update1);

                String update2 = "UPDATE apinfo2 SET ApNo='" + ApNo + "' WHERE college='" + college + "' AND qualification='" + qualification + "'";
                c.s.executeUpdate(update2);
                String username = user.getText();
                String password = pass.getText();
                if (username.length() < 6) {
                    JOptionPane.showMessageDialog(null, "Username must be at least 6 characters.");
                } else {
                    String checkUser = "SELECT username FROM LoginDetails WHERE username = '" + username + "'";
                    ResultSet rs = c.s.executeQuery(checkUser);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Username already exists! Please enter a new username.");
                    } else {
                        String queryLogin = "INSERT INTO LoginDetails (ApNo, username, pass) VALUES ('" + ApNo + "', '" + username + "', '" + password + "')";
                        c.s.executeUpdate(queryLogin);

                        JOptionPane.showMessageDialog(null, "Registration complete! Your Username is: " + username);

                        setVisible(false);
                        new Login().setVisible(true);
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Operation failed: " + ex.getMessage());
            }


        }
    }

    public static void main(String[] args) {
        new ApplicantRegister3("","","");
    }
}
