import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {
    JPanel travelsRequestPanel;
    JPanel workshopsRequestPanel;
    JPanel fundsRequestPanel;
    JPanel accountPanel;

    public AdminFrame() {
        JTabbedPane adminTabbedPane = new JTabbedPane();

        adminTabbedPane.setTabPlacement(JTabbedPane.LEFT);
        travelsRequestPanel = new RequestPanel("Travels Requests", "travels", this);
        workshopsRequestPanel = new RequestPanel("Workshops Requests", "workshops", this);
        fundsRequestPanel = new RequestPanel("Funds Requests", "fundings", this);
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            dispose();
            new AuthFrame();
        });
        logoutButton.setBounds(30, 30, 100 ,100);
        accountPanel = new JPanel();
        accountPanel.setLayout(null);
        accountPanel.add(logoutButton);

        adminTabbedPane.addTab("Travels", travelsRequestPanel);
        adminTabbedPane.addTab("Workshops", workshopsRequestPanel);
        adminTabbedPane.addTab("Funds", fundsRequestPanel);
        adminTabbedPane.addTab("Account",accountPanel);

        String[] columnNames = {"Travels", "Workshops", "Funds", "Account"};

        for(int i = 0; i < columnNames.length; i++){
            JLabel label = new JLabel(columnNames[i]);
            label.setSize(new Dimension(100,125));
            label.setFont(new Font("Poppins", Font.PLAIN, 20));
            adminTabbedPane.setTabComponentAt(i, label);
        }

        add(adminTabbedPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
