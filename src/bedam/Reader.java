/**
 * Program Design and Construction 
 * Ben Rogers - 21145117 
 * Adam Ross - 21151208
 * Assignment One - Hotel Booking System
 *
 */
package bedam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Reader 
{
  public static String about() {
           String output = "";
        try {
            FileReader about = new FileReader("./resources/aboutBedam.txt");
            BufferedReader br = new BufferedReader(about);
            String line = null;
            while ((line = br.readLine()) != null) {
                output+=line+"\n";
            }
            System.out.println();            
            br.close();
        } catch (IOException e) {
            System.err.println("Can't read from file...");
        }
        return output;
    }
        
        
        public static void contactInformation() 
        {
            try {
                FileReader contactInfo = new FileReader("./resources/contactInformation.txt");
                BufferedReader br = new BufferedReader(contactInfo);
                String line = null;
                while ((line = br.readLine()) != null)
                {
                    System.out.println(line);
                }
                System.out.println();
                br.close();
            } catch (IOException e) 
            {
                System.err.println("Can't read from file...");
            }
        }
        
        // Room Information method
        public static void bedamFacilities()
        {
            try {
                FileReader bedamFacilities = new FileReader("./resources/bedamFacilities.txt");
                BufferedReader br = new BufferedReader(bedamFacilities);
                String line = "";
                while((line = br.readLine()) != null)
                {
                    System.out.println(line);
                }
                System.out.println();
                br.close();
            } catch (IOException e)
            {
                System.out.println("Can't read from file...");
            }
        }

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
    
    public static String readHashMapFile()
    {
        String str = "", line = "";
        try {
            File file = new File("./resources/GuestInformation.txt");
            FileReader fr = new FileReader(file);
            Scanner scan = new Scanner(file);
            BufferedReader br = new BufferedReader(fr);
            
            while((line = br.readLine()) != null)
            {
                str += line + "\n";
            }
        } catch (IOException e) {
            System.err.println("file not found");
        }
        return str;
    }

        
        // Read Invoice Method
}
