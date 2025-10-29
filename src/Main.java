import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Job Search");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Job description for Software Developer
        String jobDetails =
                "Software Developer\n\n" +
                        "CTC: ₹6–12 LPA\n\n" +
                        "Duties:\n" +
                        "• Design, code, test, and maintain software applications.\n" +
                        "• Debug and enhance existing software.\n" +
                        "• Collaborate with cross-functional teams through the software development lifecycle.\n\n" +
                        "Pre-requisites:\n" +
                        "• Strong knowledge of Java/C++/Python\n" +
                        "• Algorithms, databases, object-oriented programming\n" +
                        "• Good problem-solving skills";

        JTextArea detailsArea = new JTextArea(jobDetails);
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(scrollPane);

        setLocationRelativeTo(null); // center the window
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
