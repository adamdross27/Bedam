/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Hotel Booking System
 *
 */
package bedam;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BedamApp {
    
    

    public static void main(String[] args) throws FileNotFoundException, IOException {

        System.out.println("Welcome to Bedam!");
        System.out.println("What would you like to do with us today?");
        System.out.println("\nPress the number correlating to the option.");
        menu();
        
    }

    private static void menu() throws IOException {
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        CustomerHashMap.customerHashMap.clear();
        CustomerHashMap.fillHashMap(Reader.readHashMapFile());
        CustomerHashMap.printHashMap();
        
        while (choice != 8) {
            
            System.out.println("1. About us");
            System.out.println("2. About Bedam Facilities");
            System.out.println("3. Make a booking");
            System.out.println("4. Cancel a booking");
            System.out.println("5. See availability");
            System.out.println("6. View add-ons");
            System.out.println("7. View Contact Information");
            System.out.println("8. Exit");

            choice = 0;

            //check for valid inputs
            boolean validInput = false;
            while (!validInput) {
                try {
                    choice = scan.nextInt();
                    validInput = true;
                } catch (InputMismatchException e) { //if input isn't valid, try again
                    System.out.println("That was not a recognisable response, please try again.");
                    scan.nextLine();
                }
            }
            System.out.println("");

            //complete
            switch (choice) {
                case 1:
                    System.out.println("About Bedam");
                    Reader.about();
                    break;

                //make new class
                case 2:
                    System.out.println("Loading Information...\n");
                    Reader.bedamFacilities();
                    break;

                case 3:
                    System.out.println("Make a booking");
                    MakeBooking.newBooking();
                    break;

                case 4:
                    System.out.println("Cancel booking");
                    //cancelBooking method
                    break;

                case 5:
                    System.out.println("See Availability");
                    //show availability
                    break;

                case 6:
                    System.out.println("View All Bookings (dw get rid of this later just for testing)");
                    
                    break;
                case 7:
                    System.out.println("Loading Contact Information...\n");
                    Reader.contactInformation();
                    break;
                case 8:
                    // End Program
                    break;

                default:
                    System.out.println("Sorry, that isn't an option. Try again.\n");
            }
        }
        System.out.println("Thank you for using the Bedam service. See you later!");
    }
}
