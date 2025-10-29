package LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicantRegister2 extends JFrame implements ActionListener {
        JRadioButton ug, pg, sd, fsd, bed, ce, ds, da, uiux, pm, ts;
        JTextField college1, deg;
        JComboBox dee;
        String adh;
        JButton  back,next;
        ApplicantRegister2(String aadhar){
                this.adh = aadhar;
                setSize(600,500);
                setLocation(500,200);
                setTitle("Applicant Registration");
                setLayout(null);

                JLabel t1 = new JLabel("Applicant Degree Details");
                t1.setFont(new Font("Railway", Font.BOLD, 25));
                t1.setBounds(135,20,350,30);
                add(t1);

                JLabel quali = new JLabel("Qualification:");
                quali.setFont(new Font("Railway", Font.BOLD, 15));
                quali.setBounds(50,70,100,30);
                add(quali);
                ug = new JRadioButton("UnderGraduate");
                ug.setBounds(210, 70, 150, 30);
                ug.setFont(new Font("Raleway",Font.PLAIN, 15));
                add(ug);
                pg = new JRadioButton("PostGraduate");
                pg.setBounds(370, 70, 150, 30);
                pg.setFont(new Font("Raleway",Font.PLAIN, 15));
                add(pg);
                ButtonGroup qualification = new ButtonGroup();
                qualification.add(ug);
                qualification.add(pg);

                JLabel col = new JLabel("College/University:");
                col.setFont(new Font("Railway", Font.BOLD, 15));
                col.setBounds(50,110,150,30);
                add(col);
                college1 = new JTextField();
                college1.setFont(new Font("Railway", Font.PLAIN, 15));
                college1.setBounds(210, 110, 300, 30);
                add(college1);

                JLabel degree = new JLabel("Obtained degree:");
                degree.setFont(new Font("Railway", Font.BOLD, 15));
                degree.setBounds(50,150,150,30);
                add(degree);
                String[] degrees = {"Bachelors Degree","Masters Degree","Diploma","PHD","Others"};
                dee = new JComboBox<>(degrees);
                dee.setBounds(210,150,300,30);
                add(dee);
                JLabel degree2 = new JLabel("Obtained in:");
                degree2.setBounds(50,190,150,30);
                degree2.setFont(new Font("Railway", Font.BOLD, 15));
                add(degree2);
                deg = new JTextField();
                deg.setFont(new Font("Railway", Font.PLAIN, 15));
                deg.setBounds(210, 190, 300, 30);
                add(deg);
                JLabel interests = new JLabel("Interested Jobs:");
                interests.setFont(new Font("Railway", Font.BOLD, 15));
                interests.setBounds(50,230,150,30);
                add(interests);
                sd = new JRadioButton("Software Developer");
                sd.setBounds(50,270,150,20);
                add(sd);
                fsd = new JRadioButton("Full-Stack Developer");
                fsd.setBounds(210,270,150,20);
                add(fsd);
                bed = new JRadioButton("Backend Developer");
                bed.setBounds(370,270,150,20);
                add(bed);
                ce = new JRadioButton("Cloud Engineer");
                ce.setBounds(50,300,150,20);
                add(ce);
                ds = new JRadioButton("Data Scientist");
                ds.setBounds(210,300,150,20);
                add(ds);
                da = new JRadioButton("Data Analyst");
                da.setBounds(370,300,150,20);
                add(da);
                uiux = new JRadioButton("UI/UX Designer");
                uiux.setBounds(50,330,150,20);
                add(uiux);
                pm = new JRadioButton("Product manager");
                pm.setBounds(210,330,150,20);
                add(pm);
                ts = new JRadioButton("Technical support");
                ts.setBounds(370,330,150,20);
                add(ts);

                back = new JButton("<--Back");
                back.setBounds(50,370,150,30);
                add(back);
                back.addActionListener(this);
                next = new JButton("Next-->");
                next.setBounds(370,370,150,30);
                add(next);
                next.addActionListener(this);

                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setVisible(true);

        }
        public void actionPerformed(ActionEvent ae){
                String aadhar1 = adh;
                if(ae.getSource()==back){
                        setVisible(false);
                        new ApplicantRegister().setVisible(true);
                }
                if(ae.getSource()==next){
                        try {
                                String qualification = (ug.isSelected()) ? "UnderGraduate" :
                                        (pg.isSelected()) ? "PostGraduate" : "";

                                String college = college1.getText();
                                String obtainedDegree = (String) dee.getSelectedItem();
                                String obtainedIn = deg.getText();


                                StringBuilder interested = new StringBuilder();
                                if (sd.isSelected()) interested.append("Software Developer, ");
                                if (fsd.isSelected()) interested.append("Full-Stack Developer, ");
                                if (bed.isSelected()) interested.append("Backend Developer, ");
                                if (ce.isSelected()) interested.append("Cloud Engineer, ");
                                if (ds.isSelected()) interested.append("Data Scientist, ");
                                if (da.isSelected()) interested.append("Data Analyst, ");
                                if (uiux.isSelected()) interested.append("UI/UX Designer, ");
                                if (pm.isSelected()) interested.append("Product manager, ");
                                if (ts.isSelected()) interested.append("Technical support, ");
                                if (interested.length() > 2) interested.setLength(interested.length() - 2);

                                if (qualification.equals("")) {
                                        JOptionPane.showMessageDialog(null, "Please select your Qualification.");
                                } else if (college.equals("")) {
                                        JOptionPane.showMessageDialog(null, "Please enter your College/University.");
                                } else if (obtainedDegree.equals("")) {
                                        JOptionPane.showMessageDialog(null, "Please select your Degree.");
                                } else if (obtainedIn.equals("")) {
                                        JOptionPane.showMessageDialog(null, "Please enter the field you obtained the degree in.");
                                } else if (interested.toString().equals("")) {
                                        JOptionPane.showMessageDialog(null, "Please select at least one Interested Job.");
                                } else {
                                        ConnL c = new ConnL();
                                        String query = "insert into apinfo2 (qualification, college, ObtainedDegree, ObtainedIn, interested) values ('"
                                                + qualification + "','" + college + "','" + obtainedDegree + "','" + obtainedIn + "','" + interested.toString() + "')";
                                        c.s.executeUpdate(query);

                                        setVisible(false);
                                        new ApplicantRegister3(aadhar1,college,qualification).setVisible(true);
                                }
                        } catch (Exception ex) {
                                System.out.println(ex);
                        }

                }
        }
        public static void main(String[] args) {
                new ApplicantRegister2("");
        }

}

