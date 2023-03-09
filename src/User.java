public class User {
    private static int _id;
    private static String Name;
    private static String Position;
    private static String Department;
    private static boolean isManager;


    static void createUser(int id,String name, String position, String department, boolean Manager){
        _id = id;
        Name = name;
        Position = position;
        Department = department;
        isManager = Manager;
    }

    static String getName(){
        return Name;
    }

    static String getPosition(){
        return Position;
    }

    static String getDepartment(){
        return Department;
    }

    static boolean getIsManager(){
        return isManager;
    }

    static int getUserId(){
        return _id;
    }
}
