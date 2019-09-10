package application;

import java.sql.*;

public class LinkDatabase {


    private static Connection connection = null;
    private static Statement stmt;

    public LinkDatabase() throws SQLException {
    }

    public static Connection getConnection() {
        return connection;
    }

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
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void register(String passWord,String custName, String emailAddress ){
        try{


            String query = " insert into customer (passWord, custName, emailAddress)"
                    + " values (?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, passWord);
            preparedStmt.setString (2, custName);
            preparedStmt.setString (3, emailAddress);
            preparedStmt.execute();
            // insert lines into database
            preparedStmt = connection.prepareStatement(
                    "select * from customer where  emailAddress =  ?");
            preparedStmt.setString(1,emailAddress);
            ResultSet rs=preparedStmt.executeQuery();
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
//    public static void test(){
//        try{
//            String emailAddress = "duanxinhuan@163.com";
//        String sql = "select * from customer where customerId = "'+emailAddress+'"";
//        ResultSet rs=stmt.executeQuery(sql);
//        while(rs.next()){
//            System.out.printf("%-28s", "Customer Id: ");
//            System.out.printf(rs.getInt(1)+ "\n");
//            System.out.printf("%-28s", "Customer Name: ");
//            System.out.printf(rs.getString(3)+ "\n");
//            System.out.printf("%-28s", "Email address ");
//            System.out.printf(rs.getString(4)+ "\n");} }
//        catch (Exception e){System.out.println(e);}
//    }

}
