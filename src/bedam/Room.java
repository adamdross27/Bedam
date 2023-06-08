/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Hotel Booking System
 *
 */
package bedam;

public class Room extends Accommodation {

    private static boolean hasTowels;
    private static boolean isPrivateRoom;
    private static boolean hasAirConditioning;

    public Room(int bedrooms, int bathrooms, double rentPerNight) {
        super(1, 1, rentPerNight);
     
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

    public static boolean isHasTowels() {
        return hasTowels;
    }

    public static void setHasTowels(boolean hasTowels) {
        Room.hasTowels = hasTowels;
    }

    public static boolean isIsPrivateRoom() {
        return isPrivateRoom;
    }

    public static void setIsPrivateRoom(boolean isPrivateRoom) {
        Room.isPrivateRoom = isPrivateRoom;
    }

    public static boolean isHasAirConditioning() {
        return hasAirConditioning;
    }

    public static void setHasAirConditioning(boolean hasAirConditioning) {
        Room.hasAirConditioning = hasAirConditioning;
    }

}
