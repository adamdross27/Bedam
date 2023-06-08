/**
 * Program Design and Construction 
 * Ben Rogers - 21145117 
 * Adam Ross - 21151208
 * Assignment One - Hotel Booking System
 *
 */

package bedam;

public abstract class Accommodation 
{
    private int bedrooms;
    private int bathrooms;
    private double rentPerNight;
   // public String accomName;
    
    public Accommodation(int bedrooms, int bathrooms, double rentPerNight)
    {
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.rentPerNight = 0.0; //Temporary 
    }
    
    public Accommodation(String bedrooms, String bathrooms)
    {
        this.bedrooms = Integer.parseInt(bedrooms);
        this.bathrooms = Integer.parseInt(bathrooms);
        this.rentPerNight = 0.0; //Temporary 
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
    public void setBedrooms(int bedrooms) {
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
        
        return this.rentPerNight;
        
    }

    /**
     * @param rentPerNight the rentPerNight to set
     */
    public void setRentPerNight(double rentPerNight) {
        this.rentPerNight = rentPerNight;
    }
    
    public Accommodation getAccommodation()
    {
        return this;
    }
    
}
