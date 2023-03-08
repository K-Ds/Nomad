public class User {
    private static int Id;
    private static String Name;
    private static String Position;
    private static String Department;


    static void createUser(int id,String name, String position, String department){
        Id = id;
        Name = name;
        Position = position;
        Department = department;
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

    static int getUserId(){
        return Id;
    }
}
