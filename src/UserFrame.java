import javax.sql.RowSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.SQLException;

public class UserFrame extends JFrame {
    JPanel tripRequestPanel;

    public UserFrame() {
        JTabbedPane userTabbedPane = new JTabbedPane();
        tripRequestPanel = new TripRequestPanel();

        userTabbedPane.addTab("Trips", tripRequestPanel);

        add(userTabbedPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class TripRequestPanel extends JPanel {
        JButton newTrip;

        public TripRequestPanel(){
            setLayout(new BorderLayout());

            JPanel headerPanel = new JPanel();
            headerPanel.setLayout(null);
            headerPanel.setPreferredSize(new Dimension(150, 500));

            JLabel title = new JLabel("Trip Requests");
            title.setFont(new Font("Poppins", Font.BOLD, 25));
            title.setBounds(500, 2, 200, 50);
            newTrip = new JButton("New Trip");
            newTrip.setBounds(10, 30, 150, 30);
            

            headerPanel.add(title);
            headerPanel.add(newTrip);
            add(headerPanel, BorderLayout.NORTH);

            JPanel contentPanel = new JPanel();

            JScrollPane scrollPane = new JScrollPane();
    

            try{
                DefaultTableModel tableModel = new DefaultTableModel(
                    new String[]{"title", "dateOfTravel", "meansOfTravel", "status"}, 
                    0);
                
                 // Add some data to the model
                 Database.getRecords("travels");
                 RowSet rs = Database.getRowSet();
                 
                 while(rs.next()){
                    Object[] rowData = {rs.getString("title"), rs.getString("dateOfTravel"), 
                    rs.getString("meansOfTravel"), rs.getString("status")};
                    tableModel.addRow(rowData);
                }
    
                // Create the table with the data model
                JTable table = new JTable(tableModel);
                scrollPane.add(table);
               
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Sql error", JOptionPane.ERROR_MESSAGE);
            }

            

            contentPanel.add(scrollPane);
        
        }
    }
}
