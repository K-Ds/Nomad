public class Main {
    public static void main(String[] args) {
        // AuthFrame authFrame = new AuthFrame();
        // authFrame.setSize(500, 500);
        try{
            Database.dbConnect();
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.exit(0);
        }
        AuthFrame authFrame =  new AuthFrame();
        authFrame.setSize(600, 500);
    }
}