/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bedam;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author adamross
 */
public class ConfirmBooking 
{
    public static boolean confirmBooking(Accommodation accommodation, int numBedrooms, int numBathrooms, int numNightsBooked, LocalDate checkInDate, LocalDate checkOutDate)
    {
        Scanner scan = new Scanner(System.in);
        
        accommodation.printDetails();
        
        System.out.println("This is the final stage of the booking process.");
        System.out.println("If you select Y to the below question you will receive your invoice.");
        System.out.println("If you select N to the below question you will return to the main menu and your booking will be cancelled at no cost.");
        System.out.println("Would you like to confirm this booking? (Y/N)");

        char responseChar = 'q';
        String houseResponse = "";
        boolean validInput = false;
        while(!validInput)
        {
            try {

                houseResponse = scan.next();
                responseChar = Character.toLowerCase(houseResponse.charAt(0));
                if (responseChar == 'y' || responseChar == 'n') 
                {
                    validInput = true;
                } 
                else 
                {
                    throw new InputMismatchException("Invalid input. Please enter 'Y' or 'N'.");
                }
            } catch (InputMismatchException e) 
            {
                System.out.println(e.getMessage());
            }
            if(responseChar == 'y' || responseChar == 'Y')
            {
                double rentPerNight = accommodation.calculateRentPerNight(numNightsBooked) / numNightsBooked;
                accommodation.setBedrooms(numBedrooms);
                accommodation.setBathrooms(numBathrooms);
                accommodation.setRentPerNight(rentPerNight);
                System.out.println("Your total to pay for your "+ numNightsBooked+" night visit at Bedam is:");
                System.out.println("Rent Per Night: $"+ accommodation.getRentPerNight());
                System.out.println("Number of Nights Booked: "+ numNightsBooked);
                System.out.println("Total: $"+ accommodation.getRentPerNight() + " * " + numNightsBooked);
                System.out.println("= $"+ accommodation.getRentPerNight() * numNightsBooked);
                
            }
            else if(responseChar == 'n' || responseChar == 'n')
            {
                System.out.println("Your booking has been cancelled!");
                return false;
            }
        }
        return true;
    }
}
