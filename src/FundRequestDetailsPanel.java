import javax.sql.RowSet;
import javax.swing.*;

import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;

public class FundRequestDetailsPanel extends JFrame implements RequestDetailsPanel{
    JTextField titleField;
    JTextArea descriptionField;
    JTextField dateCreatedField;
    JTextField departmentField;
    JTextField statusField;
    JTextField amountField;
    JButton saveButton;
    JButton cancelButton;
    JLabel departmentLabel;
    JLabel statusLabel;
    JLabel dateCreatedLabel;
    int id;

    public FundRequestDetailsPanel(int row, String purpose){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JScrollPane scrollPane =  new JScrollPane();
        JPanel contentPanel = new JPanel();
        try{
            contentPanel.setLayout(new GridLayout(8, 2, 3,3));

            JLabel titleLabel = new JLabel("Title");
            titleField = new JTextField();        
    
            JLabel descriptionLabel = new JLabel("Description");
            descriptionField = new JTextArea();
            
            dateCreatedLabel = new JLabel("Date Created");
            dateCreatedField = new JTextField();
           
            departmentLabel = new JLabel("Department");
            departmentField = new JTextField();
            
            statusLabel = new JLabel("Status");
            statusField= new JTextField();   
    
            JLabel amountLabel = new JLabel("Amount");
            amountField = new JTextField();
        
            if(purpose.equals("update")){
                setUpdate(row);
            }
            else{
                setNew();
            }

            saveButton = new JButton();
            saveButton.setText("Save");
            saveButton.addActionListener(e -> {
                String title =  titleField.getText();
                String description =  descriptionField.getText();
                String department = departmentField.getText();
                int amount = Integer.parseInt(amountField.getText());
                try{
                    if(purpose.equals("update")){
                        Database.updateFunding(id, title, description, department, amount);
                    }
                    else{
                        int newId;
                        Database.getRecords("fundings");
                        RowSet rs1 = Database.getRowSet();
                        if(rs1.next()){
                            rs1.absolute(-1);
                            newId = rs1.getInt("_id") + 1;
                        }
                        else {
                            newId = 100001;
                        }
                        Database.insertFunding(newId, title, description, User.getUserId(), User.getDepartment(), amount);
                    }
                   
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                new UserFrame();
                
            });

            cancelButton = new JButton();
            cancelButton.setText("Cancel");
            cancelButton.addActionListener(e -> {
                this.dispose();
                new UserFrame();
            });


            contentPanel.add(titleLabel);
            contentPanel.add(titleField);
            contentPanel.add(descriptionLabel);
            contentPanel.add(descriptionField);
            contentPanel.add(dateCreatedLabel);
            contentPanel.add(dateCreatedField);
            contentPanel.add(departmentLabel);
            contentPanel.add(departmentField);
            contentPanel.add(statusLabel);
            contentPanel.add(statusField);
            contentPanel.add(amountLabel);
            contentPanel.add(amountField);
            contentPanel.add(saveButton);
            contentPanel.add(cancelButton);
        }
        catch(Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error2", JOptionPane.ERROR_MESSAGE);
        }
        scrollPane.setViewportView(contentPanel);
        add(scrollPane);
        setSize(750, 800);
        setVisible(true);
    }

    public void setUpdate( int row) throws SQLException{
        boolean inputsEditable = false;
        RowSet rs = Database.getRowSet();
        rs.absolute(row + 1);
        id = (int) rs.getObject("_id");

        if("pending".equals((String)rs.getObject("Status"))){
            inputsEditable = true;
        };

        titleField.setText((String)rs.getObject("Title"));
        titleField.setEditable(inputsEditable);

        descriptionField.setText((String)rs.getObject("Description"));
        descriptionField.setEditable(inputsEditable);

        departmentField.setText((String)rs.getObject("Department"));
        departmentField.setEditable(false);

        dateCreatedField.setEditable(false);
        Date dateCreated = (Date)rs.getObject("DateCreated");
        dateCreatedField.setText(dateCreated.toString());

        amountField.setText((int)rs.getObject("Amount") + "");
        amountField.setEditable(inputsEditable);

        statusField.setText((String)rs.getObject("Status"));
        statusField.setEditable(false);
    }

    public void setNew(){
        statusLabel.setEnabled(false);
        statusField.setEnabled(false);

        departmentField.setEnabled(false);
        departmentLabel.setEnabled(false);

        dateCreatedLabel.setEnabled(false);
        dateCreatedField.setEnabled(false);
    }
}