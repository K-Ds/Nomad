import javax.sql.RowSet;
import javax.swing.*;
import java.awt.*;

public class AuthFrame extends JFrame {

	private JTabbedPane authTabbedPane;
	private JPanel loginPanel;
	private JPanel registerPanel;

	public AuthFrame() {
        this.dispose();
		loginPanel = new LoginPanel(this);
		registerPanel = new RegisterPanel(this);

		authTabbedPane = new JTabbedPane();

		authTabbedPane.addTab("Login", loginPanel);
		authTabbedPane.addTab("Register", registerPanel);

		add(authTabbedPane);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}

	//Inner class for the register panel
	private class RegisterPanel extends JPanel{
        private JTextField idField;
        private JPasswordField passwordField;
        private JTextField nameField;
        private JTextField positionField;
        private JButton registerButton;
		private JComboBox<String> departmentOption;

        public RegisterPanel(JFrame parentFrame) {
            setLayout(null);
            setPreferredSize(new Dimension(10,50));

            // Create the components
            JLabel idLabel = new JLabel("Id:");
            idField = new JTextField();
            idField.setText("100000");
			idLabel.setBounds(10,20, 100, 40);
			idField.setBounds(100, 20, 200,40);

            JLabel nameLabel = new JLabel("Name:");
            nameField = new JTextField();
            nameField.setText("John Doe");
			nameLabel.setBounds(10,80, 100, 40);
			nameField.setBounds(100, 80, 250,40);

            JLabel positionLabel = new JLabel("Position:");
            positionField = new JTextField();
            positionField.setText("Manager");
			positionLabel.setBounds(10,140, 100, 40);
			positionField.setBounds(100, 140, 250,40);

            JLabel departmentLabel = new JLabel("Department:");
            try{
                Database.getDepartments();
                RowSet rs = Database.getRowSet();
                departmentOption = new JComboBox<>();

                while (rs.next()) {
                  String department = (String)rs.getObject("Department");
                  departmentOption.addItem(department);
                }
                departmentLabel.setBounds(10,200, 100, 25);
                departmentOption.setBounds(100, 200, 250,25);
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
            }
           

            JLabel passwordLabel = new JLabel("Password:");
            passwordField = new JPasswordField();
            passwordField.setText("password");
			passwordLabel.setBounds(10,250, 100, 40);
			passwordField.setBounds(100, 250, 250,40);

            registerButton = new JButton("Register");
			registerButton.setBounds(150,300, 100, 40);
            registerButton.addActionListener(e -> {
                try{
                    int id = Integer.parseInt(idField.getText());
                    String password = new String(passwordField.getPassword());
                    String name = nameField.getText();
                    String  position = positionField.getText();
                    String department = (String) departmentOption.getSelectedItem();

                    Database.insertStaff(id, name, position,  department, password);
                    authTabbedPane.setSelectedIndex(0);
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }
               
            });
            

            // Add the components to the panel
            add(idLabel);
            add(idField);
            add(nameLabel);
            add(nameField);
            add(positionLabel);
            add(positionField);
            add(departmentLabel);
            add(departmentOption);
            add(passwordLabel);
            add(passwordField);
            add(registerButton);
        }
    }

    // Inner class for the login panel
    private class LoginPanel extends JPanel {
        private final JTextField idField;
        private final JPasswordField passwordField;
        private final JButton loginButton;
		private final JCheckBox managerCheckBox;


        public LoginPanel(JFrame parentFrame) {
            // Set the layout
            setLayout(null);

            // Create the components
            JLabel idLabel = new JLabel("ID:");
            idField = new JTextField(150);
			idLabel.setBounds(10,20, 100, 40);
			idField.setBounds(100, 20, 200,40);

            idField.setText("100000");

            JLabel passwordLabel = new JLabel("Password:");
            passwordField = new JPasswordField(150);
			passwordLabel.setBounds(10,80, 100,40);
			passwordField.setBounds(100, 80, 200,40);
			
			managerCheckBox = new JCheckBox("Are you a head of department?");
			managerCheckBox.setBounds(10, 140, 200, 20);

			loginButton = new JButton("Login");
			loginButton.setBounds(150, 180, 100, 40);
            loginButton.addActionListener(e -> {
                int id = Integer.parseInt(idField.getText());
                String password = new String(passwordField.getPassword());
                try{
                    String passwordCheck =  Database.getCredentials(id);
                    if(passwordCheck == "Err_InvalidId"){
                        JOptionPane.showMessageDialog(this, "Incorrect credentials", "Authentication failed", JOptionPane.WARNING_MESSAGE);
                    }
                    else if(password.equals(passwordCheck)){
                        RowSet rs = Database.getRowSet();
                        rs.absolute(1);
                        int _id  = (int) rs.getObject("_id");
                        String name = (String) rs.getObject("name");
                        String position = (String) rs.getObject("position");
                        String department = (String) rs.getObject("department");

                        User.createUser(_id, name, position, department);

                        new UserFrame();
                        parentFrame.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Incorrect credentials", "Authentication failed", JOptionPane.WARNING_MESSAGE);
                    }
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }
               
            });

            // Add the components to the panel
            add(idLabel);
            add(idField);
            add(passwordLabel);
            add(passwordField);
			add(managerCheckBox);
            add(loginButton);
        }
    }
}

