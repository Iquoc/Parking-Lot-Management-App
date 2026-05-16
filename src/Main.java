package src;
import java.util.Scanner;

import src.models.ParkingLot;
import src.models.ParkingSpot;
import src.models.Vehicle;
import src.models.VehicleSize;
import src.services.ParkingLotService;

public class Main {

    private static ParkingLotService parkingLotService;

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
                helper(numSpots);
                System.out.println("Initializing parking lot with " + input + " parking spots...");
                break; // valid input, break out of the loop
            } else {
                System.out.println("Invalid input. Please enter a valid number of parking spots.");
            }
        }

        System.out.println("Parking lot initialized successfully!");

        
        // while (true) {
        //     System.out.println("1. Park a vehicle%n 2. Remove a vehicle%n 3. Display available parking spots%n 4. Display vehicle status%n 5. Exit%n");
        //     String input = scan.nextLine();

        //     if (input.matches("[1-5]")) {
        //         // valid input, break out of the loop
        //         break;
        //     } else {
        //         System.out.println("Invalid input. Please enter a number between 1 and 5.");
        //     }

        //     switch (Integer.parseInt(input)) {
        //         case 1:
        //             // code to park a vehicle
        //             parkingLotService.parkVehicle(new Vehicle("ABC123", VehicleSize.SMALL));
        //             break;
        //         case 2:
        //             // code to remove a vehicle
        //             parkingLotService.removeVehicle("ABC123");
        //             break;
        //         case 3:
        //             // code to display available parking spots
        //             break;
        //         case 4:
        //             // code to display vehicle status
        //             break;
        //         case 5:
        //             System.out.println("Exiting the Parking Lot Management App. Goodbye!");
        //             return; // exit the program
        //         default:
        //             System.out.println("Invalid option. Please enter a number between 1 and 5.");
        //     }
        // }

        // Close the Scanner to free up resources

        scan.close();
    }

    // helper method to load initial parking lot data (e.g., parking spots, vehicles, etc.)
    private static void helper(int numSpots) {
        // create a parking lot with the specified number of parking spots
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
        Vehicle vehicle1 = new Vehicle("ABC123", VehicleSize.SMALL);
        Vehicle vehicle2 = new Vehicle("XYZ789", VehicleSize.LARGE);
        Vehicle vehicle3 = new Vehicle("DEF456", VehicleSize.OVERSIZE);

        parkingLotService.parkVehicle(vehicle1);
        parkingLotService.parkVehicle(vehicle2);
        parkingLotService.parkVehicle(vehicle3);
    }   

}
