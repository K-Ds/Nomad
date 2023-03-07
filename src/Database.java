import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class Database {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/nomad";
    static final String USER = "root";
    static final String PASSWORD = "David123";
    static JdbcRowSet rowSet = null;


    static void dbConnect() throws SQLException {
        rowSet = RowSetProvider.newFactory().createJdbcRowSet();
        //Connection
        rowSet.setUrl(DATABASE_URL);
        rowSet.setUsername(USER);
        rowSet.setPassword(PASSWORD);
    }

    static void insertStaff(int _id, String name, String position, String contractType, String department, String password) throws SQLException {
        String sql = "SELECT * FROM staff";
        rowSet.setCommand(sql);
        rowSet.execute();
        rowSet.moveToInsertRow();
        rowSet.updateInt(1, _id);
        rowSet.updateString(2, name);
        rowSet.updateString(3, position);
        rowSet.updateString(4, contractType);
        rowSet.updateString(5, department);
        rowSet.updateString(7, password);
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
        System.out.println(sql);
        rowSet.setCommand(sql);
        rowSet.setInt(1, _id);
        rowSet.execute();

        while(rowSet.next()){
            rowSet.updateString("Status", status);
            rowSet.updateRow();
        }
    }

    static String getCredentials(int _id) throws  SQLException{
        String sql = "SELECT _id, Password FROM staff WHERE _id = ?";
        rowSet.setCommand(sql);
        rowSet.setInt(1, _id);
        rowSet.execute();
        return (String) rowSet.getObject("Password");
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

    static void getRecords(String tableName) throws SQLException{
        String sql = "SELECT * FROM "+ tableName;
        rowSet.setCommand(sql);
        rowSet.execute();
    }




    public static void main(String[] args){
        try {
            //Connect to database
            dbConnect();

//            insertWorkShop(100111,"AMS training","Training staff how to use the sys",100004,"IT",10000,12,"Board room" );
//            updateWorkShop(100111,"E-Finance training","Training staff how to use the system","Finance",1500,120,"Meeting1 room" );
 //           insertFunding(100111, "Chairs and desks", "In need of funds to purchase new chairs", 100005,"Finance", 10000);
//            updateFunding(100111, "Chairs", "In need of funds to purchase new updated chairs", "Finance", 15000);
 //           insertTravel(100111, "client meet", "Meetin the client in Dubai", 100006,"Finance","2023-03-14","Plane","Plane ticket and accomodation",30000 );
//            updateTravel(100111, "client greet and meet", "Meeting the client in Dubai", "IT","2023-04-01","Plane","Plane ticket and accommodation",35000 );
           //deleteRecord(100111, "travels");
            //Select statement
            getRecords("travels");
            rowSet.next();
            int _id = 100001;
            String status = "approved";
            String requestType = "travels";

            changeRequestStatus(_id, requestType, status);
//            while(rowSet.next()){
//                String title = (String) rowSet.getObject("Title");
//                Date dateCreated = (Date) rowSet.getObject("DateCreated");
//                System.out.println(title + " " + dateCreated);
//            }


        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
