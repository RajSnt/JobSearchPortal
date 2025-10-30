package Recruiter;
import LoginPage.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class EditStatus extends JFrame implements ActionListener{
    String un;
    JTextField AID, Role;
    JButton fetch, reject, PI, hire,back;
    EditStatus(String username){
        this.un = username;
        setSize(600,400);
        setLocation(500,200);
        setTitle("Edit Application status");
        setLayout(null);

        JLabel F1 = new JLabel("Applicant ID:");
        F1.setFont(new Font("Railway", Font.BOLD, 15));
        F1.setBounds(50,70,100,30);
        add(F1);
        AID = new JTextField();
        AID.setFont(new Font("Railway", Font.PLAIN, 15));
        AID.setBounds(210, 70, 300, 30);
        add(AID);
        JLabel F2 = new JLabel("Role/Position");
        F2.setFont(new Font("Railway", Font.BOLD, 15));
        F2.setBounds(50,110,100,30);
        add(F2);
        Role = new JTextField();
        Role.setFont(new Font("Railway", Font.PLAIN, 15));
        Role.setBounds(210, 110, 300, 30);
        add(Role);

        fetch = new JButton("Fetch Applicants");
        fetch.setBounds(210,160,180,30);
        add(fetch);
        fetch.addActionListener(this);
        back = new JButton("<--Recruiter Dashboard");
        back.setBounds(50,260,180,30);
        add(back);
        back.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == fetch){
            String apNo = AID.getText();
            String role = Role.getText();

            boolean found = false;
            try {
                ConnR c = new ConnR();
                String query = "SELECT * FROM applied WHERE ApNo='" + apNo + "' AND position='" + role + "'";
                ResultSet rs = c.s.executeQuery(query);

                if(rs.next()){
                    found = true;
                } else {
                    JOptionPane.showMessageDialog(this, "No applicant found for given ID and role.");
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }

            if(found){
                reject = new JButton("Reject");
                PI = new JButton("Personal Interview");
                hire = new JButton("Hire");

                reject.setBounds(50,210,150,30);
                PI.setBounds(220,210,150,30);
                hire.setBounds(390,210,150,30);

                add(reject);
                add(PI);
                add(hire);

                reject.addActionListener(this);
                PI.addActionListener(this);
                hire.addActionListener(this);
                repaint();
            }
        }
        if(ae.getSource() == reject){
            updateApplicantStatus("Rejected");
        }
        if(ae.getSource() == PI){
            updateApplicantStatus("PI");
        }
        if(ae.getSource() == hire){
            updateApplicantStatus("Hired");
        }
        if(ae.getSource() == back){
            setVisible(false);
            new RecruiterLanding("").setVisible(true);
            return;
        }
    }

    private void updateApplicantStatus(String newStatus){
        String apNo = AID.getText();
        String role = Role.getText();
        try {
            ConnR c = new ConnR();
            String updateQuery = "UPDATE applied SET stat='" + newStatus + "' WHERE ApNo='" + apNo + "' AND position='" + role + "'";
            int rows = c.s.executeUpdate(updateQuery);
            if(rows > 0){
                JOptionPane.showMessageDialog(this, "Applicant status updated to: " + newStatus);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update status.");
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        new EditStatus("");
    }
}
