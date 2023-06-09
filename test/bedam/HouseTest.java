/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package bedam;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author benr0
 */
public class HouseTest {

    @Test
    public void testCalculateRentPerNight() {
        House house = new House(2, 1, 0.0);
        double rentPerNight = house.calculateRentPerNight(3);
        Assertions.assertEquals(165.0, rentPerNight);
    }
}
