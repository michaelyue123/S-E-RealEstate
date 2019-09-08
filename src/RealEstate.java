import java.io.FileNotFoundException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;

public class RealEstate {
    private HashMap<Integer, String> suburb_list = new HashMap<Integer, String>();
    private Scanner sc = new Scanner(System.in);
    private static Connection connection = null;

    public RealEstate() {

    }

    public void startRealEstate(){
        connectJDBCToAWSEC2();

        loadSuburb();


    }

    //this is connect class, this is used to connect to aws server where the database is built.
    public static void connectJDBCToAWSEC2() {

        System.out.println("----MySQL JDBC Connection Testing -------");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("MySQL JDBC Driver Registered!");


        try {
            connection = DriverManager.
                    getConnection("jdbc:mysql://" + "database-2.clfr5ittcqg1.ap-southeast-2.rds.amazonaws.com" + ":" + 3306 + "/" + "realestate", "duan", "duan953280");
        } catch (SQLException e) {
            System.out.println("Connection Failed!:\n" + e.getMessage());
        }

        if (connection != null) {
            System.out.println("SUCCESS!!!!");
        } else {
            System.out.println("FAILURE! Failed to make connection!");
        }

    }

    public void loadSuburb(){
        try{
            System.out.println("Loading suburb list...");
            BufferedReader reader = new BufferedReader(new FileReader("src//suburb.csv"));
            reader.readLine();
            String line = null;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",",2);
                suburb_list.put(Integer.parseInt(item[0]),item[1]);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(suburb_list.get(3000));
    }

    public void register(){
        String custName = null;
        String emailAddress = null;
        String passWord = null;
        System.out.println("");

        try{

            Statement stmt=connection.createStatement();
            String query = " insert into customer (passWord, custName, emailAddress)"
                    + " values (?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, custName);
            preparedStmt.setString (2, emailAddress);
            preparedStmt.setString (3, passWord);
            preparedStmt.execute();
            // insert lines into database

            ResultSet rs=stmt.executeQuery("select * from customer where emailAddress = " + emailAddress);
            while(rs.next()){
                System.out.printf("%-28s", "Customer Id: ");
                System.out.printf(rs.getInt(1)+ "\n");
                System.out.printf("%-28s", "Customer Name: ");
                System.out.printf(rs.getString(3)+ "\n");
                System.out.printf("%-28s", "Email address ");
                System.out.printf(rs.getString(4)+ "\n");
            }
        }catch(Exception e){ System.out.println(e);}

    }

}


