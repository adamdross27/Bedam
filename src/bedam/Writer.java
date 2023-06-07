/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Accommodation Booking System
 *
 */
package bedam;

import static bedam.CustomerHashMap.customerHashMap;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Writer {
    // Write invoice method

    public static void writeInvoice(Booking b1, LocalDate start, LocalDate end, int nights) { //This method will write the invoice onto a txt file and will open the file for the user.
        File file = new File("./resources/invoice.txt");
        String toWrite = "";

        toWrite += "------------------------------------------------------------------------\n"; //For presentation purposes
        toWrite += "Thank you for booking with Bedam!\n";
        toWrite += "We have your booking details as follows: \n";
        toWrite += "You are staying for " + nights + " nights.\n"; //Shows the number of nights they are staying for.
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); //Date formatter is needed to print in that form.
        
        toWrite += "Your stay is from "+start.format(dateFormatter) + " to " + end.format(dateFormatter)+"\n"; //Prints the dates

        if (b1.getAccommodation().getClass() == Room.class) {
            toWrite += "Bathrooms: 1\n";
            toWrite += "Bedrooms: 1\n";

        } else {
            toWrite += "Bathrooms: " + b1.getAccommodation().getBathrooms() + "\n"; //Using get methods to get bedroom and bathroom values
            toWrite += "Bedrooms: " + b1.getAccommodation().getBedrooms() + "\n";
        }
        if (b1.getAccommodation().getClass() == House.class) { //comparing the accommodation class to each of the three subclasses
            toWrite += "You have chosen to stay in a house. \n"; //They have chosen to stay in ________
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
        
        toWrite += "\n------------------------------------------------------------------------\n"; 
        toWrite += "\nTotal rent to pay: " + b1.getAccommodation().getRentPerNight() * nights +"\n"; //Total cost which is rentPerNight * nights
        toWrite += "Booking number: " + b1.getBookingNum(); //Booking number that is used to cancel orders
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(toWrite);
        } catch (IOException ex) {
            System.out.println("IO Exception: " + ex);
        }

        //open the file for user to see
        try {
            Desktop.getDesktop().open(file); //Opens the file onto the users screen.
        } catch (IOException ex) {
            System.out.println("IO Exception");
        }

    }

    public static void printBookingToFile(int bookingNum, Booking booking)
    {
        try {
            FileWriter fileWriter = new FileWriter("./resources/GuestInformation.txt", true); //True means it will not overwrite, it will append
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(bookingNum + "," + booking.toHashMapString() + ",");
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) 
        {
        }
    }
    public static void writeNextBookingNum() throws IOException //Used as our booking number counter which increments every booking.
    {
      int newBookingNum = Reader.readBookingNum() +1;
        FileWriter fileWriter = new FileWriter("./resources/bookingNum.txt", false); //false means it will overwrite the file each time.
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(Integer.toString(newBookingNum)); //Writes the newBookingNum which is readBookingNum + 1 from Reader class. This is how we increment our booking numbers.
        bufferedWriter.newLine();
        bufferedWriter.close();
}
    
    public static void writeFromHashMap() throws IOException
    {
        try {
            FileWriter fileWriter = new FileWriter("./resources/GuestInformation.txt", false); //False means it will overwrite the file each time
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Map.Entry<Integer, Booking> entry : customerHashMap.entrySet()) { //For loop which prints the hashmap onto the txt file so it can be saved when the program closes and reopens. So data isn't lost. 
                bufferedWriter.write(entry.getKey() + "," + entry.getValue().toHashMapString()+","); //Commas are used to split 
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) 
        {
        }   
    }  
}

    
     
