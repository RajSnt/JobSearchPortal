package Applicant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class ApplyPB extends JFrame implements ActionListener {
    String un;
    JButton ApplyUID, ApplyPM, ApplyTS, back;
    ApplyPB(String username){
        this.un = username;
        setSize(700,850);
        setLocation(500,75);
        setTitle("Product Based posts");
        setLayout(null);

        JLabel UID = new JLabel("1.UI/UX Designer");
        UID.setFont(new Font("Railway", Font.BOLD, 15));
        UID.setBounds(250, 20, 160, 30);
        add(UID);
        String UIDescription = "-->CTC: ₹6–14 LPA\n" +
                "\n" +
                "-->Duties: Design user-friendly interfaces and experiences. Create wireframes, prototypes, and visual assets. Conduct user research and testing.\n" +
                "\n" +
                "-->Pre-requisites: Proficiency in design tools (Figma, Adobe XD), design principles, usability testing, prototyping, and basic HTML/CSS.";
        JTextArea area7 = new JTextArea(UIDescription);
        area7.setEditable(false);
        area7.setFont(new Font("Arial", Font.PLAIN, 15));
        area7.setLineWrap(true);
        area7.setWrapStyleWord(true);
        JScrollPane scroll7 = new JScrollPane(area7);
        scroll7.setBounds(70, 50, 550, 160);
        add(scroll7);
        ApplyUID = new JButton("Apply for UI/UX Designer role");
        ApplyUID.setBounds(90,210,510,20);
        add(ApplyUID);
        ApplyUID.addActionListener(this);

        JLabel PM = new JLabel("2.Product Manager");
        PM.setFont(new Font("Railway", Font.BOLD, 15));
        PM.setBounds(250,230,200,30);
        add(PM);
        String PMDescription = "-->CTC: ₹12–35 LPA\n" +
                "\n" +
                "-->Duties: Define product strategy and roadmap. Lead development teams. Coordinate features and releases. Liaise with marketing and sales.\n" +
                "\n" +
                "-->Pre-requisites: Strong leadership, communication, market analysis, agile methodologies, and technical understanding.";
        JTextArea area8 = new JTextArea(PMDescription);
        area8.setEditable(false);
        area8.setFont(new Font("Arial", Font.PLAIN, 15));
        area8.setLineWrap(true);
        area8.setWrapStyleWord(true);
        JScrollPane scroll8 = new JScrollPane(area8);
        scroll8.setBounds(70, 260, 550, 210);
        add(scroll8);
        ApplyPM = new JButton("Apply for Product Manager role");
        ApplyPM.setBounds(90,470,510,20);
        add(ApplyPM);
        ApplyPM.addActionListener(this);

        JLabel TS = new JLabel("3.Technical Support");
        TS.setFont(new Font("Railway", Font.BOLD, 15));
        TS.setBounds(240, 500, 210, 30);
        add(TS);
        String TSDescription = "-->CTC: ₹3–8 LPA\n" +
                "\n" +
                "-->Duties: Troubleshoot and resolve technical issues for customers. Document problems and solutions. Provide user support and training.\n" +
                "\n" +
                "-->Pre-requisites: Strong technical fundamentals, problem-solving skills, customer service, and communication abilities.";
        JTextArea area9 = new JTextArea(TSDescription);
        area9.setEditable(false);
        area9.setFont(new Font("Arial", Font.PLAIN, 15));
        area9.setLineWrap(true);
        area9.setWrapStyleWord(true);
        JScrollPane scroll9 = new JScrollPane(area9);
        scroll9.setBounds(70, 530, 550, 210);
        add(scroll9);
        ApplyTS = new JButton("Apply for Technical Support role");
        ApplyTS.setBounds(90, 740, 510, 25);
        add(ApplyTS);
        ApplyTS.addActionListener(this);

        back = new JButton("<--Back to Job fields");
        back.setBounds(50,770,180,30);
        add(back);
        back.addActionListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
        String position = "";
        String username = un;
        String stat = "Applied";
        if (ae.getSource() == ApplyUID) {
            position = "UI/UX Designer";
        } else if (ae.getSource() == ApplyPM) {
            position = "Product Manager";
        } else if (ae.getSource() == ApplyTS) {
            position = "Technical Support";
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
        new ApplyPB("");
    }
}
