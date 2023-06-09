import bedam.Accommodation;
import bedam.Booking;
import bedam.Room;
import static bedam.Writer.writeInvoice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;

public class WriterTest {
    private static final String INVOICE_FILE_PATH = "./resources/invoice.txt";
    private static final String GUEST_INFO_FILE_PATH = "./resources/GuestInformation.txt";
    private static final String BOOKING_NUM_FILE_PATH = "./resources/bookingNum.txt";

    @Test
    public void testWriteInvoice() {
        // Create a sample booking and accommodation
        Booking booking = new Booking();
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusDays(3);
        int nights = 3;
        Accommodation accommodation = new Room(1, 1);

        // Call the writeInvoice method

        writeInvoice(booking, start, end, nights, accommodation);
        // Check if the invoice file exists
        File invoiceFile = new File(INVOICE_FILE_PATH);
        Assertions.assertTrue(invoiceFile.exists());

        // Read the contents of the invoice file
        String invoiceContent = readContentFromFile(INVOICE_FILE_PATH);
//        readContentFromFile();
        // Assert the expected content
        Assertions.assertTrue(invoiceContent.contains("Thank you for booking with Bedam!"));
        Assertions.assertTrue(invoiceContent.contains("You are staying for " + nights + " nights."));
        // Add more assertions as needed
    }
    
}
