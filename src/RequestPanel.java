import javax.sql.RowSet;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class RequestPanel extends JPanel {
    JButton newTrip;

    public RequestPanel(String titleName, String tableName, JFrame parentFrame){
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBackground(Color.BLUE);
        headerPanel.setPreferredSize(new Dimension(150, 100));


        JLabel title = new JLabel(titleName);
        title.setFont(new Font("Poppins", Font.BOLD, 25));
        title.setBounds(500, 2, 500, 50);
        if(!User.getIsManager()){
            newTrip = new JButton("New");
            newTrip.setBounds(10, 50, 150, 30);
            newTrip.addActionListener(e -> {
                if(tableName.equals("travels")){
                    new TravelRequestDetailsPanel(0, "new");
                }
                else if(tableName.equals("fundings")){
                    new FundRequestDetailsPanel(0, "new");
                }
                else if(tableName.equals("workshops")){
                    new WorkshopRequestDetailsPanel(0, "new");
                }
            });
            headerPanel.add(newTrip);
        }
       
        headerPanel.add(title);

        add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(1000, 500));
        contentPanel.setLayout(null);

        RequestListScrollPane scrollPane = new RequestListScrollPane(tableName, parentFrame);
        contentPanel.add(scrollPane);
        add(contentPanel, BorderLayout.CENTER);
    }

    private class RequestListScrollPane extends JScrollPane{
        public RequestListScrollPane(String tableName, JFrame parentFrame) {
            setBounds(0, 0, 1000, 500);
            
            try{
                if(!User.getIsManager()){
                    Database.getRecords(tableName, User.getUserId());
                }
                else{
                    Database.getRecords(tableName, User.getDepartment());
                }
                
                RowSet rs = Database.getRowSet();

                int numCols = rs.getMetaData().getColumnCount() - 1;
                rs.beforeFirst();

                // Create a new DefaultTableModel object
                DefaultTableModel tableModel = new DefaultTableModel(){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                    // Make the cells not editable
                    return false;
                    }
                };

                for (int i = 1; i <= numCols; i++) {
                    tableModel.addColumn(rs.getMetaData().getColumnName(i+1));
                }
        
                while (rs.next()) {
                    Object[] rowData = new Object[numCols];
                    for (int i = 1; i <= numCols; i++) {
                    rowData[i-1] = rs.getObject(i+1);
                    }
                    tableModel.addRow(rowData);
                }

                // Create the table with the data model
                JTable table = new JTable(tableModel);
                table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event){
                        int selectedRow = table.getSelectedRow();
                        
                        try{
                            if(!User.getIsManager()){
                                Database.getRecords(tableName, User.getUserId());
                            }
                            else{
                                Database.getRecords(tableName, User.getDepartment());
                            }
                        }
                        catch(SQLException ex){
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Sql error", JOptionPane.ERROR_MESSAGE);
                        }

                        if(!User.getIsManager()){
                            if(tableName.equals("travels")){
                                new TravelRequestDetailsPanel(selectedRow, "update");
                            }
                            else if(tableName.equals("fundings")){
                                new FundRequestDetailsPanel(selectedRow, "update");
                            }
                            else if(tableName.equals("workshops")){
                                new WorkshopRequestDetailsPanel(selectedRow, "update");
                            }
                            parentFrame.dispose();
                        }     
                        else{
                            try{
                                parentFrame.dispose();
                                displayRow(Database.getRowSet(), selectedRow, tableName);
                            }
                            catch(SQLException ex){
                                JOptionPane.showMessageDialog(null, ex.getMessage(), "Sql error", JOptionPane.ERROR_MESSAGE);
                            }
                           
                        }
                        
                    }
                });
                table.setPreferredSize(new Dimension(1000, 500));
                setViewportView(table);
            
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Sql error", JOptionPane.ERROR_MESSAGE);
            }
        }

        public static void displayRow(RowSet rowSet, int row,String tableName) throws SQLException {
            // Create a panel to hold the labels
            JPanel panel = new JPanel(new GridLayout(rowSet.getMetaData().getColumnCount()+2, 2));
    
            // Add a label for each column in the row set
            rowSet.absolute(row+1);
            int id = rowSet.getInt("_id");
            for (int i = 1; i <= rowSet.getMetaData().getColumnCount(); i++) {
                JLabel label = new JLabel(rowSet.getMetaData().getColumnName(i));
                JTextField text = new JTextField(rowSet.getString(i));
                panel.add(label);
                panel.add(text);
            }
            
            JButton approveButton = new JButton("Approve");
            approveButton.addActionListener(e ->{
                    try{
                        Database.changeRequestStatus(id, tableName, "approved");
                        // Close the dialog
                        ((JDialog) panel.getTopLevelAncestor()).dispose();
                        new AdminFrame();
                    }
                    catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Sql error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            );
            JButton denyButton = new JButton("Deny");
            denyButton.addActionListener(e ->{
                try{
                    Database.changeRequestStatus(id, tableName, "denied");
                    // Close the dialog
                    ((JDialog) panel.getTopLevelAncestor()).dispose();
                    new AdminFrame();
                }
                catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Sql error", JOptionPane.ERROR_MESSAGE);
                }
                }
            );
            JButton canceButton = new JButton("Cancel");
            canceButton.addActionListener(e ->{
                    // Close the dialog
                    ((JDialog) panel.getTopLevelAncestor()).dispose();
                    new AdminFrame();
                }
            );
            panel.add(approveButton);
            panel.add(denyButton);
            panel.add(canceButton);
            panel.add(new JLabel()); //placeholder

            // Create a dialog to display the labels
            JDialog dialog = new JDialog();
            dialog.setContentPane(panel);
            dialog.pack();
            dialog.setVisible(true);
        }
    }
}