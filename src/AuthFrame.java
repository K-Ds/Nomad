import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthFrame extends JFrame {

	private JTabbedPane authTabbedPane;
	private JPanel loginPanel;
	private JPanel registerPanel;

	public AuthFrame() {
		loginPanel = new LoginPanel();
		registerPanel = new RegisterPanel();

		authTabbedPane = new JTabbedPane();

		authTabbedPane.addTab("Login", loginPanel);
		authTabbedPane.addTab("Register", registerPanel);

		add(authTabbedPane);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}

	//Inner class for the register panel
	private class RegisterPanel extends JPanel {
        private final JTextField idField;
        private final JPasswordField passwordField;
        private final JTextField nameField;
        private final JButton registerButton;
		private final JComboBox departmentOption;

        public RegisterPanel() {
            setLayout(null);
            setPreferredSize(new Dimension(10,50));

            // Create the components
            JLabel idLabel = new JLabel("Id:");
            idField = new JTextField();
			idLabel.setBounds(10,20, 100, 40);
			idField.setBounds(100, 20, 200,40);

            JLabel nameLabel = new JLabel("Name:");
            nameField = new JTextField();
			nameLabel.setBounds(10,80, 100, 40);
			nameField.setBounds(100, 80, 250,40);

            JLabel departmentLabel = new JLabel("Department:");
			String[] departmentList = {"IT", "Finance", "HR"};
            departmentOption = new JComboBox(departmentList);
			departmentLabel.setBounds(10,140, 100, 25);
			departmentOption.setBounds(100, 140, 250,25);

            JLabel passwordLabel = new JLabel("Password:");
            passwordField = new JPasswordField();
			passwordLabel.setBounds(10,200, 100, 40);
			passwordField.setBounds(100, 200, 250,40);

            registerButton = new JButton("Register");
			registerButton.setBounds(150,260, 100, 40);

            // Add the components to the panel
            add(idLabel);
            add(idField);
            add(nameLabel);
            add(nameField);
            add(departmentLabel);
            add(departmentOption);
            add(passwordLabel);
            add(passwordField);
            add(registerButton);

            // Set up the button action listener
            registerButton.addActionListener(e ->{
                    // // Call imaginary method to save the data
                    // saveRegistrationData(idField.getText(), new String(passwordField.getPassword()));
                    // // Show confirmation message
                    // JOptionPane.showMessageDialog(RegisterPanel.this, "Registration successful!");
                }
            );
        }
    }

    // Inner class for the login panel
    private class LoginPanel extends JPanel {
        private final JTextField idField;
        private final JPasswordField passwordField;
        private final JButton loginButton;
		private final JCheckBox managerCheckBox;


        public LoginPanel() {
            // Set the layout
            setLayout(null);

            // Create the components
            JLabel idLabel = new JLabel("ID:");
            idField = new JTextField(150);
			idLabel.setBounds(10,20, 100, 40);
			idField.setBounds(100, 20, 200,40);

            JLabel passwordLabel = new JLabel("Password:");
            passwordField = new JPasswordField(150);
			passwordLabel.setBounds(10,80, 100,40);
			passwordField.setBounds(100, 80, 200,40);
			
			managerCheckBox = new JCheckBox("Are you a head of department?");
			managerCheckBox.setBounds(10, 140, 200, 20);

			loginButton = new JButton("Login");
			loginButton.setBounds(150, 180, 100, 40);

            // Add the components to the panel
            add(idLabel);
            add(idField);
            add(passwordLabel);
            add(passwordField);
			add(managerCheckBox);
            add(loginButton);

            // Set up the button action listener
            loginButton.addActionListener(e ->{
                    // Call imaginary method to check the login credentials
                    // boolean successful = checkLoginCredentials(emailField.getText(), new String(passwordField.getPassword()));
                    // if (successful) {
                    //     // Show confirmation message
                    //     JOptionPane.showMessageDialog(LoginPanel.this, "Login successful!");
                    // } else {
                    //     // Show error message
                    //     JOptionPane.showMessageDialog(LoginPanel.this, "Login failed. Please check your email and password.");
                    // }
                }
            );
        }
    }
}

