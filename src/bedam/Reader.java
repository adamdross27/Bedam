/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Accommodation Booking System
 *
 */
package bedam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Reader 
{
    public static void about() 
    { //This method will read the aboutBedam.txt file and print it to the user via CLI
        try {
            FileReader about = new FileReader("./resources/aboutBedam.txt");
            BufferedReader br = new BufferedReader(about);
            String line = null;
            while ((line = br.readLine()) != null) { //Loops through all of the lines on the txt until null is reached, i.e. it has reached the end of the file
                System.out.println(line); //Prints each line to the console
            }
            System.out.println();
            br.close(); //Closing the buffered reader
        } catch (IOException e) {
            System.err.println("Can't read from file...");
        }
    }

    public static void contactInformation() 
    { //This method will open the contactInformation.txt file which has all the contact details for Bedam Inc.
    try {
        FileReader contactInfo = new FileReader("./resources/contactInformation.txt"); //Finds the file
        BufferedReader br = new BufferedReader(contactInfo); 
        String line = null;
        while ((line = br.readLine()) != null) { //Loops through all the lines in the txt file and prints each to the console
            System.out.println(line);
        }
        System.out.println();
        br.close(); //Closes the buffered reader
        } catch (IOException e) {
        System.err.println("Can't read from file...");
        }
    }

    // Room Information method
    public static void bedamFacilities() { //Describes the facilities that Bedam offers and is printed to the user.
        try {
            FileReader bedamFacilities = new FileReader("./resources/bedamFacilities.txt"); //Finds the file
            BufferedReader br = new BufferedReader(bedamFacilities);
            String line = "";
            while ((line = br.readLine()) != null) { //Loops through all the lines in the txt file and prints to the user.
                System.out.println(line);
            }
            System.out.println();
            br.close();
        } catch (IOException e) {
            System.out.println("Can't read from file...");
        }
    }

    //this just reads the bookingNum file and returns the value contained within
    public static int readBookingNum() {
        int num = 0;
        String numString = "";
        try {
            File file = new File("./resources/bookingNum.txt");
            Scanner scan = new Scanner(file);
            numString = scan.nextLine();
            num = Integer.parseInt(numString);
        } catch (IOException e) {
            System.err.println("file not found");
        }
        return num;
    }

    public static String readHashMapFile() {
        String str = "", line = "";
        try {
            File file = new File("./resources/GuestInformation.txt");
            FileReader fr = new FileReader(file);
            Scanner scan = new Scanner(file);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                str += line + "\n";
            }
        } catch (IOException e) {
            System.err.println("file not found");
        }
        return str;
    }

    public static void viewAddOns() { //This will display the add-ons that are available for each accommodation type.
        try {
            FileReader addOns = new FileReader("./resources/addOns.txt");  //Finds the txt file in the resources folder
            BufferedReader br = new BufferedReader(addOns);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line); //Prints each line from the txt file to the user.
            }
            System.out.println();
            br.close();
        } catch (IOException e) {
            System.err.println("Can't read from file...");
        }
    }
}
