import java.util.Scanner;

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
