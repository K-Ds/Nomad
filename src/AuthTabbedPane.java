import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthTabbedPane extends JFrame {
    private final JTabbedPane tabbedPane;
    private final RegisterPanel registerPanel;
    private final LoginPanel loginPanel;

    public AuthTabbedPane() {
        super("Authentication Example");

        // Create the register and login panels
        registerPanel = new RegisterPanel();
        loginPanel = new LoginPanel();

        // Create the tabbed pane and add the panels
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Register", registerPanel);
        tabbedPane.addTab("Login", loginPanel);

        // Add the tabbed pane to the frame
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Set up the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,
                400);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    public static void main(String[] args) {
        new AuthTabbedPane();
    }

    // Inner class for the register panel
    private class RegisterPanel extends JPanel {
        private final JTextField idField;
        private final JPasswordField passwordField;

        private final JTextField nameField;

        private final JTextField departmentField;
        private final JButton registerButton;

        public RegisterPanel() {
            // Set the layout
            setLayout(new GridLayout(5, 2));
            setPreferredSize(new Dimension(10,50));

            // Create the components
            JLabel idLabel = new JLabel("Id:");
            idField = new JTextField();

            JLabel nameLabel = new JLabel("Name:");
            nameField = new JTextField();

            JLabel departmentLabel = new JLabel("Department:");
            departmentField = new JTextField();

            JLabel passwordLabel = new JLabel("Password:");
            passwordField = new JPasswordField();

            registerButton = new JButton("Register");

            // Add the components to the panel
            add(idLabel);
            add(idField);
            add(nameLabel);
            add(nameField);
            add(departmentLabel);
            add(departmentField);
            add(passwordLabel);
            add(passwordField);
            add(new JLabel()); // Placeholder for layout purposes
            add(registerButton);

            // Set up the button action listener
            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Call imaginary method to save the data
                    saveRegistrationData(idField.getText(), new String(passwordField.getPassword()));
                    // Show confirmation message
                    JOptionPane.showMessageDialog(RegisterPanel.this, "Registration successful!");
                }
            });
        }
    }

    // Inner class for the login panel
    private class LoginPanel extends JPanel {
        private final JTextField emailField;
        private final JPasswordField passwordField;
        private final JButton loginButton;

        public LoginPanel() {
            // Set the layout
            setLayout(new GridLayout(3, 2));

            // Create the components
            JLabel emailLabel = new JLabel("Email:");
            emailField = new JTextField();
            JLabel passwordLabel = new JLabel("Password:");
            passwordField = new JPasswordField();
            loginButton = new JButton("Login");

            // Add the components to the panel
            add(emailLabel);
            add(emailField);
            add(passwordLabel);
            add(passwordField);
            add(new JLabel()); // Placeholder for layout purposes
            add(loginButton);

            // Set up the button action listener
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Call imaginary method to check the login credentials
                    boolean successful = checkLoginCredentials(emailField.getText(), new String(passwordField.getPassword()));
                    if (successful) {
                        // Show confirmation message
                        JOptionPane.showMessageDialog(LoginPanel.this, "Login successful!");
                    } else {
                        // Show error message
                        JOptionPane.showMessageDialog(LoginPanel.this, "Login failed. Please check your email and password.");
                    }
                }
            });
        }
    }

    // Imaginary method to save registration data
    private void saveRegistrationData(String email, String password) {
        // This is where you would actually save the registration data to a database or file
        // For the purposes of this example, we will just print the data to the console
        System.out.println("Registration data saved:");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    }

    // Imaginary method to check login credentials
    private boolean checkLoginCredentials(String email, String password) {
        // This is where you would actually check the login credentials against a database or file
        // For the purposes of this example, we will just use some hardcoded values
        if (email.equals("test@example.com") && password.equals("password123")) {
            return true;
        } else {
            return false;
        }
    }
}
