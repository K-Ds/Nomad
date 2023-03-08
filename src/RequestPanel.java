import javax.sql.RowSet;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class RequestPanel extends JPanel {
    JButton newTrip;

    public RequestPanel(String titleName, String tableName){
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBackground(Color.BLUE);
        headerPanel.setPreferredSize(new Dimension(150, 100));


        JLabel title = new JLabel(titleName);
        title.setFont(new Font("Poppins", Font.BOLD, 25));
        title.setBounds(500, 2, 500, 50);
        newTrip = new JButton("New");
        newTrip.setBounds(10, 50, 150, 30);
        

        headerPanel.add(title);
        headerPanel.add(newTrip);
        add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(1000, 500));
        contentPanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 1000, 500);
        scrollPane.setOpaque(true);

        try{
            Database.getRecords(tableName, 100002);
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
                public void valueChanged(ListSelectionEvent event) {
                   // Get the selected row index
                   int selectedRow = table.getSelectedRow();
       
                   // Print the selected row data to the console
                   System.out.println("Selected row: " + selectedRow);
                }
             });
            table.setPreferredSize(new Dimension(1000, 500));
            scrollPane.setViewportView(table);
           
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Sql error", JOptionPane.ERROR_MESSAGE);
        }

        contentPanel.add(scrollPane);
        add(contentPanel, BorderLayout.CENTER);
    }
}