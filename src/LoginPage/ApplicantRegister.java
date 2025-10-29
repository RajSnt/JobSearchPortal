package LoginPage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class ApplicantRegister extends JFrame implements ActionListener{

    JTextField name1, name2, Pname1, Pname2, phone, email, aadhar;
    JButton back, clear, next;
    ApplicantRegister(){
        setSize(600,500);
        setLocation(500,200);
        setTitle("Applicant Registration");
        setLayout(null);
        JLabel t1 = new JLabel("Applicant Personal Details");
        t1.setFont(new Font("Railway", Font.BOLD, 25));
        t1.setBounds(135,20,350,30);
        add(t1);

        JLabel F1 = new JLabel("First Name:");
        F1.setFont(new Font("Railway", Font.BOLD, 15));
        F1.setBounds(50,70,100,30);
        add(F1);
        name1 = new JTextField();
        name1.setFont(new Font("Railway", Font.PLAIN, 15));
        name1.setBounds(210, 70, 300, 30);
        add(name1);
        JLabel F2 = new JLabel("Last Name:");
        F2.setFont(new Font("Railway", Font.BOLD, 15));
        F2.setBounds(50,110,100,30);
        add(F2);
        name2 = new JTextField();
        name2.setFont(new Font("Railway", Font.PLAIN, 15));
        name2.setBounds(210, 110, 300, 30);
        add(name2);
        JLabel Fname = new JLabel("Father's name:");
        Fname.setFont(new Font("Railway", Font.BOLD, 15));
        Fname.setBounds(50,150,150,30);
        add(Fname);
        Pname1 = new JTextField();
        Pname1.setFont(new Font("Railway", Font.PLAIN, 15));
        Pname1.setBounds(210, 150, 300, 30);
        add(Pname1);
        JLabel Mname = new JLabel("Mother's name:");
        Mname.setFont(new Font("Railway", Font.BOLD, 15));
        Mname.setBounds(50,190,150,30);
        add(Mname);
        Pname2 = new JTextField();
        Pname2.setFont(new Font("Railway", Font.PLAIN, 15));
        Pname2.setBounds(210, 190, 300, 30);
        add(Pname2);
        JLabel ph = new JLabel("Contact Number:");
        ph.setFont(new Font("Railway", Font.BOLD, 15));
        ph.setBounds(50,230,150,30);
        add(ph);
        phone = new JTextField();
        phone.setFont(new Font("Railway", Font.PLAIN, 15));
        phone.setBounds(210, 230, 300, 30);
        add(phone);
        JLabel em = new JLabel("E-Mail:");
        em.setFont(new Font("Railway", Font.BOLD, 15));
        em.setBounds(50,270,100,30);
        add(em);
        email = new JTextField();
        email.setFont(new Font("Railway", Font.PLAIN, 15));
        email.setBounds(210, 270, 300, 30);
        add(email);
        JLabel ad = new JLabel("Aadhar number:");
        ad.setFont(new Font("Railway", Font.BOLD, 15));
        ad.setBounds(50,310,150,30);
        add(ad);
        aadhar = new JTextField();
        aadhar.setFont(new Font("Railway", Font.PLAIN, 15));
        aadhar.setBounds(210, 310, 300, 30);
        add(aadhar);

        back = new JButton("<--Back");
        back.setBounds(50,350,150,30);
        add(back);
        back.addActionListener(this);
        clear = new JButton("Clear");
        clear.setBounds(210,350,150,30);
        add(clear);
        clear.addActionListener(this);
        next = new JButton("Next-->");
        next.setBounds(370,350,150,30);
        add(next);
        next.addActionListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==back){
            setVisible(false);
            new Login().setVisible(true);
        }
        else if(ae.getSource()==clear){
            name1.setText("");
            name2.setText("");
            Pname1.setText("");
            Pname2.setText("");
            phone.setText("");
            email.setText("");
            aadhar.setText("");
        }
        else if(ae.getSource()==next){
            String Firstname = name1.getText();
            String Lastname = name2.getText();
            String fatherName = Pname1.getText();
            String motherName = Pname2.getText();
            String contact = phone.getText();
            String email1 = email.getText();
            String aadhar1 = aadhar.getText();

            try {
                if (Firstname.equals("")) {
                    JOptionPane.showMessageDialog(null, "Entering first name is mandatory");
                } else if (Lastname.equals("")) {
                    JOptionPane.showMessageDialog(null, "Entering last name is mandatory");
                } else if (fatherName.equals("")) {
                    JOptionPane.showMessageDialog(null, "Entering father's name is mandatory");
                } else if (motherName.equals("")) {
                    JOptionPane.showMessageDialog(null, "Entering mother's name is mandatory");
                } else if (contact.equals("")) {
                    JOptionPane.showMessageDialog(null, "Entering contact is mandatory");
                } else if (email1.equals("")) {
                    JOptionPane.showMessageDialog(null, "Entering E-mail address is mandatory");
                } else if (aadhar1.equals("")) {
                    JOptionPane.showMessageDialog(null, "Entering Aadhar is mandatory");
                } else if (contact.length() != 10 || !contact.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "Contact number must be exactly 10 digits.");
                } else if (aadhar1.length() != 12 || !aadhar1.matches("\\d{12}")) {
                    JOptionPane.showMessageDialog(null, "Aadhar number must be exactly 12 digits.");
                } else {
                    ConnL c = new ConnL();
                    String checkQuery = "SELECT aadhar FROM apinfo1 WHERE aadhar = '" + aadhar1 + "'";
                    ResultSet rs = c.s.executeQuery(checkQuery);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Aadhar number already exists. Please enter a correct (unique) Aadhar number.");
                    } else {
                        String query = "insert into apinfo1 (Firstname, Lastname, father_name, mother_name, contact, email, aadhar) " +
                                "values ('" + Firstname + "','" + Lastname + "','" + fatherName + "','" + motherName + "','" +
                                contact + "','" + email1 + "','" + aadhar1 + "')";
                        c.s.executeUpdate(query);

                        setVisible(false);
                        new ApplicantRegister2(aadhar1).setVisible(true);
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }


        }
    }

    public static void main(String[] args) {
        new ApplicantRegister();
    }
}
