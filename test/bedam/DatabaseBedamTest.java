/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package bedam;

import bedam.DatabaseBedam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author benr0
 */
public class DatabaseBedamTest {
    
    private static String URL = "jdbc:derby://localhost:1527/theBedamDB";
    private static String USERNAME = "bedam";
    private static String PASSWORD = "bedam";
    private static String TABLENAME = "THEBOOKINGS";

    private Connection connection;
    
    @BeforeEach
    public void setUp() throws SQLException {
        try{ 
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            fail("failed to connect to db "+ ex.getMessage());
        }
    }
    
//    @AfterEach
//    public void tearDown() throws SQLException {
//        try { 
//            if(connection != null && !connection.isClosed())
//            {
//                connection.close();
//            }
//        } catch (SQLException ex) {
//            fail("Failed to close db connection "+ ex.getMessage());
//        }
//    }

    @Test
    public void testAddToDB()
    {
        //Test Data
        int bookingNum = 1;
        String locationStr = "House";
        int bedrooms = 4;
        int bathrooms = 3;
        int numNightsBooked = 5;
        double totalCost = 1125.0;
        LocalDate checkInDate = LocalDate.now();
        LocalDate checkOutDate = LocalDate.now().plusDays(3);
        
        try {
            DatabaseBedam.addToDB(bookingNum, locationStr, bedrooms, bathrooms, numNightsBooked, totalCost, checkInDate, checkOutDate);
        } catch (ClassNotFoundException e)
        {
            fail("Database failed to load..." +e.getMessage());
        }
        
        String sql = "SELECT * FROM "+ TABLENAME + " WHERE BOOKINGNUM = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, bookingNum);
            ResultSet rs = statement.executeQuery();
            assertTrue(rs.next(), "No rows found in database for test");
        } catch (SQLException e)
        {
            fail("Database failed to load...");
        }
    }
}
