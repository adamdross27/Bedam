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
import java.util.Scanner;

public class Booking {

    private static int nextBookingNum = 1; //This is our booking counter for every booking that is created, it will increase by 1, starting at number 1.
    private Accommodation accommodation;
    private int locationID;
    private int numNightsBooked;
    private LocalDate checkInDate; //Variables used for bookings
    private LocalDate checkOutDate;
    private String locationStr;
    private int bookingNum;

    
    public Booking(int locationID, Accommodation accommodation, int numNightsBooked, LocalDate checkInDate, LocalDate checkOutDate, String locationStr, int bookingNum) throws IOException
    {
        this.locationID = locationID;
        this.accommodation = accommodation;
        this.numNightsBooked = numNightsBooked; //Constructor that instantiates all of these variables
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.locationStr = locationStr;
        this.bookingNum = bookingNum;
  
    }
    
    public Booking(int locationID, Accommodation accommodation, int numNightsBooked, LocalDate checkInDate, LocalDate checkOutDate, String locationStr, int bookingNum, double rentPerNight) throws IOException
    {
        this.locationID = locationID;
        this.accommodation = accommodation;
        this.numNightsBooked = numNightsBooked;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate; //Second constructor which has rentPerNight
        this.locationStr = locationStr;
        this.bookingNum = bookingNum;        
        this.accommodation.setRentPerNight(rentPerNight);
    }

    public Accommodation getAccommodation() //Get and Set methods below
    {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) 
    {
        this.accommodation = accommodation;
    }
    
    public int getNumNightsBooked() 
    {
        return numNightsBooked;
    }

    public void setNumNightsBooked(int numNightsBooked) 
    {
        this.numNightsBooked = numNightsBooked;
    }

    public LocalDate getCheckInDate() 
    {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) 
    {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() 
    {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) 
    {
        this.checkOutDate = checkOutDate;
    }

    public int getLocationID() 
    {
        return locationID;
    }

    public void setLocationID(int locationID) 
    {
        this.locationID = locationID;
    }

    public String getLocationStr() 
    {
        return locationStr;
    }

    public void setLocationStr(String locationStr) 
    {
        this.locationStr = locationStr;
    }
    
    public int getBookingNum() 
    {
        return bookingNum;
    }

    public void setBookingNum(int bookingNum) 
    {
        this.bookingNum = bookingNum;
    }

    public static void resetBookingNum()
    {
        nextBookingNum = 1;
    }
    
    
    @Override
    public String toString()
    {
        String output = "\n";
        output += "Accommodation Type: " + this.getLocationStr()+"\n";
        output += "Number of Nights Booked: " + this.numNightsBooked+"\n";
        output += "Check In Date: " + this.checkInDate+"\n";
        output += "Check Out Date: " + this.checkOutDate+"\n";
        output += "Number of Bedrooms: " + this.accommodation.getBedrooms()+"\n";  //toString method which prints the details about the booking
        output += "Number of Bathrooms: " + this.accommodation.getBathrooms() +"\n";
        output += "Price Per Night: $" + this.accommodation.getRentPerNight() +"\n";
        output += "Total Cost: $" + this.accommodation.getRentPerNight() * this.numNightsBooked+"\n";
        output += "Booking Number: " + this.getBookingNum() + "\n";
        
        
        return output;
    }
    
    public String toHashMapString()
    {
        String output = "\n";
        output += this.locationID+"~";
        output += this.numNightsBooked+"~";
        output += this.checkInDate+"~";
        output += this.checkOutDate+"~";
        output += this.accommodation.getBedrooms()+"~";
        output += this.accommodation.getBathrooms() +"~"; //This is the toString that is used to print onto the HashMap so the bookings can be saved on a txt file and be accessed even when the program is closed
        output += this.accommodation.getRentPerNight() +"~";
        output += this.accommodation.getRentPerNight() * this.numNightsBooked+"~";
        output += this.locationStr + "~"; //~ are used as dividers between each variable
        output += this.getBookingNum();  
        
        return output;
    }

    public static Booking bookingFromString(String str) throws IOException
    {
        Scanner scan = new Scanner(System.in);
        String line;  
        String[] parts = str.split("~"); //split the line into its respective parts, each part representing an aspect of the a booking
        //following lines are just creating variables and casting them to the correct variable type so they can be used to create a booking
        int location = Integer.parseInt(parts[0]);
        int numNightsBooked = Integer.parseInt(parts[1]);
        LocalDate checkInDate = LocalDate.parse(parts[2]);
        LocalDate checkOutDate = LocalDate.parse(parts[3]);
        int bedrooms = Integer.parseInt(parts[4]);
        int bathrooms = Integer.parseInt(parts[5]);
        double rentPerNight = Double.parseDouble(parts[6]);
        double totalCost = Double.parseDouble(parts[7]);
        String locationStr = parts[8];
        int bookingNum = Integer.parseInt(parts[9]);
        
        //made an accommodation object from parts in the string
        Accommodation accom = new Accommodation(bedrooms, bathrooms, rentPerNight) {
        //didnt utilise these methods but needed to include because of the abstract class
            @Override
            public double calculateRentPerNight(int numOfNights) {
                //adding abstract methods
                return 0;
            }

            @Override
            public void printDetails() {
             //adding abstract methods
            }
        };
      
        //finally create a booking using the data gathered from the string
       return new Booking(location, accom, numNightsBooked, checkInDate, checkOutDate, locationStr, bookingNum, rentPerNight);
    }
      
    
}
