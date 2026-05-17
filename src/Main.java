package src;
import java.util.Arrays;
import java.util.Scanner;

import src.models.ParkingLot;
import src.models.ParkingSpot;
import src.models.Vehicle;
import src.models.VehicleSize;
import src.services.ParkingLotService;

public class Main {

    private static ParkingLotService parkingLotService; // instance variable to manage the parking lot service

    public static void main(String[] args) {

        // Create a Scanner object to read input from the console
        Scanner scan = new Scanner(System.in);

        // Prompt the user for input
        System.out.println("Welcome to the Parking Lot Management App!");
        System.out.println("Please enter the number of parking spots in the parking lot: ");

        // Continuously read input until a valid number of parking spots is entered
        while (true) {
            String input = scan.nextLine();
            if (input.matches("\\d+")) {
                int numSpots = Integer.parseInt(input);
                
                System.out.println("Initializing parking lot with " + input + " parking spots...");
                initializeParkingLot(numSpots);
                break; // valid input, break out of the loop
            } else {
                System.out.println("Invalid input. Please enter a valid number of parking spots.");
            }
        }

        System.out.println("Parking lot initialized successfully!");

        // menu loop to allow the user to park/remove/display vehicles until they choose to exit
        while (true) {
            System.out.println("\n=== MAIN MENU ===\n\nPlease select an option:");
            System.out.println("1. Park a vehicle\n2. Remove a vehicle\n3. Display vehicle status\n4. Exit\n");
            String input = scan.nextLine();

            // validate user input and call the appropriate helper method based on the selected option
            if (input.matches("\\d+")) { 
                switch (Integer.parseInt(input)) {
                    case 1:
                        // code to park a vehicle
                        System.out.println("\n=== PARKING ===\n\nType \"back\" to return to the main menu.\nPlease enter the license plate and size of the vehicle to park (e.g., ABC123 SMALL)\nSizing options: " + Arrays.toString(VehicleSize.values()));

                        while (true) {
                            input = scan.nextLine().toUpperCase();

                            if (input.equalsIgnoreCase("back")) {
                                break; // return to the main menu
                            }

                            String[] parts = input.split(" ");
                            if (parts.length == 2 && parts[0].matches("[A-Z0-9]+") && parts[1].matches("SMALL|LARGE|OVERSIZE")) {
                                String licensePlate = parts[0];
                                VehicleSize size = VehicleSize.valueOf(parts[1]);
                                parkingLotService.parkVehicle(new Vehicle(licensePlate, size));
                                break; // valid input, break out of the loop
                            } else {
                                System.out.println("Invalid input. Type \"back\" to return to the main menu.\nPlease enter the license plate and size of the vehicle to park (e.g., ABC123 SMALL)\nSizing options: " + Arrays.toString(VehicleSize.values()));
                            }
                        }
                        
                        break;
                    case 2:
                        // code to remove a vehicle
                        System.out.println("\n=== REMOVING ===\n\nType \"back\" to return to the main menu.\nPlease enter the license plate of the vehicle to remove:");
                        
                        while (true) {
                            input = scan.nextLine().toUpperCase();

                            if (input.equalsIgnoreCase("back")) {
                                break; // return to the main menu
                            }

                            if (input.matches("[A-Z0-9]+")) {
                                String licensePlate = input;
                                parkingLotService.removeVehicle(licensePlate);
                                break; // valid input, break out of the loop
                            } else {
                                System.out.println("Invalid input. Type \"back\" to return to the main menu.\nPlease enter the license plate of the vehicle to remove:");
                            }
                        }
                        break;
                    case 3:
                        // code to display vehicle status
                        System.out.println("\n=== STATUS ===\n\nType \"back\" to return to the main menu.\nPlease enter the license plate of the vehicle to display its status:");
                        
                        while (true) {
                            input = scan.nextLine().toUpperCase();

                            if (input.equalsIgnoreCase("back")) {
                                break; // return to the main menu
                            }

                            if (input.matches("[A-Z0-9]+")) {
                                String licensePlate = input;
                                parkingLotService.displayVehicleStatus(licensePlate);
                                break; // valid input, break out of the loop
                            } else {
                                System.out.println("Invalid input. Please enter the license plate of the vehicle to display its status:");
                            }
                        }
                        break;
                    case 4:
                        System.out.println("\n=== EXITING ===\n");
                        System.out.println("Exiting the Parking Lot Management App. Goodbye!");
                        
                        scan.close();
                        return; // exit the program
                    default:
                        System.out.println("Invalid option. Please enter a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid option. Please enter a number between 1 and 4.");
            }
            
        }
    }

    // helper method to load initial parking lot data (e.g., parking spots, vehicles, etc.)
    private static void initializeParkingLot(int numSpots) {
        ParkingLot parkingLot = new ParkingLot();

        for (int i = 1; i <= numSpots; i++) {
            // create a parking spot with a unique spot number and a random size
            VehicleSize size = VehicleSize.values()[(int) (Math.random() * VehicleSize.values().length)];
            ParkingSpot spot = new ParkingSpot(i, size);
            parkingLot.addParkingSpot(spot);
        }

        // initialize parking lot service to manage the parking lot
        parkingLotService = new ParkingLotService(parkingLot);

        // add some vehicles to the parking lot
        /*
        Vehicle vehicle1 = new Vehicle("ABC123", VehicleSize.SMALL);
        Vehicle vehicle2 = new Vehicle("XYZ789", VehicleSize.LARGE);
        Vehicle vehicle3 = new Vehicle("DEF456", VehicleSize.OVERSIZE);

        parkingLotService.parkVehicle(vehicle1);
        parkingLotService.parkVehicle(vehicle2);
        parkingLotService.parkVehicle(vehicle3);
        */
    }   
}
