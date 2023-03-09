import javax.sql.RowSet;
import javax.swing.*;

import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;

public class TravelRequestDetailsPanel extends JFrame implements RequestDetailsPanel{
    JTextField titleField;
    JTextArea descriptionField;
    JTextField dateCreatedField;
    JTextField departmentField;
    JTextField statusField;
    JTextField dateOfTravelField;
    JTextField meansOfTravelField;
    JTextArea expenseDescriptionField;
    JTextField expenseAmountField;
    JButton saveButton;
    JButton cancelButton;
    JLabel departmentLabel;
    JLabel statusLabel;
    JLabel dateCreatedLabel;
    int id;

    public TravelRequestDetailsPanel(int row, String purpose){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JScrollPane scrollPane =  new JScrollPane();
        JPanel contentPanel = new JPanel();
        try{
            contentPanel.setLayout(new GridLayout(10, 2, 3,3));

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
    
            JLabel dateOfTravelLabel = new JLabel("Date of Travel");
            dateOfTravelField = new JTextField();
    
            JLabel meansOfTravelLabel = new JLabel("Means of Travel");
            meansOfTravelField = new JTextField();
            
            JLabel expenseDescriptionLabel = new JLabel("Expense Description");
            expenseDescriptionField = new JTextArea();        
    
            JLabel expenseAmountLabel = new JLabel("Expense Amount");
            expenseAmountField = new JTextField();
        
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
                String dateOfTravel = dateOfTravelField.getText();
                String meansOfTravel = meansOfTravelField.getText();
                String expenseDescription = expenseDescriptionField.getText();
                int expenseAmount = Integer.parseInt(expenseAmountField.getText());
                try{
                    if(purpose.equals("update")){
                        Database.updateTravel(id, title, description, department, dateOfTravel, meansOfTravel, expenseDescription, expenseAmount);
                    }
                    else{
                        int newId;
                        Database.getRecords("travels");
                        RowSet rs1 = Database.getRowSet();
                        if(rs1.next()){
                            rs1.absolute(-1);
                            newId = rs1.getInt("_id") + 1;
                        }
                        else {
                            newId = 100001;
                        }
                        Database.insertTravel(newId,title, description, User.getUserId(), User.getDepartment(), dateOfTravel, meansOfTravel, expenseDescription, expenseAmount);
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
            contentPanel.add(dateOfTravelLabel);
            contentPanel.add(dateOfTravelField);
            contentPanel.add(meansOfTravelLabel);
            contentPanel.add(meansOfTravelField);
            contentPanel.add(expenseDescriptionLabel);
            contentPanel.add(expenseDescriptionField);
            contentPanel.add(expenseAmountLabel);
            contentPanel.add(expenseAmountField);
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

        expenseAmountField.setText((int)rs.getObject("ExpenseAmount") + "");
        expenseAmountField.setEditable(inputsEditable);

        expenseDescriptionField.setText((String)rs.getObject("ExpenseDescription"));
        expenseDescriptionField.setEditable(inputsEditable);

        meansOfTravelField.setText((String)rs.getObject("MeansOfTravel"));
        meansOfTravelField.setEditable(inputsEditable);

        Date dateOfTravelDate = (Date)rs.getObject("DateOfTravel");
        dateOfTravelField.setText(dateOfTravelDate.toString());
        dateOfTravelField.setEditable(inputsEditable);

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