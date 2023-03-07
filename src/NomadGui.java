import javax.swing.*;
import java.awt.*;

public class NomadGui extends JFrame {

    public NomadGui(){
        this.setTitle("Nomad");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane authtab = new JTabbedPane();
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BorderLayout());

        JLabel loginTitle = new JLabel("Welcome Back!");
        loginPanel.add(loginTitle, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(2, 2));
        fieldsPanel.setSize(150, 150);

        JLabel emailLabel = new JLabel("ID");
        JTextField emailField = new JTextField("192168");
        emailField.setSize(new Dimension(300, 10));

        JLabel passwordLabel = new JLabel("Password");
        JTextField passwordField = new JTextField("Password");
        passwordField.setSize(new Dimension(300, 1));

        fieldsPanel.add(emailLabel);
        fieldsPanel.add(emailField);
        fieldsPanel.add(passwordLabel);
        fieldsPanel.add(passwordField);

        loginPanel.add(fieldsPanel, BorderLayout.CENTER);

        authtab.add("Login",loginPanel);
        this.add(authtab);
    }

    public static void main(String[] args){
        NomadGui gui = new NomadGui();
        gui.setVisible(true);
    }
}
