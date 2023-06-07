/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Accommodation Booking System
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
        System.out.println("What would you like to do with us today?"); //Introduction to Bedam
        System.out.println("\nPress the number correlating to the option.");
        menu(); //Calling the menu static method.
        
    }

    private static void menu() throws IOException {
        Scanner scan = new Scanner(System.in); //So the user's responses can be inputted
        int choice = 0;
        CustomerHashMap.customerHashMap.clear();
        CustomerHashMap.fillHashMap(Reader.readHashMapFile());     
        
       // System.out.println(CustomerHashMap.customerHashMap.size());   Used for testing purposes
        
        while (choice != 7) {
            
            System.out.println("1. About us");
            System.out.println("2. About Bedam Facilities");
            System.out.println("3. Make a booking");
            System.out.println("4. Cancel a booking"); //List of options for the user to choose from 
            System.out.println("5. View add-ons");
            System.out.println("6. View Contact Information");
            System.out.println("7. Exit");

            //check for valid inputs
            boolean validInput = false;
            while (!validInput) { //Validating the user's input is first an integer
                try {
                    choice = scan.nextInt();
                    validInput = true;
                } catch (InputMismatchException e) { //if input isn't valid, try again
                    System.out.println("That was not a recognisable response, please try again."); 
                    scan.nextLine();
                }
            }
            System.out.println();

            //complete
            switch (choice) { //A switch based on the input from the user
                case 1:
                    System.out.println("About Bedam");
                    Reader.about(); //Calls the about method in Reader class 
                    break;

                case 2:
                    System.out.println("About Bedam Facilities");
                    Reader.bedamFacilities(); //Calls bedamFacilities method from the Reader Class
                    break;

                case 3:
                    System.out.println("Make a booking");
                    MakeBooking.newBooking(); //Calls the method newBooking from MakeBooking class
                    break;

                case 4:
                    System.out.println("Cancel booking");
                    CustomerHashMap.cancelBooking(); //Calls the method cancelBooking inside the CustomerHashMap class
                    break;

                case 5:
                    System.out.println("View add-ons");
                    Reader.viewAddOns(); //Calls method viewAddOns from the Reader class
                    break;
                    
                case 6:
                    System.out.println("Loading Contact Information...\n");
                    Reader.contactInformation(); //Calls contactInformation method from Reader class
                    break;
                case 7:
                    // Ends the Program
                    break;

                default: //If an integer that isn't between 1 and 7 is entered
                    System.out.println("Sorry, that isn't an option. Try again.\n");
            }
        }
        System.out.println("Thank you for using the Bedam service. See you later!"); //Exit message once user presses 7.
    }
}
