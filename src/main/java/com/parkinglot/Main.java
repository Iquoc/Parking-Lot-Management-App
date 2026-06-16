package com.parkinglot;
import java.util.Arrays;
import java.util.Scanner;

import com.parkinglot.models.ParkingLot;
import com.parkinglot.models.ParkingSpot;
import com.parkinglot.models.Vehicle;
import com.parkinglot.models.VehicleSize;
import com.parkinglot.services.ParkingLotService;

public class Main {

    private static ParkingLotService parkingLotService; // instance variable to manage the parking lot service

    public static void main(String[] args) {

        // Create a Scanner object to read input from the console
        Scanner scan = new Scanner(System.in);

        // Prompt the user for input
        System.out.println("Welcome to the Parking Lot Management App!");
        
        // >>> MANUAL SETUP 

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

        // <<< MANUAL SETUP

        // >>> SEED DATA

        // seedData(); // seed initial data into the parking lot

        // <<< SEED DATA

        System.out.println("Parking lot initialized successfully!");
        String menuString = """

            === MAIN MENU ===
            
            Please select an option:
            1. Park a vehicle
            2. Remove a vehicle
            3. Display vehicle status
            4. Display parking lot status
            5. Exit

            """;

        String parking_suffix = """
                
                Type \"back\" to return to the main menu.
                Please enter the license plate and size of the vehicle to park (e.g., ABC123 SMALL)
                Sizing options: %s

                """.formatted(Arrays.toString(VehicleSize.values()));

        String remove_suffix = """
                
                Type \"back\" to return to the main menu.
                Please enter the license plate of the vehicle to remove:

                """;

        String status_suffix = """
                
                Type \"back\" to return to the main menu.
                Please enter the license plate of the vehicle to display its status:

                """;

        String invalid_input_suffix = """
                
                Invalid input. Type \"back\" to return to the main menu.

                """;        

        // menu loop to allow the user to park/remove/display vehicles until they choose to exit
        while (true) {
            System.out.print(menuString);
            String input = scan.nextLine();

            // validate user input and call the appropriate helper method based on the selected option
            if (input.matches("\\d+")) { 
                switch (Integer.parseInt(input)) {
                    case 1 -> {
                        // code to park a vehicle
                        System.out.print("""

                            === PARKING ===
                            %s""".formatted(parking_suffix)
                        );

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
                                System.out.print("""

                                    Invalid input. 
                                    %s""".formatted(parking_suffix)
                                );
                            }
                        }
                        
                    }
                    case 2 -> {
                        // code to remove a vehicle
                        System.out.print("""

                            === REMOVING ===
                            %s""".formatted(remove_suffix)
                        );
                        
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
                                System.out.print("""
                                    Invalid input.
                                    %s""".formatted(remove_suffix)
                                );
                            }
                        }
                    }
                    case 3 -> {
                        // code to display vehicle status
                        System.out.print("""

                            === STATUS ===
                            %s""".formatted(status_suffix)
                        );
                        
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
                                System.out.print("""
                                    Invalid input.
                                    %s""".formatted(status_suffix)
                                );
                            }
                        }
                    }
                    case 4 -> {
                        // code to display all parking spots and their status
                        System.out.print("""

                            === PARKING LOT STATUS ===

                            """
                        );
                        parkingLotService.displayParkingLotStatus();
                    }
                    case 5 -> {
                        // code to exit the program
                        System.out.print("""

                            === EXITING ===

                            Exiting the Parking Lot Management App. Goodbye!
                            """
                        );
                        scan.close();
                        return; // exit the program
                    }
                    default -> System.out.print(invalid_input_suffix);
                }
            } else {
                System.out.print(invalid_input_suffix);
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
    }   

    private static void seedData() {
        // This method can be used to seed initial data into the parking lot if needed
        ParkingLot parkingLot = new ParkingLot();

        // Add some parking spots
        int n = 10; // number of parking spots to add
        double s_per = 0.5; // percentage of small spots
        double l_per = 0.3; // percentage of large spots

        int smallSpots = (int) (n * s_per);
        int largeSpots = (int) (n * l_per);
        // int oversizeSpots = n - smallSpots - largeSpots;

        for (int i = 1; i <= n; i++) {
            if (i <= smallSpots) {
                VehicleSize size = VehicleSize.SMALL;
                ParkingSpot spot = new ParkingSpot(i, size);
                parkingLot.addParkingSpot(spot);
            } else if (i <= smallSpots + largeSpots) {
                VehicleSize size = VehicleSize.LARGE;
                ParkingSpot spot = new ParkingSpot(i, size);
                parkingLot.addParkingSpot(spot);
            } else {
                VehicleSize size = VehicleSize.OVERSIZE;
                ParkingSpot spot = new ParkingSpot(i, size);
                parkingLot.addParkingSpot(spot);
            }
        }

        System.out.println("\nParking lot initialized with 10 slots");
        System.out.println("Slot distribution: " + smallSpots + " small, " + largeSpots + " large, " + (n - smallSpots - largeSpots) + " oversize");

        // initialize parking lot service to manage the parking lot
        parkingLotService = new ParkingLotService(parkingLot);

        // add some vehicles to the parking lot
        
        Vehicle vehicle1 = new Vehicle("ABC123", VehicleSize.SMALL);
        Vehicle vehicle2 = new Vehicle("XYZ789", VehicleSize.LARGE);
        Vehicle vehicle3 = new Vehicle("DEF456", VehicleSize.OVERSIZE);

        System.out.println("\nSample vehicles added to the parking lot: " + vehicle1.licensePlate() + " (" + vehicle1.size() + "), " + vehicle2.licensePlate() + " (" + vehicle2.size() + "), " + vehicle3.licensePlate() + " (" + vehicle3.size() + ")");

        parkingLotService.parkVehicle(vehicle1);
        parkingLotService.parkVehicle(vehicle2);
        parkingLotService.parkVehicle(vehicle3);
        
    }
}
