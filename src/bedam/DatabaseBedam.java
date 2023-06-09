/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

    static String URL = "jdbc:derby://localhost:1527/BedamDataBase";
    static String USERNAME = "bedam";
    static String PASSWORD = "bedam";
    static String TABLENAME = "BEDAM.BOOKING";

//    public static void main(String[] args) throws IOException, ClassNotFoundException {
////        CustomerHashMap.customerHashMap.clear();
//  //      CustomerHashMap.fillHashMap(Reader.readHashMapFile());
//        //CustomerHashMap.printHashMap();
//       
//
//    }
    public static void addToDB(int bookingNum, String locationStr, int bedrooms, int bathrooms, int numNightsBooked, double totalCost, LocalDate checkInDate, LocalDate checkOutDate) throws ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection conn;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String sql = "INSERT INTO BOOKING(BOOKINGNUM, LOCATIONSTR, BEDROOMS, BATHROOMS, NUMNIGHTS, PRICE, CHECKINDATE, CHECKOUTDATE)" + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            BufferedReader reader;

            statement.setInt(1, bookingNum);
            statement.setString(2, locationStr);
            statement.setInt(3, bedrooms);
            statement.setInt(4, bathrooms);
            statement.setInt(5, numNightsBooked);
            statement.setDouble(6, totalCost);
            //convert to date

            java.sql.Date sqlCheckInDate = java.sql.Date.valueOf(checkInDate);
            java.sql.Date sqlCheckOutDate = java.sql.Date.valueOf(checkOutDate);

            statement.setDate(7, sqlCheckInDate);
            statement.setDate(8, sqlCheckOutDate);

            statement.executeUpdate();
            statement.close();

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBedam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void removeBookingFromDB(int bookingNum) {
        try {
            String sql = "DELETE FROM " + TABLENAME + " WHERE BOOKINGNUM = ?";
            Connection conn;

            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement remove = conn.prepareStatement(sql);
            remove.setInt(1, bookingNum);
            remove.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBedam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean checkBookingNumInDB(int bookingNum) {
        String sql = "SELECT BOOKINGNUM FROM " + TABLENAME;
        Connection conn;

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
            {
                int key = rs.getInt("BOOKINGNUM");
                if(bookingNum == key)
                {
                    return true;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBedam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}

//            try {
//                reader = new BufferedReader(new FileReader("./resources/GuestInformation.txt"));
//
//                String line = "";
//                String longString = "";
//
//                while ((line = reader.readLine()) != null) {
//                    longString += line;
//                }
//
//                String[] hashmapItems = longString.split(",");
//
//                String[] parts = hashmapItems[1].split("~");
//                int location = Integer.parseInt(parts[0]);
//                int numNightsBooked = Integer.parseInt(parts[1]);
//                String checkInDate = parts[2];
//                String checkOutDate = parts[3];
//                int bedrooms = Integer.parseInt(parts[4]);
//                int bathrooms = Integer.parseInt(parts[5]);
//                double rentPerNight = Double.parseDouble(parts[6]);
//                double totalCost = Double.parseDouble(parts[7]);
//                String locationStr = parts[8];
//                int bookingNum = Integer.parseInt(parts[9]);
//--
//
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(DatabaseBedam.class.getName()).log(Level.SEVERE, null, ex);
//            }
//--
//   PreparedStatement run = connection.prepareStatement("CREATE SCHEMA BEDAM");
//  run.execute();

