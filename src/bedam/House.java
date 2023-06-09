/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment Two - Hotel Booking System
 *
 */
package bedam;

public class House extends Accommodation { //Subclass of Accommodation

    private static boolean hasPool; //User is asked if they want each of these for an extra $$ per night
    private static boolean hasYard;
    private static boolean hasGarage;

    public House(int bedrooms, int bathrooms, double rentPerNight) {
        super(bedrooms, bathrooms, rentPerNight); //Constrctor that invokes super
    }

    @Override
    public double calculateRentPerNight(int numOfNights) { 
        // Method to calculate the rent per night
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
    public void printDetails() {
        System.out.println("Details...");
        if (hasPool) {
            System.out.print("Pool available, "); //Details to be printed for user
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
            string+=("Yard Available, "); //ToString method
        }
        if (hasGarage) {
            string+=("Garage Available");
        }
        string+=("\nRent Per Night: $" + this.calculateRentPerNight(1) + "\n");
        return string;
    }

    public static boolean isHasPool() { //Get and Set methods for add ons
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
