/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Accommodation Booking System
 *
 */
package bedam;

public class House extends Accommodation { //Subclass of Accommodation

    private boolean hasPool; //User is asked if they want each of these for an extra $$ per night
    private boolean hasYard;
    private boolean hasGarage;

    public House(int bedrooms, int bathrooms, double rentPerNight) 
    {
        super(bedrooms, bathrooms, rentPerNight); //Invoking super from Accommodation
    }

    @Override //Overriding calculateRentPerNight method from abstract class
    public double calculateRentPerNight(int numOfNights) {

        double rent = 0.0;
        rent += (double) getBedrooms() * 45.0; //Depending on how many bedrooms/bathrooms user chooses, the value is multiplied by the price.
        rent += (double) getBathrooms() * 15.0;
        if (hasPool) {
            rent += 50;
        }
        if (hasYard) { //If statements for boolean conditions. These are the add-ons the user may choose to select.
            rent += 15;
        }
        if (hasGarage) {
            rent += 20;
        }
        System.out.println();
        rent *= numOfNights; //Calculates the rent for total or 1 night depending on argument entered.
        return rent;
    }

    @Override
    public void printDetails() {
        System.out.println("Details...");
        
        System.out.println("Number Of Bedrooms: " + getBedrooms());
        System.out.println("Number Of Bathrooms: " + getBathrooms());
        
        if (hasPool) {
            System.out.print("Pool available, ");
        }
        if (hasYard) {
            System.out.print("Yard Available, "); //Prints these statements based on if user selects some/all of these add-ons.
        }
        if (hasGarage) {
            System.out.print("Garage Available");
        }
        System.out.println("Rent Per Night: $" + this.calculateRentPerNight(1)); //Calculates the rent for just one night.
    }

    @Override
    public String toString() { //Overriden toString method
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
        string+=("\nRent Per Night: $" + this.calculateRentPerNight(1));
        return string;
    }

    public boolean isHasPool() { //Get and set methods for private variables
        return hasPool;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }

    public boolean isHasYard() {
        return hasYard;
    }

    public void setHasYard(boolean hasYard) {
        this.hasYard = hasYard;
    }

    public boolean isHasGarage() {
        return hasGarage;
    }

    public void setHasGarage(boolean hasGarage) {
        this.hasGarage = hasGarage;
    }

}
