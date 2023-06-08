/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Hotel Booking System
 *
 */
package bedam;

public class House extends Accommodation {

    private static boolean hasPool; //User is asked if they want each of these for an extra $$ per night
    private static boolean hasYard;
    private static boolean hasGarage;

    public House(int bedrooms, int bathrooms, double rentPerNight) {
        super(bedrooms, bathrooms, rentPerNight);
    }

    @Override
    public double calculateRentPerNight(int numOfNights) {
        //just a rough kinda vibe of a price 'formula'?
        double rent = 0.0;
        rent += (double) getBedrooms() * 45.0;
        rent += (double) getBathrooms() * 15.0;
        if (hasPool) {
            rent += 50;
        }
        if (hasYard) {
            rent += 15;
        }
        if (hasGarage) {
            rent += 20;
        }
        System.out.println();
        rent *= numOfNights;
        return rent;
    }

    @Override
    //could use for write class possibly?
    public void printDetails() {
        System.out.println("Details...");
        if (hasPool) {
            System.out.print("Pool available, ");
        }
        if (hasYard) {
            System.out.print("Yard Available, ");
        }
        if (hasGarage) {
            System.out.print("Garage Available");
        }
        System.out.println("Rent Per Night: $" + this.calculateRentPerNight(1)); //not sure how to implement num of nights for now
    }

    @Override
    public String toString() {
        String string = "";
        if (hasPool) {
            string+=("Pool available, ");
        }
        if (hasYard) {
            string+=("Yard Available, ");
        }
        if (hasGarage) {
            string+=("Garage Available");
        }
        string+=("\nRent Per Night: $" + this.calculateRentPerNight(1) + "\n");
        return string;
    }

    public static boolean isHasPool() {
        return hasPool;
    }

    public static void setHasPool(boolean hasPool) {
        House.hasPool = hasPool;
    }

    public static boolean isHasYard() {
        return hasYard;
    }

    public static void setHasYard(boolean hasYard) {
        House.hasYard = hasYard;
    }

    public static boolean isHasGarage() {
        return hasGarage;
    }

    public static void setHasGarage(boolean hasGarage) {
        House.hasGarage = hasGarage;
    }

}
