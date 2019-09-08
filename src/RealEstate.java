import java.io.FileNotFoundException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;

public class RealEstate {
    HashMap<Integer, String> suburb_list = new HashMap<Integer, String>();
    Scanner sc = new Scanner(System.in);

    public RealEstate() {

    }

    public void startRealEstate(){

        loadSuburb();
        register();


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
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/realestate?serverTimezone=Australia/Sydney","root","D1710911879!");
//here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
            String query = " insert into customer (passWord, custName, emailAddress)"
                    + " values (?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, custName);
            preparedStmt.setString (2, emailAddress);
            preparedStmt.setString (3, passWord);
            preparedStmt.execute();
            // insert lines into database

            ResultSet rs=stmt.executeQuery("select * from customer where emailAddress = ");
            while(rs.next()){
                System.out.printf("%-28s", "Customer Id: ");
                System.out.printf(rs.getInt(1)+ "\n");
                System.out.printf("%-28s", "Customer Name: ");
                System.out.printf(rs.getString(3)+ "\n");
                System.out.printf("%-28s", "Email address ");
                System.out.printf(rs.getString(4)+ "\n");
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}

    }

}


