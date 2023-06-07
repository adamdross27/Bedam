/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bedam;

import java.util.Scanner;

/**
 *
 * @author benr0
 */
public class Person {
    
    private String name;
    private String pin;
    
    public Person(String name, String pin)
    {
        this.name = name;
        this.pin = pin;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getPin()
    {
        return this.pin;
    }
    
        public static Person makePerson()
    {
       
        Scanner scan = new Scanner(System.in);
        System.out.println("To save your details please enter your full name:");
        String userName = scan.nextLine();
        System.out.println("Please enter a security pin:");
        String pin = scan.nextLine();

        boolean validInput = false;
        
        int pinInt = -1;
        while (validInput != true) {
            if (pin.length() != 4) {
                System.out.println("Please enter a numerical 4 digit pin.");
                pin = scan.nextLine();
            } else {
                try {
                    pinInt = Integer.parseInt(pin);

                } catch (NumberFormatException e) {
                    System.out.println("That is not a numerical pin, please try again.");
                    pin = scan.nextLine();
                }
            }
            if (pinInt != -1) {
                validInput = true;
            }
        }
        
        return new Person(userName, pin);
    }
}
