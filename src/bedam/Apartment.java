/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Hotel Booking System
 *
 */
package bedam;

public class Apartment extends Accommodation {

    private boolean hasLaundry;
    private boolean hasParking; //Asks the user if they want each of these for extra $ per night
    private boolean hasBalcony;

    public Apartment(int bedrooms, int bathrooms, double rentPerNight) {
        super(bedrooms, bathrooms, rentPerNight);
    }

    @Override
    public double calculateRentPerNight(int numOfNights) {
        double rent = 0.0;
        rent += (double) getBedrooms() * 35.0;
        rent += (double) getBathrooms() * 15.0;
        if (hasLaundry) {
            rent += 20;
        }
        if (hasParking) {
            rent += 50;
        }
        if (hasBalcony) {
            rent += 30;
        }
        rent *= numOfNights;
        return rent;
    }

    @Override
    public void printDetails() {
        System.out.println("Details...");
        if (hasLaundry) {
            System.out.print("Laundry access included, ");
        }
        if (hasParking) {
            System.out.print("Parking included, ");
        }
        if (hasBalcony) {
            System.out.print("Balcony available");
        }
        System.out.println("");
        System.out.println("Rent: $"+this.calculateRentPerNight(1));
    }
    
       @Override
    public String toString() {
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
        string+=("\nRent Per Night: $" + this.calculateRentPerNight(1) + "\n");
        return string;
    }


    public boolean isHasLaundry() {
        return hasLaundry;
    }

    public void setHasLaundry(boolean hasLaundry) {
        this.hasLaundry = hasLaundry;
    }

    public boolean isHasParking() {
        return hasParking;
    }

    public void setHasParking(boolean hasParking) {
        this.hasParking = hasParking;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }
    
    @Override
    public Accommodation getAccommodation()
    {
        return this;
    }
}
