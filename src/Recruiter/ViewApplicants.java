package Recruiter;
import LoginPage.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class ViewApplicants extends JFrame implements ActionListener{
    String un;
    JComboBox combo;
    JButton fetch, back;
    ViewApplicants(String username){
        this.un = username;
        setSize(600,400);
        setLocation(500,200);
        setTitle("View Applicants");
        setLayout(null);

        JLabel t1 = new JLabel("Select the role for which you want to search:");
        t1.setFont(new Font("Railway", Font.BOLD, 20));
        t1.setBounds(80,20,450,30);
        add(t1);
        String[] jobRoles = {
                "Software Developer",
                "Full Stack Developer",
                "Back End Developer",
                "Cloud Engineer",
                "Data Science Engineer",
                "Data Analyst",
                "UI/UX Designer",
                "Product Manager",
                "Technical Support"
        };

        combo = new JComboBox(jobRoles);
        combo.setBounds(170, 110, 250, 30);
        add(combo);

        fetch = new JButton("Fetch Applicants");
        fetch.setBounds(350,260,180,30);
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
        if(ae.getSource() == back){
            setVisible(false);
            new RecruiterLanding("").setVisible(true);
            return;
        }
        if(ae.getSource() == fetch){
            String selectedRole = (String)combo.getSelectedItem();

            JFrame tableFrame = new JFrame("Applicants for " + selectedRole);
            tableFrame.setSize(800, 500);
            tableFrame.setLocationRelativeTo(null);

            String[] columns = {"ApNo", "Username", "Position", "Qualification", "Degree", "Status"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            try {
                ConnR c = new ConnR();
                String query = "SELECT ApNo, username, position, qualification, degree, stat FROM applied WHERE position='" + selectedRole + "'";
                ResultSet rs = c.s.executeQuery(query);

                while (rs.next()) {
                    Object[] row = {
                            rs.getString("ApNo"),
                            rs.getString("username"),
                            rs.getString("position"),
                            rs.getString("qualification"),
                            rs.getString("degree"),
                            rs.getString("stat")
                    };
                    model.addRow(row);
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);

            tableFrame.add(scrollPane, BorderLayout.CENTER);
            tableFrame.setVisible(true);
            tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    public static void main(String[] args) {
        new ViewApplicants("");
    }
}
