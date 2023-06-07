/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Accommodation Booking System
 *
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

public class CustomerHashMap 
{
      //make the hashmap that will be utilised throughout the whole class
    static final Map<Integer, Booking> customerHashMap = new HashMap<>(); 

    //putBooking takes in a booking and gets its bookingNum and puts the booking in with it's bookingNum as the key
    public void putBooking(Booking booking)
    {
        int bookingNum = booking.getBookingNum();
        Writer.printBookingToFile(bookingNum, booking);
        
        customerHashMap.put(bookingNum, booking);  //Might not need this if saving to txt file

    }
    //this method fills the hashmap using a string, this string contains all the information needed to make a hashmap entry
    public static void fillHashMap(String str)
    {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("./resources/GuestInformation.txt"));
                String line = "";
                String longString = "";
                //read through the file containing the information needed and save it to a variable
                while ((line = reader.readLine()) != null) {
                    longString += line;
                }
                //split the text file into parts that represent entries in the hashmap
                String[] parts = longString.split(",");
                
                //this for loop iterates through the above array, the array starts with bookingNums, and then alternates between bookings and bookingNums, meaning each 2 items in the array make one hashmap entry
                for (int i = 0; i < parts.length; i += 2) 
                {
                    String bookingNumString = parts[i].trim();
                    if(!bookingNumString.isEmpty())
                    {
                        int bookingNum = Integer.parseInt(bookingNumString);
                        Booking booking = Booking.bookingFromString(parts[i+1]);
                        customerHashMap.put(bookingNum, booking);
                    }
                }
                reader.close();
                
            } catch (IOException e) {
                System.out.println("Error Reading File...");
            }     
    }
    
    public static void cancelBooking() throws IOException 
    {
        Scanner scan = new Scanner(System.in);
        //check if there is anything to delete
        if(CustomerHashMap.customerHashMap.isEmpty())
        {
            //print error message
            System.out.println("There are no bookings saved!");
            System.out.println("Please make a booking!");
        }
        else 
        {
            //get a booking num for user
            System.out.println("Please enter booking number to cancel.");
            boolean validInput = false;
            //default value that will never be a key in the hashmap
            int bookingNum = -1;
            //get a valid integer
            while (!validInput) {
                try {
                    bookingNum = scan.nextInt();
                    //check integer is a key in hashmap
                    if (customerHashMap.containsKey(bookingNum)) {
                        validInput = true;
                        scan.nextLine(); 
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
            
            //get valid char for y/n
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
                //we implemented a cut off date for one weeks notice for cancellations otherwise there would be no 'refund'
                LocalDate today = LocalDate.now();
                LocalDate cutOffDate = customerHashMap.get(bookingNum).getCheckInDate().minusDays(7);
                if (cutOffDate.isBefore(today)) {
                    System.out.println("Your booking is within 7 days of today. There will be no refund for any cancellations with less than 7 days notice.");
                    System.out.println("Are you sure you want to cancel this booking? (Y/N)");
                    
                    char lateCancellationCheck;
                    validInput = false;
                    while (!validInput) { //input validator
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
                //remove booking from hashmap
                customerHashMap.remove(bookingNum);
                //update the hashmap text file to represent changes
                Writer.writeFromHashMap();

            } else {
                System.out.println("We will not cancel this booking.");
            }
        }

    }
}
