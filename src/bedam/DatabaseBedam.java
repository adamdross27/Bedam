/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment Two - Hotel Booking System
 *
 */
package bedam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benr0
 */
public class DatabaseBedam {

    static String URL = "jdbc:derby://localhost:1527/theBedamDB";
    static String USERNAME = "bedam";
    static String PASSWORD = "bedam"; //Variables for JDBC
    static String TABLENAME = "THEBOOKINGS";

//    public static void main(String[] args) throws IOException, ClassNotFoundException {
////        CustomerHashMap.customerHashMap.clear();
//  //      CustomerHashMap.fillHashMap(Reader.readHashMapFile());
//        //CustomerHashMap.printHashMap();
//       
//
//    }                  This method below is how we would add a booking to our database which can be saved offline.
    public static void addToDB(int bookingNum, String locationStr, int bedrooms, int bathrooms, int numNightsBooked, double totalCost, LocalDate checkInDate, LocalDate checkOutDate) throws ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection conn;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); //Connects to the database

            String sql = "INSERT INTO THEBOOKINGS(BOOKINGNUM, LOCATIONSTR, BEDROOMS, BATHROOMS, NUMNIGHTS, PRICE, CHECKINDATE, CHECKOUTDATE)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql); //Preparing statement which will insert each value into the database.

            BufferedReader reader;

            statement.setInt(1, bookingNum);  //This gets the java variables and inputs these for each parameter so it can be inserted into the database.
            statement.setString(2, locationStr);
            statement.setInt(3, bedrooms);
            statement.setInt(4, bathrooms);
            statement.setInt(5, numNightsBooked);
            statement.setDouble(6, totalCost);
            //convert to date

            java.sql.Date sqlCheckInDate = java.sql.Date.valueOf(checkInDate); //This converts the LocalDate to the SQL Date
            java.sql.Date sqlCheckOutDate = java.sql.Date.valueOf(checkOutDate);

            statement.setDate(7, sqlCheckInDate); //Implementing the SQL dates
            statement.setDate(8, sqlCheckOutDate);

            statement.executeUpdate(); //Updating the statement
            statement.close(); //Closing the statement

            conn.close(); //Closing the connection
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBedam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void removeBookingFromDB(int bookingNum) { //This method will be called if the user enters a valid int AND the int is a current booking number
        try {
            String sql = "DELETE FROM " + TABLENAME + " WHERE BOOKINGNUM = ?"; //Preparing the statement, the ? will be replaced by the bookingNum
            Connection conn;

            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement remove = conn.prepareStatement(sql);
            remove.setInt(1, bookingNum); //Inserting the bookingNum to replace the ?
            remove.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBedam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean checkBookingNumInDB(int bookingNum) { //Checks the int from the user is in the database as a current booking number
        String sql = "SELECT BOOKINGNUM FROM " + TABLENAME;
        Connection conn;

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); //Connecting to database
            Statement statement = conn.createStatement();
            
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) //This while loop iterates through each booking number and checks if it matches with the int provided by user
            {
                int key = rs.getInt("BOOKINGNUM");
                if(bookingNum == key)
                {
                    return true; //If found
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBedam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; //If not found
    }

}
