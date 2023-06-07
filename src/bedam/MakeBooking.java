/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Hotel Booking System
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

    
    public static Booking newBooking() throws IOException {
        Scanner scan = new Scanner(System.in);
        
        int locationID = 0;
        Accommodation accommodation = null;
        int numBedrooms = 0;
        int numBathrooms = 0;
        int numNightsBooked = 0;
        LocalDate checkInDate = null;
        LocalDate checkOutDate = null;
        String locationStr = "";
    

        ArrayList<Booking> bookings = new ArrayList<>();

        CustomerHashMap customerHashMap = new CustomerHashMap();
        
        Booking b1 = new Booking(locationID, accommodation, numNightsBooked, checkInDate, checkOutDate, locationStr, Reader.readBookingNum());
        
        boolean validInput = false;
        Person p1;

        //location
        boolean nextStep = false;
        OUTER:
        while (nextStep != true) {
            System.out.println("To make a booking, please type 1, 2, 3, or 4 to match the corresponding accommodation type.");
            System.out.println("1. House");
            System.out.println("2. Apartment");
            System.out.println("3. Room");
            System.out.println("4. Go back to menu");
            validInput = false;
            while (!validInput) {
                try {
                    locationID = scan.nextInt();
                    if (locationID >= 1 && locationID <= 4) {
                        validInput = true;
                    } else {
                        System.out.println("Please input an integer between 1 and 4.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("That was not a recognisable response, please try again.");
                    scan.nextLine();
                }

            }
            System.out.println();
            switch (locationID) {
                case 1:
                    System.out.println("You have chosen to rent a House.\n");
                    b1.setLocationStr("House");
                    break;

                case 2:
                    System.out.println("You have chosen to rent an Apartment.\n");
                    b1.setLocationStr("Apartment");
                    break;

                case 3:
                    System.out.println("You have chosen to rent a Room.\n");
                    b1.setLocationStr("Room");
                    
                    break;

                case 4:
                    System.out.println("Returning to main menu...\n");
                    break OUTER;

                default:
                    System.out.println("Sorry, that isn't an option. Try again.\n");
            }

            validInput = false;
            System.out.println("Are you happy with your choice? (Y/N, press x to cancel)");
            char confirm = 'n';
            while (!validInput) {
                confirm = scan.next().charAt(0);
                confirm = Character.toLowerCase(confirm);
                if (confirm == 'y' || confirm == 'n' || confirm == 'x') {
                    validInput = true;
                } else {
                    System.out.println("Please choose 'Y' for yes, 'N' for no, or 'x' to exit");
                }
            }
            switch (Character.toLowerCase(confirm)) {
                case 'y':
                    System.out.println("\nConfirmed!\nPlease answer the following questions about your accommodation.");
                    System.out.println("You will be asked at the end to confirm or cancel your booking.\n");
                    b1.setLocationID(locationID);
                    nextStep = true;
                    break;
                case 'n':
                    System.out.println("\nNot confirmed, returning to options...\n");
                    break;
                case 'x':
                    System.out.println("\nCancelled, returning to main menu\n");
                    nextStep = false;
                    break OUTER;
                default:
                    break;
            }
        }

        if (nextStep) 
        {
            String houseResponse = "";

            validInput = false;
            System.out.println("How many nights would you like to stay in your accommodation? Please note the minimum stay is 1 night maximum stay is 30 nights.");
            while (!validInput) {
                try {
                    numNightsBooked = scan.nextInt();
                    if (numNightsBooked >= 1 && numNightsBooked <= 30) {
                        validInput = true;
                    } else {
                        throw new InputMismatchException("Invalid input. Please enter an integer between 1 and 30.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer between 1 and 30.");
                    scan.nextLine();
                }
            }
            scan.nextLine();
            validInput = false;
            
            LocalDate today = LocalDate.now();
            LocalDate minCheckInDate = today.plusDays(1); //Bedam cannot process a booking with less than 24 hours notice
            LocalDate maxCheckInDate = today.plusDays(365);
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            
            while(checkInDate == null || checkInDate.isBefore(minCheckInDate) || checkInDate.isAfter(maxCheckInDate))
            {

                    System.out.println("Please enter the date you would like to check in for (format: DD-MM-YYYY): ");
                    String checkInDateString = scan.next();
                try {
                        checkInDate = LocalDate.parse(checkInDateString, formatter);
                        checkOutDate = checkInDate.plusDays(numNightsBooked);
                    if(checkInDate.isBefore(minCheckInDate) || checkInDate.isAfter(maxCheckInDate))
                    {
                        System.out.println("Check in date must be at least one day in advance of current date and be less than 365 days in the future (" + minCheckInDate.format(formatter) +" - " + maxCheckInDate.format(formatter) +").");
                    }
                    else
                    {
                        System.out.println("Your current check in date is: " + checkInDate.format(formatter));
                        System.out.println("Your current check out date is: " +checkOutDate.format(formatter));
                        System.out.println("Are you happy to book this date? (Y/N) You may choose to cancel your booking at the end.");
                        validInput = false;
                        while(!validInput)
                        {
                            String confirmation = scan.next().toLowerCase();
                            if(confirmation.equals("y"))
                            {
                                validInput = true;
                                scan.nextLine();
                            }
                            else if (confirmation.equals("n"))
                            {
                                checkInDate = null;
                                validInput = true;
                            }
                            else
                            {
                                System.out.println("Invalid input. Please enter 'Y' to confirm or 'N' to change check in date.");    
                            }
                        }
                    }
                } catch (Exception e)
                {
                    System.out.println("Invalid date format. Please try again!");
                }

            }
            
            System.out.println("Your check in date is: " + checkInDate.format(formatter));
            System.out.println("Your current check out date is: " +checkOutDate.format(formatter));
            
            boolean confirm = false;
            validInput = false;
            switch (locationID) {
                
                case 1:
                    House h1 = new House(0, 0, 0); // so i chucked this in to see if i could get it fully working but idrk how to set h1's hasPool and stuff, mr GPT said somehting with super(); but idk, it sorta looks like it might work the way i did it
                    System.out.println("How many bedrooms would you like your rented house to have? Please choose from 1 to 5 bedrooms.");
                    while (!validInput) {
                        try {

                            numBedrooms = scan.nextInt();
                            if (numBedrooms >= 1 && numBedrooms <= 5) {
                                validInput = true;
                                scan.nextLine(); //So the next question doesn't use return key as the input for the answer.
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter an integer between 1 and 5.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter an integer between 1 and 5!!");
                            scan.nextLine();
                        }
                    }
                    h1.setBedrooms(numBedrooms);

                    validInput = false;
                    System.out.println("How many bathrooms would you like your rented house to have? Please choose from 1 to 5 bathrooms.");
                    while (!validInput) {
                        try {
                            numBathrooms = scan.nextInt();
                            if (numBathrooms >= 1 && numBathrooms <= 5) {
                                validInput = true;
                                scan.nextLine(); //So the next question doesn't use return key as the input for the answer.
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter an integer between 1 and 5.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter an integer between 1 and 5!!");
                            scan.nextLine();
                        }
                    }
                    h1.setBathrooms(numBathrooms);

                    validInput = false;
                    char responseChar = 'n';
                    System.out.println("Would you like your rented House to have a pool? (Y/N)");
                    while (!validInput) {
                        try {
                            houseResponse = scan.next();
                            responseChar = Character.toLowerCase(houseResponse.charAt(0));
                            if (responseChar == 'y' || responseChar == 'n') {
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (responseChar == 'y' || responseChar == 'Y') {
                        h1.setHasPool(true);
                    } else {
                        h1.setHasPool(false);
                    }

                    System.out.println("Would you like your rented House to have a Backyard? (Y/N)");
                    responseChar = 'n';
                    houseResponse = "";
                    validInput = false;
                    while (!validInput) {
                        try {
                            houseResponse = scan.next();
                            responseChar = Character.toLowerCase(houseResponse.charAt(0));
                            if (responseChar == 'y' || responseChar == 'n') {
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'.");
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

                    System.out.println("Would you like your rented House to have a Garage? (Y/N)");
                    responseChar = 'n';
                    houseResponse = "";
                    validInput = false;
                    while (!validInput) {
                        try {
                            houseResponse = scan.next();
                            responseChar = Character.toLowerCase(houseResponse.charAt(0));
                            if (responseChar == 'y' || responseChar == 'n') {
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (responseChar == 'y' || responseChar == 'Y') {
                        h1.setHasGarage(true);
                    } else {
                        h1.setHasGarage(false);
                    }

                    Accommodation house = h1; // could do like this?
                    confirm = ConfirmBooking.confirmBooking(house, numBedrooms, numBathrooms, numNightsBooked, checkInDate, checkOutDate);
                    if(confirm)
                    {   

                        b1.setAccommodation(house);
                        b1.setCheckInDate(checkInDate);
                        b1.setCheckOutDate(checkOutDate);
                        b1.setLocationID(locationID);
                        b1.setLocationStr("House");
                        b1.setNumNightsBooked(numNightsBooked);
                        Writer.writeInvoice(b1, checkInDate, checkOutDate, numNightsBooked);
                        customerHashMap.putBooking(b1);
                        bookings.add(b1);
                    }

                    break;

                case 2:  //APARTMENT
                    String apartmentResponse = "";
                    responseChar = 'n';
                    Apartment a1 = new Apartment(0, 0, 0);
                    System.out.println("How many bedrooms would you like your apartment to have? Please choose from 1 to 3 bedrooms.");
                    numBedrooms = 0;
                    while (!validInput) {
                        try {
                            numBedrooms = scan.nextInt();
                            if (numBedrooms >= 1 && numBedrooms <= 3) {
                                validInput = true;
                                scan.nextLine(); //So the next question doesn't use return key as the input for the answer.
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter an integer between 1 and 3.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter an integer between 1 and 3!!");
                            scan.nextLine();
                        }
                    }
                    a1.setBedrooms(numBedrooms);
                    
                    apartmentResponse = "";
                    responseChar = 'n';
                    validInput = false;
                    System.out.println("How many bathrooms would you like your apartment to have? Please choose from 1 to 2 bathrooms.");
                    numBathrooms = 0;
                    while (!validInput) {
                        try {
                            numBathrooms = scan.nextInt();
                            if (numBathrooms >= 1 && numBathrooms <= 2) {
                                validInput = true;
                                scan.nextLine(); //So the next question doesn't use return key as the input for the answer.
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter an integer between 1 and 2.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter an integer between 1 and 2!!");
                            scan.nextLine();
                        }
                    }
                    a1.setBathrooms(numBathrooms);

                    validInput = false;
                    char needLaundry = 'n';
                    System.out.println("Would you like your apartment to include laundry access? (Y/N)");
                    while (!validInput) {
                        try {
                            apartmentResponse = scan.next();
                            needLaundry = Character.toLowerCase(apartmentResponse.charAt(0));
                            if (needLaundry == 'y' || needLaundry == 'n') {
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (needLaundry == 'y' || needLaundry == 'Y') {
                        a1.setHasLaundry(true);
                    } else {
                        a1.setHasLaundry(false);
                    }

                    System.out.println("Would you like your apartment to have parking? (Y/N)");
                    char needParking = 'n';
                    apartmentResponse = "";
                    validInput = false;
                    while (!validInput) {
                        try {
                            apartmentResponse = scan.next();
                            needParking = Character.toLowerCase(apartmentResponse.charAt(0));
                            if (needParking == 'y' || needParking == 'n') {
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    if (needParking == 'y' || needParking == 'Y') {
                        a1.setHasParking(true);
                    } else {
                        a1.setHasParking(false);
                    }

                    System.out.println("Would you like your apartment to have a balcony? (Y/N)");
                    char needBalcony = 'n';
                    validInput = false;
                    while (!validInput) {
                        try {
                            apartmentResponse = scan.next();
                            needBalcony = Character.toLowerCase(apartmentResponse.charAt(0));
                            if (needBalcony == 'y' || needBalcony == 'n') {
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (needBalcony == 'y' || needBalcony == 'Y') {
                        a1.setHasBalcony(true);
                    } else {
                        a1.setHasBalcony(false);
                    }

                    Accommodation apartment = a1; // could do like this?
                    confirm = ConfirmBooking.confirmBooking(apartment, numBedrooms, numBathrooms, numNightsBooked, checkInDate, checkOutDate);
                    if(confirm)
                    {   
                        
                        b1.setAccommodation(apartment);
                        b1.setCheckInDate(checkInDate);
                        b1.setCheckOutDate(checkOutDate);
                        b1.setLocationID(locationID);
                        b1.setLocationStr("Apartment");
                        b1.setNumNightsBooked(numNightsBooked);
                        Writer.writeInvoice(b1, checkInDate, checkOutDate, numNightsBooked);
                        customerHashMap.putBooking(b1);
                        bookings.add(b1);
                        System.out.println(bookings.toString());
                    }

                    break;

                case 3: //ROOM
                    String roomResponse = "";
                    numBedrooms = 1;
                    numBathrooms = 1;
                    System.out.println("Please Note: A room will always come with 1 bedroom and 1 bathroom.\n");
                    Room r1 = new Room(numBedrooms, numBathrooms, 0);

                    validInput = false;
                    char needTowels = 'n';
                    System.out.println("Would you like your room to come with towels? (Y/N)");
                    while (!validInput) {
                        try {
                            roomResponse = scan.next();
                            needTowels = Character.toLowerCase(roomResponse.charAt(0));
                            if (needTowels == 'y' || needTowels == 'n') {
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (needTowels == 'y' || needTowels == 'Y') {
                        r1.setHasTowels(true);
                    } else {
                        r1.setHasTowels(false);
                    }

                    System.out.println("Would you like your room to be private? (Y/N)");
                    char needPrivate = 'n';
                    roomResponse = "";
                    validInput = false;
                    while (!validInput) {
                        try {
                            roomResponse = scan.next();
                            needPrivate = Character.toLowerCase(roomResponse.charAt(0));
                            if (needPrivate == 'y' || needPrivate == 'n') {
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    if (needPrivate == 'y' || needPrivate == 'Y') {
                        r1.setIsPrivateRoom(true);
                    } else {
                        r1.setIsPrivateRoom(false);
                    }

                    System.out.println("Would you like your room to have air conditioning? (Y/N)");
                    char needAC = 'n';
                    validInput = false;
                    while (!validInput) {
                        try {
                            roomResponse = scan.next();
                            needAC = Character.toLowerCase(roomResponse.charAt(0));
                            if (needAC == 'y' || needAC == 'n') {
                                validInput = true;
                            } else {
                                throw new InputMismatchException("Invalid input. Please enter either 'Y' or 'N'.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    if (needAC == 'y' || needAC == 'Y') {
                        r1.setHasAirConditioning(true);
                    } else {
                        r1.setHasAirConditioning(false);
                    }
                    r1.setBathrooms(1);
                    r1.setBedrooms(1);
                    Accommodation room = r1; // could do like this?
                    confirm = ConfirmBooking.confirmBooking(room, numBedrooms, numBathrooms, numNightsBooked, checkInDate, checkOutDate);
                    if(confirm)
                    {
                        b1.setAccommodation(room);
                        b1.setCheckInDate(checkInDate);
                        b1.setCheckOutDate(checkOutDate);
                        b1.setLocationID(locationID);
                        b1.setLocationStr("Room");
                        b1.setNumNightsBooked(numNightsBooked);
                        Writer.writeInvoice(b1, checkInDate, checkOutDate, numNightsBooked);
                        customerHashMap.putBooking(b1);
                        
                        bookings.add(b1);
                        System.out.println(bookings.toString());

                    }
                   
                    break;
                    
                case 4:
                    System.out.println("Returning to main menu!!!!!!!!!!!!");

                default:
                    System.out.println("Error has occurred!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"); // DELETE THIS BEFORE HANDING IN!!! ONLY USED FOR CHECKING RANDOM ERRORS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }

        }
        nextStep = false;
        
        return b1;
    }
    




}