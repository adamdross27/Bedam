/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Accommodation Booking System
 *
 */
package bedam;

public class Apartment extends Accommodation { //Subclass of Accommodation

    private boolean hasLaundry; //Unique variables to Apartment
    private boolean hasParking; //Asks the user if they want each of these for extra $ per night
    private boolean hasBalcony;

    public Apartment(int bedrooms, int bathrooms, double rentPerNight) 
    {
        super(bedrooms, bathrooms, rentPerNight); //Invoking super from Accommodation class
    }

    @Override //Overriding the calculateRentPerNight method 
    public double calculateRentPerNight(int numOfNights) 
    {
        double rent = 0.0;
        rent += (double) getBedrooms() * 35.0;
        rent += (double) getBathrooms() * 15.0;
        if (hasLaundry) 
        {
            rent += 20;
        }
        if (hasParking) //The user is asked if they would like each of these variables. Their response will give it a true/false result to these variables
        {               //If true, they will be charged an add on fee.
            rent += 50;
        }
        if (hasBalcony) 
        {
            rent += 30;
        }
        rent *= numOfNights; //This enables the total rent or just one night of rent to be calculated 
        return rent;
    }

    @Override
    public void printDetails() {
        System.out.println("Details...");
        
        System.out.println("Number Of Bedrooms: " + getBedrooms());
        System.out.println("Number Of Bathrooms: " + getBathrooms());
        
        if (hasLaundry) {
            System.out.print("Laundry access included, ");
        }
        if (hasParking) {
            System.out.print("Parking included, "); //String of add-ons included depending if the user said yes to some/all of these options
        }
        if (hasBalcony) {
            System.out.print("Balcony available");
        }
        System.out.println("");
        System.out.println("Rent Per Night: $"+this.calculateRentPerNight(1)); //Calculating the rent for just ONE night.
    }
    
       @Override
    public String toString() { //Apartment toString method
        String string = "";
        if (hasLaundry) {
            string+=("Laundry access included, ");
        }
        if (hasParking) {
            string+=("Parking included, "); 
        }
        if (hasBalcony) {
            string+=("Balcony available");
        }
        string+=("\nRent Per Night: $" + this.calculateRentPerNight(1));
        return string; //Returns the above string concatenations
    }


    public boolean isHasLaundry() 
    {
        return hasLaundry;
    }

    public void setHasLaundry(boolean hasLaundry) 
    {
        this.hasLaundry = hasLaundry;
    }

    public boolean isHasParking() 
    {
        return hasParking;
    }

    public void setHasParking(boolean hasParking)  //Get and set methods for boolean add-ons for Apartment
    {
        this.hasParking = hasParking;
    }

    public boolean isHasBalcony() 
    {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) 
    {
        this.hasBalcony = hasBalcony;
    }
}
