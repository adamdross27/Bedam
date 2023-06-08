/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Hotel Booking System
 *
 */
package bedam;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Writer {
    // Write invoice method

    public static void writeInvoice(Booking b1, LocalDate start, LocalDate end, int nights) {
        File file = new File("./resources/invoice.txt");
        String toWrite = "";

        toWrite += "------------------------------------------------------------------------\n"; //should be longest length now
        toWrite += "Thank you for booking with Bedam!\n";
        toWrite += "We have your booking details as follows: \n";
//        toWrite += "Your booking is saved under the name: "+ p1.getName() +"\n";
        toWrite += "You are staying for " + nights + " nights.\n";
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        toWrite += "Your stay is from "+start.format(dateFormatter) + " to " + end.format(dateFormatter)+"\n";
        //not sure why but room class doesnt have the correct amount of rooms or bathrooms
        if (b1.getAccommodation().getClass() == Room.class) {
            toWrite += "Bathrooms: 1\n";
            toWrite += "Bedrooms: 1\n";

        } else {
            toWrite += "Bathrooms: " + b1.getAccommodation().getBathrooms() + "\n";
            toWrite += "Bedrooms: " + b1.getAccommodation().getBedrooms() + "\n";
        }
        if (b1.getAccommodation().getClass() == House.class) {
            toWrite += "You have chosen to stay in a house. \n";
            House accomHouse = (House) b1.getAccommodation();
            toWrite += accomHouse.toString();
        }
        if (b1.getAccommodation().getClass() == Apartment.class) {
            toWrite += "You have chosen to stay in an apartment. \n";
            Apartment accomApartment = (Apartment) b1.getAccommodation();
            toWrite += accomApartment.toString();
        }
        if (b1.getAccommodation().getClass() == Room.class) {
            toWrite += "You have chosen to stay in a room. \n";
            Room accomRoom = (Room) b1.getAccommodation();
            toWrite += accomRoom.toString();
        }
<<<<<<< Updated upstream
=======
        
        
        double rentPerNight = 0.0;
        rentPerNight = accom.calculateRentPerNight(nights);
        accom.setRentPerNight(nights);
        rentPerNight = accom.calculateRentPerNight(nights);
        
        
        if(b1.getLocationStr().equalsIgnoreCase("room"))
        {
            rentPerNight = nights * 100;
            if(Room.isHasTowels())
            {
                rentPerNight += 10;
            }
            if(Room.isIsPrivateRoom())
            {
                rentPerNight += 45;
            }
            if(Room.isHasAirConditioning())
            {
                rentPerNight += 20;
            }
            accom.setRentPerNight(rentPerNight);
        }
        
        else if (b1.getLocationStr().equalsIgnoreCase("apartment"))
        {
            rentPerNight = nights * ((b1.getAccommodation().getBedrooms() * 35) + b1.getAccommodation().getBathrooms() * 15);
            if(Apartment.isHasLaundry())
            {
                rentPerNight += 20;
            }
            if(Apartment.isHasParking())
            {
                rentPerNight += 50;
            }
            if(Apartment.isHasBalcony())
            {
                rentPerNight += 30;
            }
            accom.setRentPerNight(rentPerNight);
        }
        else if (b1.getLocationStr().equalsIgnoreCase("house"))
        {
            rentPerNight = nights * ((b1.getAccommodation().getBedrooms() * 45) + b1.getAccommodation().getBathrooms() * 15);
            
            if(House.isHasPool())
            {
                rentPerNight += 50;
            }
            if(House.isHasYard())
            {
                rentPerNight += 15;
            }
            if(House.isHasGarage())
            {
                rentPerNight += 20;
            }
            accom.setRentPerNight(rentPerNight);
        }

>>>>>>> Stashed changes
        toWrite += "------------------------------------------------------------------------\n";
        toWrite += "\nTotal rent to pay: " + b1.getAccommodation().getRentPerNight() * nights +"\n";
        toWrite += "Booking number: " + b1.getBookingNum();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(toWrite);
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }

        //open the file for user to see
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            System.out.println("IO Exception");
        }

    }

    //Special request method (e.g. extra snacks)
    //List of Names of previous guests
    public static void printBookingToFile(int bookingNum, Booking booking)
    {
        try {
            FileWriter fileWriter = new FileWriter("./resources/GuestInformation.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(bookingNum + "," + booking.toHashMapString() + ",");
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) 
        {
        }
    }
    public static void writeNextBookingNum() throws IOException
    {
      int newBookingNum = Reader.readBookingNum() +1;
        FileWriter fileWriter = new FileWriter("./resources/bookingNum.txt", false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(Integer.toString(newBookingNum));
        bufferedWriter.newLine();
        bufferedWriter.close();
        
        
}

    
        
}
