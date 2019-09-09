package application;

import java.sql.*;

public class LinkDatabase {
    private static Connection connection = null;

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
