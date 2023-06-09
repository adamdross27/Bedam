/**
 * Program Design and Construction
 * Ben Rogers - 21145117
 * Adam Ross - 21151208
 * Assignment Two - Hotel Booking System
 *
 */

package bedam;

public abstract class Accommodation 
{
    private int bedrooms;
    private int bathrooms; //Variables
    private double rentPerNight;
    
    public Accommodation(int bedrooms, int bathrooms, double rentPerNight)
    {
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.rentPerNight = 0.0; //Constructor 
    }
    
    public Accommodation(String bedroomStr, String bathroomStr)
    {
        this.bedrooms = Integer.parseInt(bedroomStr); //Constructor used for JDBC
        this.bathrooms = Integer.parseInt(bathroomStr);
    }
    
    public Accommodation(int bedrooms, int bathrooms)
    {
        this.bedrooms = bedrooms; //Another constructor with no rentPerNight initialisation
        this.bathrooms = bathrooms;
    }
    
    public abstract double calculateRentPerNight(int numOfNights);
    
    public abstract void printDetails();

    /**
     * @return the bedrooms
     */
    public int getBedrooms() {
        return bedrooms;
    }

    /**
     * @param bedrooms the bedrooms to set
     */
    public void setBedrooms(int bedrooms) { //Get and Set methods 
        this.bedrooms = bedrooms;
    }

    /**
     * @return the bathrooms
     */
    public int getBathrooms() {
        return bathrooms;
    }

    /**
     * @param bathrooms the bathrooms to set
     */
    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    /**
     * @return the rentPerNight
     */
    public double getRentPerNight() {
        return rentPerNight;
    }

    /**
     * @param rentPerNight the rentPerNight to set
     */
    public void setRentPerNight(double rentPerNight) {
        this.rentPerNight = rentPerNight;
    }
    
    
}
