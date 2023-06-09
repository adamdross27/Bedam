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
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author adamross
 */
public class CustomerHashMap 
{
    static final Map<Integer, Booking> customerHashMap = new HashMap<>(); //Hashmap declared
    
    
    public void putBooking(Booking booking)
    {
        int bookingNum = booking.getBookingNum(); //puts booking into hashmap and calls the printBookingToFile method
        Writer.printBookingToFile(bookingNum, booking);
        
        customerHashMap.put(bookingNum, booking);  

    }
    
    public void askForBookingNum()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your booking number: ");//Asks user for booking number
        int userBookingNum = scan.nextInt();
        
        boolean bookingNumExists = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./resources/GuestInformation.txt"));
            String line;
            while((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");                                       //POTENTIALLY MOVE THIS TO THE READER CLASS????
                if(parts.length == 2 && parts[0].equals(userBookingNum))
                { //This while loop checks if the booking number exists in the txt file that permanently stores bookings.
                    bookingNumExists = true;
                    break;
                }
            }
            reader.close();
        } catch (IOException e) 
        {
            System.out.println("Error Reading File...");
        }
        
        if(bookingNumExists)
        {
            Booking booking = customerHashMap.get(userBookingNum);
            //cancel / view order
        }
        else
        {
            System.out.println("Booking number isn't recognised...");
        }
    }
    
    public boolean checkNameInHashMap(String name)
    {
        return customerHashMap.containsKey(name);
    }
    
    public static void fillHashMap(String str) //This method gets the hashmap details from the txt and then puts those into the hashmap so it can be added to
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./resources/GuestInformation.txt"));
            String line = "";
            String longString = "";
            
            while ((line = reader.readLine()) != null) {
                longString += line;                
            }
            
            String[] parts = longString.split(",");
            
            
            for(int i = 0; i<parts.length; i+=2)
                {
                    customerHashMap.put(Integer.parseInt(parts[i]), Booking.bookingFromString(parts[i+1]));
                }
                
            reader.close();
        } catch (IOException e) {
            System.out.println("Error Reading File...");
        }
        
        
    }
    
    public static void printHashMap() //Prints the hashmap (used for testing)
    {
  //      System.out.println(customerHashMap.size());
        for(Map.Entry<Integer, Booking> entry : customerHashMap.entrySet())
        {
            System.out.println(entry.getKey()+ ":" + entry.getValue().toString());
        }
    }
}
