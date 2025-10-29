package Applicant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ApplySE extends JFrame implements ActionListener{
    String un;
    JButton ApplySDE, ApplyFSD, ApplyBED, back;
    ApplySE(String username){
        this.un =  username;
        setSize(700,850);
        setLocation(500,75);
        setTitle("Software Engineer posts");
        setLayout(null);
        JLabel SDE = new JLabel("1.Software Developer");
        SDE.setFont(new Font("Railway", Font.BOLD, 15));
        SDE.setBounds(250,20,160,30);
        add(SDE);
        String SoftwareD = "-->CTC: ₹6–12 LPA (varies by company and location)\n" +
                "\n" +
                "-->Duties: Design, code, test, and maintain software applications. Debug and enhance existing software. Collaborate with cross-functional teams through the software development lifecycle.\u200B\n" +
                "\n" +
                "-->Pre-requisites: Strong knowledge of Java/C++/Python, algorithms, databases, object-oriented programming, and problem-solving skills.";
        JTextArea area1 = new JTextArea(SoftwareD);
        area1.setEditable(false);
        area1.setFont(new Font("Arial", Font.PLAIN, 15));
        area1.setLineWrap(true);
        area1.setWrapStyleWord(true);
        setLayout(null);
        JScrollPane scroll1 = new JScrollPane(area1);
        scroll1.setBounds(70, 50, 550, 160);
        add(scroll1);
        ApplySDE = new JButton("Apply for Software developer role");
        ApplySDE.setBounds(90,210,510,20);
        add(ApplySDE);
        ApplySDE.addActionListener(this);

        JLabel FSD = new JLabel("2.Full Stack Developer");
        FSD.setFont(new Font("Railway", Font.BOLD, 15));
        FSD.setBounds(250,230,180,30); // Adjust Y value as needed to place below previous box
        add(FSD);
        String FullStackD = "-->CTC: ₹8–18 LPA\n" +
                "\n" +
                "-->Duties: Design both frontend (web/mobile UI) and backend (APIs, databases). Deploy and maintain web applications. Develop and integrate APIs. Troubleshoot and optimize application performance. Work closely with designers and stakeholders to deliver responsive and scalable products.\n" +
                "\n" +
                "-->Pre-requisites: Proficient in JavaScript, HTML, CSS, and frameworks like React/Angular. One backend language (Java, Python, etc.), SQL/NoSQL databases. Experience with version control systems (Git), web servers (Apache/Nginx), UI/UX fundamentals, and project management skills.";
        JTextArea area2 = new JTextArea(FullStackD);
        area2.setEditable(false);
        area2.setFont(new Font("Arial", Font.PLAIN, 15));
        area2.setLineWrap(true);
        area2.setWrapStyleWord(true);
        JScrollPane scroll2 = new JScrollPane(area2);
        scroll2.setBounds(70, 260, 550, 210);
        add(scroll2);
        ApplyFSD = new JButton("Apply for Full Stack Developer role");
        ApplyFSD.setBounds(90,470,510,20);
        add(ApplyFSD);
        ApplyFSD.addActionListener(this);

        JLabel BED = new JLabel("3. Back End Developer");
        BED.setFont(new Font("Railway", Font.BOLD, 15));
        BED.setBounds(240, 500, 210, 30);
        add(BED);

        String BackendD = "-->CTC: ₹7–15 LPA\n" +
                "\n" +
                "-->Duties: Build and maintain APIs, server-side logic, and databases. Optimize performance, ensure data security, and integrate third-party services. Collaborate with frontend and other teams to deliver robust applications.\n" +
                "\n" +
                "-->Pre-requisites: Strong in backend languages (Java, Python, Node.js), SQL/NoSQL databases, RESTful APIs, authentication, server management, version control tools (Git).";
        JTextArea area3 = new JTextArea(BackendD);
        area3.setEditable(false);
        area3.setFont(new Font("Arial", Font.PLAIN, 15));
        area3.setLineWrap(true);
        area3.setWrapStyleWord(true);
        JScrollPane scroll3 = new JScrollPane(area3);
        scroll3.setBounds(70, 530, 550, 210);
        add(scroll3);
        ApplyBED = new JButton("Apply for Back End Developer role");
        ApplyBED.setBounds(90, 740, 510, 25);
        add(ApplyBED);
        ApplyBED.addActionListener(this);

        back = new JButton("<--Back to Job fields");
        back.setBounds(50,770,180,30);
        add(back);
        back.addActionListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        String position = "";
        String username = un;
        String stat = "Applied";
        if (ae.getSource() == ApplySDE) {
            position = "Software Developer";
        } else if (ae.getSource() == ApplyFSD) {
            position = "Full Stack Developer";
        } else if (ae.getSource() == ApplyBED) {
            position = "Back End Developer";
        }
        try {
            ConnA c = new ConnA();
            String getApNoQuery = "SELECT ApNo FROM LoginDetails WHERE username = '" + username + "'";
            ResultSet rsApNo = c.s.executeQuery(getApNoQuery);
            String apNo = null;

            if (rsApNo.next()) {
                apNo = rsApNo.getString("ApNo");
            }

            if (apNo != null) {
                String fetchQuery = "SELECT qualification, ObtainedDegree FROM apinfo2 WHERE ApNo = '" + apNo + "'";
                ResultSet rs = c.s.executeQuery(fetchQuery);

                if (rs.next()) {
                    String qualification = rs.getString("qualification");
                    String degree = rs.getString("ObtainedDegree");
                    String insertQuery = "INSERT INTO applied (ApNo, username, position, qualification, degree, stat) " +
                            "VALUES ('" + apNo + "','" + username + "','" + position + "','" + qualification + "','" + degree + "','" + stat + "')";
                    c.s.executeUpdate(insertQuery);

                    JOptionPane.showMessageDialog(null, "Application submitted!");
                } else {
                    JOptionPane.showMessageDialog(null, "No data found for this applicant number in apinfo2.");
                }
            }

            if(ae.getSource()==back){
                setVisible(false);
                new Search1("").setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

    }
        public static void main(String[] args) {
        new ApplySE("");
    }

}
