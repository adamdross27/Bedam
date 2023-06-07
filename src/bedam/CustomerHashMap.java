/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bedam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author adamross
 */
public class CustomerHashMap 
{
    static final Map<Integer, Booking> customerHashMap = new HashMap<>(); 
    
    
    public void putBooking(Booking booking)
    {
        int bookingNum = booking.getBookingNum();
        Writer.printBookingToFile(bookingNum, booking);
        
        customerHashMap.put(bookingNum, booking);  //Might not need this if saving to txt file

    }
    
    public void askForBookingNum()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your booking number: ");
        int userBookingNum = scan.nextInt();
        
        boolean bookingNumExists = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./resources/GuestInformation.txt"));
            String line;
            while((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");                                       //POTENTIALLY MOVE THIS TO THE READER CLASS????
                if(parts.length == 2 && parts[0].equals(userBookingNum))
                {
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
    
    public static void fillHashMap(String str)
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
    
    public static void printHashMap()
    {
  //      System.out.println(customerHashMap.size());
        for(Map.Entry<Integer, Booking> entry : customerHashMap.entrySet())
        {
            System.out.println(entry.getKey()+ ":" + entry.getValue().toString());
        }
    }
    
    public static void cancelBooking() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter booking number to cancel.");
        boolean validInput = false;
        int bookingNum = -1;
        while (!validInput) {
            try {
                bookingNum = scan.nextInt();
                if (customerHashMap.containsKey(bookingNum)) {
                    System.out.println("That is a valid booking");
                    validInput = true;
                    scan.nextLine(); //So the next question doesn't use return key as the input for the answer.
                } else {
                    throw new InputMismatchException("Invalid input. Please enter a valid booking number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scan.nextLine();
            }
        }
        System.out.println("You are about to cancel booking " + bookingNum);
        System.out.println("Booking number " + bookingNum + " contains the following booking information:");
        System.out.println(customerHashMap.get(bookingNum).toString());
        System.out.println("Are you sure you want to cancel this booking? (Y/N)");
        
        char cancellationCheck = 'n';
        validInput = false;
        while (!validInput) {
            try {
                String cancellationString = scan.next();
                cancellationCheck = Character.toLowerCase(cancellationString.charAt(0));
                if (cancellationCheck == 'y' || cancellationCheck == 'n') {
                    validInput = true;
                } else {
                    throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'.");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
        
        if (cancellationCheck == 'y' || cancellationCheck == 'Y') {
            //cancel
            LocalDate today = LocalDate.now();
            LocalDate cutOffDate = customerHashMap.get(bookingNum).getCheckInDate().minusDays(7);
            if (cutOffDate.isBefore(today)) {
                System.out.println("Your booking is within 7 days of today. There will be no refund for any cancellations with less than 7 days notice.");
                System.out.println("Are you sure you want to cancel this booking? (Y/N)");
    
                char lateCancellationCheck = 'n';
                validInput = false;
                while (!validInput) {
                    try {
                        String lateCancellationString = scan.next();
                        lateCancellationCheck = Character.toLowerCase(lateCancellationString.charAt(0));
                        if (lateCancellationCheck == 'y' || lateCancellationCheck == 'n') {
                            validInput = true;
                        } else {
                            throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            System.out.println("Cancelling booking.");
            customerHashMap.remove(bookingNum);
            Writer.writeFromHashMap();
           
        } else {
            System.out.println("We will not cancel this booking.");
            return;
        }

    }
}
