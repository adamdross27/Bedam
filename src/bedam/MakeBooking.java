/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Accommodation Booking System
 *
 */
package bedam;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class MakeBooking {

    
    public static Booking newBooking() throws IOException 
    { //If the user presses 3 in the menu, it will begin this method.
        Scanner scan = new Scanner(System.in);
        
        int locationID = 0;
        Accommodation accommodation = null;
        int numBedrooms = 0;
        int numBathrooms = 0;
        double rentPerNight = 0.0;
        int numNightsBooked = 0;       //Declaring variables that will be later used to instantiate the Accommodation and the Booking
        LocalDate checkInDate = null;
        LocalDate checkOutDate = null;
        String locationStr = "";
    

        ArrayList<Booking> bookings = new ArrayList<>();

        CustomerHashMap customerHashMap = new CustomerHashMap();
        
        Booking b1 = new Booking(locationID, accommodation, numNightsBooked, checkInDate, checkOutDate, locationStr, Reader.readBookingNum()); //Declaring the booking and instantiating with basic values which will all be replaced.
        
        boolean validInput = false; //Used to validate input below

        //location
        boolean nextStep = false;
        OUTER: //Outer while loop 
        while (nextStep != true) 
        {
            System.out.println("To make a booking, please type 1, 2, 3, or 4 to match the corresponding accommodation type.");
            System.out.println("1. House");
            System.out.println("2. Apartment"); //If the user presses 3, they will be prompted with these options.
            System.out.println("3. Room");
            System.out.println("4. Go back to menu");
            validInput = false;
            while (!validInput) {
                try {
                    locationID = scan.nextInt();
                    if (locationID >= 1 && locationID <= 4) { //Will repeatedly ask the user to enter an Int between 1 and 4.
                        validInput = true;
                    } else {
                        System.out.println("Please input an integer between 1 and 4.");//Error message if it isn't between 1 and 4.
                    }
                } catch (InputMismatchException e) {
                    System.out.println("That was not a recognisable response, please try again."); //If a non-intger is inputted
                    scan.nextLine();
                }
            }
            System.out.println();
            switch (locationID) { //Switch depending on the accommodation type they select.
                case 1:
                    System.out.println("You have chosen to rent a House.\n");
                    b1.setLocationStr("House"); //Sets the locationStr of the Booking 
                    break;

                case 2:
                    System.out.println("You have chosen to rent an Apartment.\n");
                    b1.setLocationStr("Apartment");//Sets the locationStr of the Booking 
                    break;

                case 3:
                    System.out.println("You have chosen to rent a Room.\n");
                    b1.setLocationStr("Room");//Sets the locationStr of the Booking 
                    
                    break;

                case 4:
                    System.out.println("Returning to main menu...\n");
                    break OUTER; //This breaks the outer while loop which takes them back to the main menu.

                default:
                    System.out.println("Sorry, that isn't an option. Try again.\n"); //Default for the switch.
            }

            validInput = false;
            System.out.println("Are you happy with your choice? (Y/N, press x to cancel)"); //Allows the user to confirm their option
            char confirm = 'n';
            while (!validInput) { //Input validator 
                confirm = scan.next().charAt(0);
                confirm = Character.toLowerCase(confirm);
                if (confirm == 'y' || confirm == 'n' || confirm == 'x') { //This checks for both upper and lowercase types because of line above
                    validInput = true;
                } else {
                    System.out.println("Please choose 'Y' for yes, 'N' for no, or 'x' to exit"); //If an appropriate answer isn't supplied
                }
            }
            switch (Character.toLowerCase(confirm)) {
                case 'y': //If Yes is the answer
                    System.out.println("\nConfirmed!\nPlease answer the following questions about your accommodation.");
                    System.out.println("You will be asked at the end to confirm or cancel your booking.\n");
                    b1.setLocationID(locationID);
                    nextStep = true; //Allows the user to proceed below
                    break;
                case 'n':
                    System.out.println("\nNot confirmed, returning to options...\n"); //Takes them back to choose from accommodation types
                    break;
                case 'x':
                    System.out.println("\nCancelled, returning to main menu\n");
                    nextStep = false;
                    break OUTER; //Takes them back to the main menu.
                default:
                    break;
            }
        }

        if (nextStep) 
        {
            String houseResponse = "";

            validInput = false;
            System.out.println("How many nights would you like to stay in your accommodation? Please note the minimum stay is 1 night maximum stay is 30 nights.");
            while (!validInput) { //Int input validator that needs to be between 1 and 30 days.
                try {
                    numNightsBooked = scan.nextInt();
                    if (numNightsBooked >= 1 && numNightsBooked <= 30) {
                        validInput = true; //Will now exit the while loop.
                    } else {
                        throw new InputMismatchException("Invalid input. Please enter an integer between 1 and 30.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer between 1 and 30."); //Error message returned
                    scan.nextLine();
                }
            }
            scan.nextLine(); //Used to scan the "return" key so the next answer isn't inputted
            validInput = false;
            
            LocalDate today = LocalDate.now(); //Getting today's date
            LocalDate minCheckInDate = today.plusDays(1); //Bedam cannot process a booking with less than 24 hours notice
            LocalDate maxCheckInDate = today.plusDays(365); //Bedam cannot prepare for a booking that is more than 365 days in advance.
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); //Changes the format to DD-MM-YYYY rather than the default settings.
            
            while(checkInDate == null || checkInDate.isBefore(minCheckInDate) || checkInDate.isAfter(maxCheckInDate))
            {

                    System.out.println("Please enter the date you would like to check in for (format: DD-MM-YYYY): ");
                    String checkInDateString = scan.next(); //Scans for input
                try {
                        checkInDate = LocalDate.parse(checkInDateString, formatter); //Confirms it into the format that we coded above.
                        checkOutDate = checkInDate.plusDays(numNightsBooked); //Calculates the check out date based on checkInDate and numNightsBooked.
                    if(checkInDate.isBefore(minCheckInDate) || checkInDate.isAfter(maxCheckInDate)) //Making sure that the booking date is within the parameters
                    {
                        System.out.println("Check in date must be at least one day in advance of current date and be less than 365 days in the future (" + minCheckInDate.format(formatter) +" - " + maxCheckInDate.format(formatter) +").");
                    }
                    else
                    {
                        System.out.println("Your current check in date is: " + checkInDate.format(formatter));
                        System.out.println("Your current check out date is: " +checkOutDate.format(formatter)); //Prints both check in and check out dates so the user can see.
                        System.out.println("Are you happy to book this date? (Y/N) You may choose to cancel your booking at the end.");
                        validInput = false;
                        while(!validInput) //Input validator for their confirmation of their check in/out date.
                        {
                            String confirmation = scan.next().toLowerCase(); 
                            if(confirmation.equals("y"))
                            {
                                validInput = true; //Input is good
                                scan.nextLine(); //Takes in the return key
                            }
                            else if (confirmation.equals("n"))
                            {
                                checkInDate = null; 
                                checkOutDate = null; //Resets the check in/out dates so the while loop catches it on line 151.
                                validInput = true; //Valid input is true, so it can exit the inner while loop however the check in/out dates do not fulfil the outer while loop conditions
                            }
                            else
                            {
                                System.out.println("Invalid input. Please enter 'Y' to confirm or 'N' to change check in date.");  //If a non y/n/Y/N answer is provided.   
                            }
                        }
                    }
                } catch (Exception e)
                {
                    System.out.println("Invalid date format. Please try again!"); //If date format isn't exactly right. E.g. if you do 1-1-2024. This is ineligible. Needs to be 01-01-2024.
                }

            }
            
            System.out.println("Your check in date is: " + checkInDate.format(formatter)); //Prints their confirmed check in/out dates.
            System.out.println("Your current check out date is: " +checkOutDate.format(formatter));
            
            boolean confirm = false;
            validInput = false;
            switch (locationID) { 
                
                case 1:
                    House h1 = new House(numBedrooms, numBathrooms, rentPerNight); // Instantiating the accommodation. These values will be replaced later by user input
                    System.out.println("How many bedrooms would you like your rented house to have? Please choose from 1 to 5 bedrooms.");
                    while (!validInput) { //Input validator
                        try {

                            numBedrooms = scan.nextInt(); //Scans for int
                            if (numBedrooms >= 1 && numBedrooms <= 5) { //Needs to be within 1 and 5
                                validInput = true;
                                scan.nextLine(); //So the next question doesn't use return key as the input for the answer.
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter an integer between 1 and 5."); //Error message
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter an integer between 1 and 5!!");
                            scan.nextLine();
                        }
                    }
                    h1.setBedrooms(numBedrooms); //Sets the bedrooms to user inputted  value

                    validInput = false;
                    System.out.println("How many bathrooms would you like your rented house to have? Please choose from 1 to 5 bathrooms."); //Asks for how many bathrooms
                    while (!validInput) { //Input validator
                        try {
                            numBathrooms = scan.nextInt();
                            if (numBathrooms >= 1 && numBathrooms <= 5) { //Must be between 1 and 5
                                validInput = true;
                                scan.nextLine(); //So the next question doesn't use return key as the input for the answer.
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter an integer between 1 and 5."); //Error message
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter an integer between 1 and 5!!");
                            scan.nextLine();
                        }
                    }
                    h1.setBathrooms(numBathrooms); //Sets the numBathrooms to user inputted value

                    validInput = false;
                    char responseChar = 'n';
                    System.out.println("Would you like your rented House to have a pool? (Y/N)"); //First boolean condition unique to House
                    while (!validInput) { //Input validator
                        try {
                            houseResponse = scan.next();
                            responseChar = Character.toLowerCase(houseResponse.charAt(0));
                            if (responseChar == 'y' || responseChar == 'n') {
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'."); //Error message
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (responseChar == 'y' || responseChar == 'Y') { //If yes sets hasPool to true
                        h1.setHasPool(true);
                    } else {
                        h1.setHasPool(false); //Sets to false
                    }

                    System.out.println("Would you like your rented House to have a Backyard? (Y/N)");
                    responseChar = 'n';
                    houseResponse = "";
                    validInput = false;
                    while (!validInput) { //Input validator
                        try {
                            houseResponse = scan.next();
                            responseChar = Character.toLowerCase(houseResponse.charAt(0));
                            if (responseChar == 'y' || responseChar == 'n') { //Checks both upper and lowercase because of line above
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'."); //Error message
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    if (responseChar == 'y' || responseChar == 'Y') {
                        h1.setHasYard(true);
                    } else {
                        h1.setHasYard(false);
                    }

                    System.out.println("Would you like your rented House to have a Garage? (Y/N)"); //Next unique variable of House
                    responseChar = 'n';
                    houseResponse = "";
                    validInput = false;
                    while (!validInput) { //Input validator
                        try {
                            houseResponse = scan.next();
                            responseChar = Character.toLowerCase(houseResponse.charAt(0));
                            if (responseChar == 'y' || responseChar == 'n') {
                                validInput = true; //Gets out of while loop now because answer is appropriate.
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'."); //Error message if answer isn't appropriate
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (responseChar == 'y' || responseChar == 'Y') {
                        h1.setHasGarage(true); //Sets to true or false based on Y/N
                    } else {
                        h1.setHasGarage(false);
                    }

                    Accommodation house = h1; //Instantiates new accommodation called house which takes all the values of h1. house is then used in the below code
                    confirm = ConfirmBooking.confirmBooking(house, numBedrooms, numBathrooms, numNightsBooked, checkInDate, checkOutDate); //Calls the confirmBooking static method from ConfirmBooking class with these arguments.
                    if(confirm) //If the confirmBooking method returns TRUE
                    {   
                        b1.setAccommodation(house);
                        b1.setCheckInDate(checkInDate);
                        b1.setCheckOutDate(checkOutDate); //Sets these variables to their appropriate values
                        b1.setLocationID(locationID);
                        b1.setLocationStr("House");
                        b1.setNumNightsBooked(numNightsBooked);
                        Writer.writeInvoice(b1, checkInDate, checkOutDate, numNightsBooked); //Calls writeInvoice method from Writer class which writes the user's invoice onto a txt.
                        customerHashMap.putBooking(b1); //calls putBooking method of CustomerHashMap class which will write it into the txt and save to the HashMap.
                        bookings.add(b1); //Adds to the arraylist.
                    }
                    break;

                case 2:  //APARTMENT
                    String apartmentResponse = "";
                    responseChar = 'n';
                    Apartment a1 = new Apartment(numBedrooms, numBathrooms, rentPerNight); //Instantiates apartment with 0 values, but will be replaced later on by user input.
                    System.out.println("How many bedrooms would you like your apartment to have? Please choose from 1 to 3 bedrooms.");
                    numBedrooms = 0;
                    while (!validInput) { //Input validator
                        try {
                            numBedrooms = scan.nextInt();
                            if (numBedrooms >= 1 && numBedrooms <= 3) { //Needs to be between 1 and 3 as apartment is smaller than house
                                validInput = true;
                                scan.nextLine(); //So the next question doesn't use return key as the input for the answer.
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter an integer between 1 and 3."); //Error message
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter an integer between 1 and 3!!"); //If non int input is recorded
                            scan.nextLine(); //Scans the return key
                        }
                    }
                    a1.setBedrooms(numBedrooms); //Sets numBedrooms to user choice between 1 and 3.
                    
                    apartmentResponse = "";
                    responseChar = 'n';
                    validInput = false;
                    System.out.println("How many bathrooms would you like your apartment to have? Please choose from 1 to 2 bathrooms.");
                    numBathrooms = 0;
                    while (!validInput) { //Input validator
                        try {
                            numBathrooms = scan.nextInt();
                            if (numBathrooms >= 1 && numBathrooms <= 2) { //Needs to be between 1 and 2.
                                validInput = true;
                                scan.nextLine(); //So the next question doesn't use return key as the input for the answer.
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter an integer between 1 and 2."); //If int isn't between 1 and 2.
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter an integer between 1 and 2!!"); //If non int answer is provided
                            scan.nextLine(); //Eats up the return key
                        }
                    }
                    a1.setBathrooms(numBathrooms); //Sets numBathrooms to user input.
 
                    validInput = false;
                    char needLaundry = 'n';
                    System.out.println("Would you like your apartment to include laundry access? (Y/N)"); //First unique variable of Apartment.
                    while (!validInput) { //Input validator
                        try {
                            apartmentResponse = scan.next();
                            needLaundry = Character.toLowerCase(apartmentResponse.charAt(0));
                            if (needLaundry == 'y' || needLaundry == 'n') {
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'."); //If input isn't y/n/Y/N.
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (needLaundry == 'y' || needLaundry == 'Y') { //Sets to TRUE or FALSE depending on user answer.
                        a1.setHasLaundry(true);
                    } else {
                        a1.setHasLaundry(false);
                    }

                    System.out.println("Would you like your apartment to have parking? (Y/N)"); //Next unique apartment variable.
                    char needParking = 'n';
                    apartmentResponse = "";
                    validInput = false;
                    while (!validInput) { //Input validator
                        try {
                            apartmentResponse = scan.next();
                            needParking = Character.toLowerCase(apartmentResponse.charAt(0));
                            if (needParking == 'y' || needParking == 'n') { //If y/n/Y/N then input is accepted.
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'."); //Error message
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    if (needParking == 'y' || needParking == 'Y') { //Sets to true or false based on user decision
                        a1.setHasParking(true);
                    } else {
                        a1.setHasParking(false);
                    }

                    System.out.println("Would you like your apartment to have a balcony? (Y/N)"); //Final unique variable for apartment.
                    char needBalcony = 'n';
                    validInput = false; //This is also set to false before any question because in the previous it will be set to true once user inputs valid answer. This is so the loop isn't actually skipped on accident.
                    while (!validInput) { //Input validator
                        try {
                            apartmentResponse = scan.next();
                            needBalcony = Character.toLowerCase(apartmentResponse.charAt(0));
                            if (needBalcony == 'y' || needBalcony == 'n') { //Need to be y or n (upper/lowercase accepted)
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'."); //Error message
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (needBalcony == 'y' || needBalcony == 'Y') { //Sets to true or false based on user input
                        a1.setHasBalcony(true);
                    } else {
                        a1.setHasBalcony(false);
                    }

                    Accommodation apartment = a1; // instantiates apartment to have all the qualities of A1.
                    confirm = ConfirmBooking.confirmBooking(apartment, numBedrooms, numBathrooms, numNightsBooked, checkInDate, checkOutDate); //Calls confirmBooking method and will return TRUE/FALSE.
                    if(confirm) //If confirm is equal to true. The booking will be confirmed. 
                    {   
                        
                        b1.setAccommodation(apartment);
                        b1.setCheckInDate(checkInDate);
                        b1.setCheckOutDate(checkOutDate); //Sets variables to appropriate values
                        b1.setLocationID(locationID);
                        b1.setLocationStr("Apartment");
                        b1.setNumNightsBooked(numNightsBooked); 
                        Writer.writeInvoice(b1, checkInDate, checkOutDate, numNightsBooked); //Writes the invoice via the writeInvoice method in Writer Class
                        customerHashMap.putBooking(b1); //Calls putBooking method in CustomerHashMap class.
                        bookings.add(b1); //Adds the booking to the arrayList
                    }
                    break;

                case 3: //ROOM
                    String roomResponse = "";
                    numBedrooms = 1; //Sets them to 1 as room will only come with 1 bedroom and 1 bathroom. User has no choice on this as a room is very small.
                    numBathrooms = 1;
                    System.out.println("Please Note: A room will always come with 1 bedroom and 1 bathroom.\n");
                    Room r1 = new Room(numBedrooms, numBathrooms, rentPerNight); //rentPerNight will be calculated later and replaced with correct value.

                    validInput = false;
                    char needTowels = 'n';
                    System.out.println("Would you like your room to come with warm towels? (Y/N)"); //First unique room variable
                    while (!validInput) { //Input validator 
                        try {
                            roomResponse = scan.next();
                            needTowels = Character.toLowerCase(roomResponse.charAt(0));
                            if (needTowels == 'y' || needTowels == 'n') {
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'."); //Error message
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (needTowels == 'y' || needTowels == 'Y') { //Sets to true/false based on user response
                        r1.setHasTowels(true);
                    } else {
                        r1.setHasTowels(false);
                    }

                    System.out.println("Would you like your room to be private? (Y/N)");
                    char needPrivate = 'n'; //Used to convert roomResponse to lowercase so only y/n needs to be checked not y/n/Y/N
                    roomResponse = ""; 
                    validInput = false;
                    while (!validInput) { //Input validator 
                        try {
                            roomResponse = scan.next();
                            needPrivate = Character.toLowerCase(roomResponse.charAt(0));
                            if (needPrivate == 'y' || needPrivate == 'n') {
                                validInput = true; //Input is valid
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'."); //Error message
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    if (needPrivate == 'y' || needPrivate == 'Y') { //Sets to true/false depending on user input
                        r1.setIsPrivateRoom(true);
                    } else {
                        r1.setIsPrivateRoom(false);
                    }

                    System.out.println("Would you like your room to have air conditioning? (Y/N)"); //Final house unique variable
                    char needAC = 'n';
                    validInput = false; //Resets to false so while loop is called
                    while (!validInput) { //input validator
                        try {
                            roomResponse = scan.next();
                            needAC = Character.toLowerCase(roomResponse.charAt(0));
                            if (needAC == 'y' || needAC == 'n') {
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'."); //Error message for bad input
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (needAC == 'y' || needAC == 'Y') { //Sets to true/false based on user input.
                        r1.setHasAirConditioning(true);
                    } else {
                        r1.setHasAirConditioning(false);
                    }
                    r1.setBathrooms(1);
                    r1.setBedrooms(1); //Calling set method to ensure they are set to 1 as this is a room. User has no choice
                    Accommodation room = r1; // Makes room variable which takes all the variables from r1.
                    confirm = ConfirmBooking.confirmBooking(room, numBedrooms, numBathrooms, numNightsBooked, checkInDate, checkOutDate); //Returns true or false based on user decision to confirm booking
                    if(confirm)
                    {
                        b1.setAccommodation(room);
                        b1.setCheckInDate(checkInDate);
                        b1.setCheckOutDate(checkOutDate); //Sets variables to their appropriate values
                        b1.setLocationID(locationID);
                        b1.setLocationStr("Room");
                        b1.setNumNightsBooked(numNightsBooked);
                        Writer.writeInvoice(b1, checkInDate, checkOutDate, numNightsBooked); //Writes invoice to txt file via writeInvoice method in Writer class
                        customerHashMap.putBooking(b1); //Calls putBooking method in CustomerHashMap class
                        bookings.add(b1); //Adds booking to the arraylist.
                    }                  
                    break;
            }

        }
        nextStep = false;
        
        return b1; //returns the booking
    }
}