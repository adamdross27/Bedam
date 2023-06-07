/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment One - Accommodation Booking System
 *
 */

package bedam;

public abstract class Accommodation //Abstract class that will have variables and methods inherited from subclasses
{
    private int bedrooms;
    private int bathrooms; //All accommodation types will have these variables
    private double rentPerNight;
    
    public Accommodation(int bedrooms, int bathrooms, double rentPerNight)
    {
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms; //Constructor
        this.rentPerNight = 0.0; //Will be replaced via the calculateRentPerNight(int numOfNights) method 
    }
    
    public abstract double calculateRentPerNight(int numOfNights); //Declaration of method
    
    public abstract void printDetails(); //Declaration of method

    public int getBedrooms() 
    {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) 
    {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() 
    {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms)  //Get and set methods for all variables
    {
        this.bathrooms = bathrooms;
    }

    public double getRentPerNight() 
    {
        return rentPerNight;
    }

    public void setRentPerNight(double rentPerNight) 
    {
        this.rentPerNight = rentPerNight;
    }
}
