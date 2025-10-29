package Applicant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class ApplyDS extends JFrame implements ActionListener{
    String un;
    JButton ApplyCE, ApplyDSE, ApplyDA, back;
    ApplyDS(String username){
        this.un =  username;
        setSize(700,850);
        setLocation(500,75);
        setTitle("Data Science Posts");
        setLayout(null);

        JLabel CE = new JLabel("1.Cloud Engineer");
        CE.setFont(new Font("Railway", Font.BOLD, 15));
        CE.setBounds(250, 20, 160, 30);
        add(CE);
        String CloudEngD = "-->CTC: ₹10–25 LPA\n" +
                "\n" +
                "-->Duties: Design, deploy, and manage cloud infrastructure. Automate deployments and ensure high availability. Monitor performance and manage cloud security. Support migration projects.\n" +
                "\n" +
                "-->Pre-requisites: Knowledge of AWS/Azure/GCP, virtualization, Docker/Kubernetes, scripting, and cloud networking.";
        JTextArea area4 = new JTextArea(CloudEngD);
        area4.setEditable(false);
        area4.setFont(new Font("Arial", Font.PLAIN, 15));
        area4.setLineWrap(true);
        area4.setWrapStyleWord(true);
        JScrollPane scroll4 = new JScrollPane(area4);
        scroll4.setBounds(70, 50, 550, 160 );
        add(scroll4);
        ApplyCE = new JButton("Apply for Cloud Engineer role");
        ApplyCE.setBounds(90,210,510,20);
        add(ApplyCE);
        ApplyCE.addActionListener(this);

        JLabel DSE = new JLabel("2.Data Science Engineer");
        DSE.setFont(new Font("Railway", Font.BOLD, 15));
        DSE.setBounds(250,230,200,30);
        add(DSE);
        String DataSciD = "-->CTC: ₹10–28 LPA\n" +
                "\n" +
                "-->Duties: Analyze data, build predictive models, and interpret results. Create data visualizations and communicate insights to stakeholders. Develop, test, and deploy machine learning solutions.\n" +
                "\n" +
                "-->Pre-requisites: Proficiency in Python/R, statistics, machine learning, data wrangling, communication skills, and tools like Pandas, Scikit-learn, or TensorFlow.";
        JTextArea area5 = new JTextArea(DataSciD);
        area5.setEditable(false);
        area5.setFont(new Font("Arial", Font.PLAIN, 15));
        area5.setLineWrap(true);
        area5.setWrapStyleWord(true);
        JScrollPane scroll5 = new JScrollPane(area5);
        scroll5.setBounds(70, 260, 550, 210);
        add(scroll5);
        ApplyDSE = new JButton("Apply for Data Science Engineer role");
        ApplyDSE.setBounds(90,470,510,20);
        add(ApplyDSE);
        ApplyDSE.addActionListener(this);

        JLabel DA = new JLabel("3.Data Analyst");
        DA.setFont(new Font("Railway", Font.BOLD, 15));
        DA.setBounds(240, 500, 210, 30);
        add(DA);
        String DataAnalystD = "-->CTC: ₹5–12 LPA\n" +
                "\n" +
                "-->Duties: Collect and analyze data. Prepare dashboards and data visualizations. Support decision-making by extracting actionable insights for business teams.\n" +
                "\n" +
                "-->Pre-requisites: Proficiency in Excel, SQL, Python, data visualization tools (PowerBI/Tableau), and strong analytical and presentation skills.";
        JTextArea area6 = new JTextArea(DataAnalystD);
        area6.setEditable(false);
        area6.setFont(new Font("Arial", Font.PLAIN, 15));
        area6.setLineWrap(true);
        area6.setWrapStyleWord(true);
        JScrollPane scroll6 = new JScrollPane(area6);
        scroll6.setBounds(70, 530, 550, 210);
        add(scroll6);
        ApplyDA = new JButton("Apply for Data Analyst role");
        ApplyDA.setBounds(90, 740, 510, 25);
        add(ApplyDA);
        ApplyDA.addActionListener(this);

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
        if (ae.getSource() == ApplyCE) {
            position = "Cloud Engineer";
        } else if (ae.getSource() == ApplyDSE) {
            position = "Data Science Engineer";
        } else if (ae.getSource() == ApplyDA) {
            position = "Data Analyst";
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
        new ApplyDS("");
    }

}
