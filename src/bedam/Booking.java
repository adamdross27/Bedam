/**
 * Program Design and Construction 
 * Ben Rogers - 21145117 
 * Adam Ross - 21151208
 * Assignment One - Hotel Booking System
 *
 */
package bedam;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Booking {

    private static int nextBookingNum = 1;
    private Accommodation accommodation;
    private int locationID;
    private int numNightsBooked;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String locationStr;
    private int bookingNum;

    
    public Booking(int locationID, Accommodation accommodation, int numNightsBooked, LocalDate checkInDate, LocalDate checkOutDate, String locationStr, int bookingNum) throws IOException
    {
        this.locationID = locationID;
        this.accommodation = accommodation;
        this.numNightsBooked = numNightsBooked;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.locationStr = locationStr;
        this.bookingNum = bookingNum;
        Writer.writeNextBookingNum();
    }
    
    public Booking(String locationStr1, Accommodation accom, int numNightsBooked1, LocalDate checkInDate1, LocalDate checkOutDate1, int readBookingNum) throws IOException
    {
        this.locationStr = locationStr1;
        this.accommodation = accom;
        this.numNightsBooked = numNightsBooked1;
        this.checkInDate = checkInDate1;
        this.checkOutDate = checkOutDate1;
        this.bookingNum = readBookingNum;
        Writer.writeNextBookingNum();
    }

    public Accommodation getAccommodation() 
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
        output += "Number of Bedrooms: " + this.accommodation.getBedrooms()+"\n";
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
        output += this.accommodation.getBathrooms() +"~";
        output += this.accommodation.getRentPerNight() +"~";
        output += this.accommodation.getRentPerNight() * this.numNightsBooked+"~";
        output += this.locationStr + "~";
        output += this.getBookingNum();
        
        return output;
    }

    public static Booking bookingFromString(String str) throws IOException
    {
        Scanner scan = new Scanner(System.in);
        String line;
        String[] parts = str.split("~");
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
        

        Accommodation accom = new Accommodation(bedrooms, bathrooms, rentPerNight) {
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
      
        Booking b1 = new Booking(location, accom, numNightsBooked, checkInDate, checkOutDate, locationStr, bookingNum);
        return b1;
    }
    
}
