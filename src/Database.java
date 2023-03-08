import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import java.sql.*;

public class Database {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/nomad_db";
    static final String USER = "root";
    static final String PASSWORD = "David123";
    static JdbcRowSet rowSet = null;


    static void dbConnect() throws Exception{
    	Class.forName(DRIVER);
    	
        rowSet = RowSetProvider.newFactory().createJdbcRowSet();
        //Connection
        rowSet.setUrl(DATABASE_URL);
        rowSet.setUsername(USER);
        rowSet.setPassword(PASSWORD);
    }

    static String getCredentials(int _id) throws  SQLException{
        String sql = "SELECT * FROM staff WHERE _id = ?";
        rowSet.setCommand(sql);
        rowSet.setInt(1, _id);
        rowSet.execute();
        if(rowSet.next()){
            return (String)rowSet.getObject("Password");
        }
        else{
            return "Err_InvalidId";
        }
        
    }

    static void getDepartments() throws SQLException{
        String sql = "SELECT DISTINCT(Department) FROM headofdepartment";
        rowSet.setCommand(sql);
        rowSet.execute();
    }

    static void insertStaff(int _id, String name, String position, String department, String password) throws SQLException {
        String sql = "SELECT * FROM staff";
        rowSet.setCommand(sql);
        rowSet.execute();
        rowSet.moveToInsertRow();
        rowSet.updateInt(1, _id);
        rowSet.updateString(2, name);
        rowSet.updateString(3, position);
        rowSet.updateString(4, department);
        rowSet.updateString(5, password);
        rowSet.insertRow();
        rowSet.moveToCurrentRow();
    }

    static void insertHeadOfDepartment(int _id, String name, String department, String email, String password) throws SQLException {
        String sql = "SELECT * FROM headdfdepartment";
        rowSet.setCommand(sql);
        rowSet.execute();
        rowSet.moveToInsertRow();
        rowSet.updateInt(1, _id);
        rowSet.updateString(2, name);
        rowSet.updateString(3, department);
        rowSet.updateString(4, email);
        rowSet.updateString(5, password);
        rowSet.insertRow();
        rowSet.moveToCurrentRow();
    }

    static void changeRequestStatus(int _id, String requestType, String status) throws SQLException {
        String sql = "SELECT * FROM " + requestType +" WHERE _id=?";
        rowSet.setCommand(sql);
        rowSet.setInt(1, _id);
        rowSet.execute();

        while(rowSet.next()){
            rowSet.updateString("Status", status);
            rowSet.updateRow();
        }
    }

    static void insertFunding(int _id, String title, String description,int requestedBy, String department, int amount) throws SQLException{
        Date dateCreated = new Date(System.currentTimeMillis());
        String sql = "SELECT * FROM fundings";
        rowSet.setCommand(sql);
        rowSet.execute();
        rowSet.moveToInsertRow();
        rowSet.updateInt(1, _id);
        rowSet.updateString(2, title);
        rowSet.updateString(3,description);
        rowSet.updateDate(4, dateCreated);
        rowSet.updateInt(5, requestedBy);
        rowSet.updateString(6, department);
        rowSet.updateString(7, "pending");
        rowSet.updateInt(8, amount);
        rowSet.insertRow();
        rowSet.moveToCurrentRow();
    }

    static void updateFunding(int _id, String title, String description, String department, int amount) throws SQLException{
        String sql = "SELECT * FROM fundings WHERE _id=?";
        rowSet.setCommand(sql);
        rowSet.setInt(1, _id);
        rowSet.execute();

        while(rowSet.next()){
            rowSet.updateString(2, title);
            rowSet.updateString(3,description);
            rowSet.updateString(6, department);
            rowSet.updateInt(8, amount);
            rowSet.updateRow();
        }

    }

    static void insertTravel(int _id,String title, String description, int requestedBy, String department, String dateOfTravel, String meansOfTravel, String expenseDescription, int expenseAmount) throws SQLException{
        Date dateCreated = new Date(System.currentTimeMillis());
        String sql = "SELECT * FROM travels";
        rowSet.setCommand(sql);
        rowSet.execute();
        rowSet.moveToInsertRow();
        rowSet.updateInt(1, _id);
        rowSet.updateString(2, title);
        rowSet.updateString(3,description);
        rowSet.updateDate(4, dateCreated);
        rowSet.updateInt(5, requestedBy);
        rowSet.updateString(6, department);
        rowSet.updateString(7, "pending");
        rowSet.updateString(8, dateOfTravel);
        rowSet.updateString(9, meansOfTravel);
        rowSet.updateString(10, expenseDescription);
        rowSet.updateInt(11, expenseAmount);
        rowSet.insertRow();
        rowSet.moveToCurrentRow();
    }

    static void updateTravel(int _id,String title, String description, String department, String dateOfTravel, String meansOfTravel, String expenseDescription, int expenseAmount) throws SQLException{
        String sql = "SELECT * FROM travels WHERE _id=?";
        rowSet.setCommand(sql);
        rowSet.setInt(1, _id);
        rowSet.execute();

        while(rowSet.next()){
            rowSet.updateString(2, title);
            rowSet.updateString(3,description);
            rowSet.updateString(6, department);
            rowSet.updateString(8, dateOfTravel);
            rowSet.updateString(9, meansOfTravel);
            rowSet.updateString(10, expenseDescription);
            rowSet.updateInt(11, expenseAmount);
            rowSet.updateRow();
        }

    }

    static void insertWorkShop(int _id,String title, String description, int requestedBy, String department, int amount, int numberOfParticipants,String venue) throws SQLException{
        Date dateCreated = new Date(System.currentTimeMillis());
        String sql = "SELECT * FROM workShops";
        rowSet.setCommand(sql);
        rowSet.execute();
        rowSet.moveToInsertRow();
        rowSet.updateInt(1, _id);
        rowSet.updateString(2, title);
        rowSet.updateString(3,description);
        rowSet.updateDate(4, dateCreated);
        rowSet.updateInt(5, requestedBy);
        rowSet.updateString(6, department);
        rowSet.updateString(7, "pending");
        rowSet.updateInt(8, amount);
        rowSet.updateInt(9, numberOfParticipants);
        rowSet.updateString(10, venue);
        rowSet.insertRow();
        rowSet.moveToCurrentRow();
    }

    static void updateWorkShop(int _id,String title, String description, String department,int amount, int numberOfParticipants,String venue) throws SQLException{
        String sql = "SELECT * FROM workshops WHERE _id=?";
        rowSet.setCommand(sql);
        rowSet.setInt(1, _id);
        rowSet.execute();

        while(rowSet.next()){
            rowSet.updateString(2, title);
            rowSet.updateString(3,description);
            rowSet.updateString(6, department);
            rowSet.updateInt(8, amount);
            rowSet.updateInt(9, numberOfParticipants);
            rowSet.updateString(10, venue);
            rowSet.updateRow();
        }

    }

    static void deleteRecord(int _id, String tableName) throws SQLException{
        String sql = "SELECT * FROM "+ tableName + " WHERE _id= ?";
        rowSet.setCommand(sql);
        rowSet.setInt(1,_id);
        rowSet.execute();
        while (rowSet.next()){
            rowSet.deleteRow();
        }
    }

    static void getRecords(String tableName, int userId) throws SQLException{
        String sql = "SELECT * FROM "+ tableName + " WHERE requestedBy = " + userId;
        rowSet.setCommand(sql);
        rowSet.execute();
    }

    static RowSet getRowSet(){
        return rowSet;
    }
}
