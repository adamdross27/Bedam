/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Hotel Booking System
 *
 */
package bedam;

public class Room extends Accommodation {

    private boolean hasTowels;
    private boolean isPrivateRoom;
    private boolean hasAirConditioning;

    public Room(int bedrooms, int bathrooms, double rentPerNight) {
        super(1, 1, 100);
        //this.accomName = "Room";
    }

    @Override
    public double calculateRentPerNight(int numOfNights) {
        double rent = 0.0;
        rent += (double) getBedrooms() * 45.0;
        rent += (double) getBathrooms() * 15.0;
        if (hasTowels) {
            rent += 10;
        }
        if (isPrivateRoom) {
            rent += 45;
        }
        if (hasAirConditioning) {
            rent += 20;
        }
        rent *= numOfNights;
        return rent;
    }

    @Override
    public void printDetails() {
        System.out.println("\nDetails about your booking:\n");

        System.out.println("Number Of Bedrooms: " + getBedrooms());
        System.out.println("Number Of Bathrooms: " + getBathrooms());

        if (hasTowels) {
            System.out.print("Towels included, ");
        }
        if (isPrivateRoom) {
            System.out.print("The room is private, ");
        }
        if (hasAirConditioning) {
            System.out.print("Air conditioning included");
        }
        System.out.println("");
        System.out.println("Rent: $" + this.calculateRentPerNight(1) + "\n");
    }

    @Override
    public String toString() {
        String string = "";
        if (hasTowels) {
            string += ("Towels included, ");
        }
        if (isPrivateRoom) {
            string += ("The room is private, ");
        }
        if (hasAirConditioning) {
            string += ("Air conditioning included");
        }
        string += ("\nRent Per Night: $" + this.calculateRentPerNight(1) + "\n");
        return string;
    }

    public boolean isHasTowels() {
        return hasTowels;
    }

    public void setHasTowels(boolean hasTowels) {
        this.hasTowels = hasTowels;
    }

    public boolean isIsPrivateRoom() {
        return isPrivateRoom;
    }

    public void setIsPrivateRoom(boolean isPrivateRoom) {
        this.isPrivateRoom = isPrivateRoom;
    }

    public boolean isHasAirConditioning() {
        return hasAirConditioning;
    }

    public void setHasAirConditioning(boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
    }

    
    @Override
    public Accommodation getAccommodation()
    {
        return this;
    }
}
