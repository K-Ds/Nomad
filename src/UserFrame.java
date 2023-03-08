import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class UserFrame extends JFrame {
    JPanel travelsRequestPanel;
    JPanel workshopsRequestPanel;
    JPanel fundsRequestPanel;

    public UserFrame() {
        JTabbedPane userTabbedPane = new JTabbedPane();

        userTabbedPane.setTabPlacement(JTabbedPane.LEFT);
        travelsRequestPanel = new RequestPanel("Travels Requests", "travels");
        workshopsRequestPanel = new RequestPanel("Workshops Requests", "workshops");
        fundsRequestPanel = new RequestPanel("Funds Requests", "fundings");

        userTabbedPane.addTab("Travels", travelsRequestPanel);
        userTabbedPane.addTab("Workshops", workshopsRequestPanel);
        userTabbedPane.addTab("Funds", fundsRequestPanel);

        String[] columnNames = {"Travels", "Workshops", "Funds"};

        for(int i = 0; i < columnNames.length; i++){
            JLabel label = new JLabel(columnNames[i]);
            label.setSize(new Dimension(100,125));
            label.setFont(new Font("Poppins", Font.PLAIN, 20));
            userTabbedPane.setTabComponentAt(i, label);
        }

        add(userTabbedPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
