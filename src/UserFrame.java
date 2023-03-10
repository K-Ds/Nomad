import javax.swing.*;
import java.awt.*;

public class UserFrame extends JFrame {
    JPanel travelsRequestPanel;
    JPanel workshopsRequestPanel;
    JPanel fundsRequestPanel;
    JPanel accountPanel;

    public UserFrame() {
        JTabbedPane userTabbedPane = new JTabbedPane();

        userTabbedPane.setTabPlacement(JTabbedPane.LEFT);
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

        userTabbedPane.addTab("Travels", travelsRequestPanel);
        userTabbedPane.addTab("Workshops", workshopsRequestPanel);
        userTabbedPane.addTab("Funds", fundsRequestPanel);
        userTabbedPane.addTab("Account", accountPanel);

        String[] columnNames = {"Travels", "Workshops", "Funds", "Account"};

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
