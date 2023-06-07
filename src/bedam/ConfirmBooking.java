/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Accommodation Booking System
 *
 */
package bedam;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConfirmBooking 
{
    public static boolean confirmBooking(Accommodation accommodation, int numBedrooms, int numBathrooms, int numNightsBooked, LocalDate checkInDate, LocalDate checkOutDate) throws IOException
    {
        Scanner scan = new Scanner(System.in); //Allows user input
        
        accommodation.printDetails(); //Prints the details for the user
        
        System.out.println("This is the final stage of the booking process.");
        System.out.println("If you select Y to the below question you will receive your invoice.");
        System.out.println("If you select N to the below question you will return to the main menu and your booking will be cancelled at no cost.");
        System.out.println("Would you like to confirm this booking? (Y/N)"); //Asks the user if they want to confirm this booking.

        char responseChar = 'q';
        String houseResponse = "";
        boolean validInput = false;
        while(!validInput) //Input validator 
        {
            try {

                houseResponse = scan.next();
                responseChar = Character.toLowerCase(houseResponse.charAt(0)); //Converts the response to lowercase so the below line only has to meet one of two conditions rather than 4 (y,n,Y,N)
                if (responseChar == 'y' || responseChar == 'n') 
                {
                    validInput = true; //Input is accepted
                } 
                else 
                {
                    throw new InputMismatchException("Invalid input. Please enter 'Y' or 'N'."); //If the input isn't accepted
                }
            } catch (InputMismatchException e) 
            {
                System.out.println(e.getMessage());
            }
            if(responseChar == 'y' || responseChar == 'Y')
            {
                double rentPerNight = accommodation.calculateRentPerNight(numNightsBooked) / numNightsBooked; //Calculates the rentPerNight 
                accommodation.setBedrooms(numBedrooms);
                accommodation.setBathrooms(numBathrooms); 
                accommodation.setRentPerNight(rentPerNight);
                System.out.println("Your total to pay for your "+ numNightsBooked+" night visit at Bedam is:");
                System.out.println("Rent Per Night: $"+ accommodation.getRentPerNight());
                System.out.println("Number of Nights Booked: "+ numNightsBooked);
                System.out.println("Total: $"+ accommodation.getRentPerNight() + " * " + numNightsBooked); 
                System.out.println("= $"+ accommodation.getRentPerNight() * numNightsBooked); 
                Writer.writeNextBookingNum(); //This calls the writeNextBookingNum method from Writer class.
            }
            else if(responseChar == 'n' || responseChar == 'n') //If the user decides they don't want to confirm their booking.
            {
                System.out.println("Your booking has been cancelled!");
                return false;
            }
        }
        return true; //User wants to confirm their booking
    }
}
