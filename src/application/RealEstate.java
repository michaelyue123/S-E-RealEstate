package application;

import customer.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class RealEstate {
    private HashMap<Integer, String> suburb_list = new HashMap<Integer, String>();
    private Scanner sc = new Scanner(System.in);
    Customers current_customer;

    public RealEstate() {

    }

    public void startRealEstate(){
        LinkDatabase.connectJDBCToAWSEC2();

        loadSuburb();
        
        System.out.println("1.register\n" +"2.login");
        int choice = sc.nextInt();

        switch (choice){
            case 1:
                register();
                break;
            case 2:
                login();
                break;
            case 3:
                System.out.println("See you next time!");
            
        }while(choice !=3);


    }

    private void login() {
        String customer_details;
        try {
            customer_details =LinkDatabase.logIn("duanxinhuan@163.com", "12345678");
            System.out.println(customer_details);
        } catch (SQLException e) {
            e.printStackTrace();
    }
}

   // login function

    //this is connect class, this is used to connect to aws server where the database is built.


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
    }

    public void register() {
        String custName = null;
        String emailAddress = null;
        String passWord = null;
        String passWord2 = null;
        System.out.println("enter your information to register");
        Scanner scan = new Scanner(System.in);
        System.out.println("enter your customer name");
        custName = scan.next();

        do {
            System.out.println("enter your email address,email address must contain @ and.com");
            emailAddress = scan.next();
        }
        while (!emailAddress.contains("@") || !emailAddress.contains(".com"));
        System.out.println("create your password");
        passWord = scan.next();
        System.out.println("confirm your password");
        passWord2 = scan.next();
        while (!passWord.equals(passWord2)) {
            System.out.println("password mismatch ");
            System.out.println("enter your password again");
            passWord = scan.next();
            System.out.println("confirm your password");
            passWord2 = scan.next();
        }
        System.out.println("register successfully");
        System.out.println("here is your account details: ");
        System.out.println("password length: " + (passWord.length()));
        
        LinkDatabase.register(passWord, custName, emailAddress);
    }

}


