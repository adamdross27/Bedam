/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package bedam;

import java.awt.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author benr0
 */
public class MakeBookingJFrameTest {
    
@Test
public void testAccomChoiceActionPerformed() {
    // Extract the code from accomChoiceActionPerformed into a separate helper method
private void updateOptionsAndComboBoxes() {
    // Code to update options and combo boxes goes here
}

// Modify the original accomChoiceActionPerformed to call the helper method
private void accomChoiceActionPerformed(ActionEvent event) {
    updateOptionsAndComboBoxes();
}

// Now you can test the updateOptionsAndComboBoxes method directly
@Test
public void testUpdateOptionsAndComboBoxes() {
    MakeBookingJFrame frame = new MakeBookingJFrame();

    // Set the initial state of the frame to prepare for testing
    frame.accomChoice.setSelectedItem("Room");

    // Call the extracted helper method directly
    frame.updateOptionsAndComboBoxes();

    // Check if the options and combo boxes are updated correctly
    Assertions.assertEquals("Warm Towels ($10)", frame.option1.getText());
    Assertions.assertEquals("Private Room ($45)", frame.option2.getText());
    Assertions.assertEquals("Air Conditioning ($20)", frame.option3.getText());
    Assertions.assertArrayEquals(new String[]{"1"}, frame.bathroomChoice.getModel().getItems());
    Assertions.assertArrayEquals(new String[]{"1"}, frame.bedroomChoice.getModel().getItems());
}

 
}
