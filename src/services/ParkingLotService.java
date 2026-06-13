package src.services;

import src.models.ParkingLot;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import src.models.ParkingSpot;
import src.models.Vehicle;

public class ParkingLotService {
    
    private ParkingLot parkingLot;
    private Map<String, ParkingSpot> vehicleMap;

    public ParkingLotService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.vehicleMap = new HashMap<>();
    }

    // find a parking spot for the given vehicle and park it in the parking lot
    public boolean parkVehicle(Vehicle vehicle) {
        // // check if vehicle is already parked in the parking lot by looking it up in the map
        // if (vehicleMap.containsKey(vehicle.licensePlate())) {
        //     System.out.println("Vehicle with license plate " + vehicle.licensePlate() + " is already parked in the parking lot.");
        //     return false;
        // }

        // // find an available parking spot for the vehicle
        // // if a spot is found, mark it as occupied and add the vehicle to the map
        // // return true if the vehicle was parked successfully, false otherwise
        // List<ParkingSpot> availableSpots = findAvailableSpots();
        // ParkingSpot nextAvailable = null; // if there is no spot matching the vehicle size, this will be the next available spot that can fit the vehicle

        // for (ParkingSpot spot : availableSpots) {
        //     if (spot.getSize().getRank() == vehicle.size().getRank()) {
        //         spot.addVehicle(vehicle);
        //         vehicleMap.put(vehicle.licensePlate(), spot); // add the vehicle to the map with its license plate as the key
        //         return true;
        //     }
        //     else if (spot.getSize().getRank() - 1 == vehicle.size().getRank()) {
        //         if (nextAvailable == null) {
        //             nextAvailable = spot; // update the next available medium spot that can fit the vehicle
        //         }
        //     }
        //     else if (spot.getSize().getRank() - 2 == vehicle.size().getRank()) {
        //         if (nextAvailable == null) {
        //             nextAvailable = spot; // update the next available large spot that can fit the vehicle
        //         }
        //     }
        // }

        AtomicBoolean isParked = new AtomicBoolean(false); // thread safe

        // filter the available spots to find the first spot that can fit the vehicle based on its size rank
        parkingLot.getAvailableSpots().stream()
            .filter(spot -> spot.getSize().getRank() >= vehicle.size().getRank())
            .min(Comparator.comparingInt(spot -> spot.getSize().getRank())) // find the spot with the smallest size rank that can fit the vehicle
            .ifPresentOrElse(
                spot -> {
                    spot.addVehicle(vehicle);
                    vehicleMap.put(vehicle.licensePlate(), spot); // add the vehicle to the map with its license plate as the key
                    isParked.set(true);
                },
                () -> {
                    System.out.println("No available parking spot found for vehicle with license plate " + vehicle.licensePlate() + ".");
                    isParked.set(false);
                }
            );

        return isParked.get();

        // if (nextAvailable != null) {
        //     nextAvailable.addVehicle(vehicle);
        //     vehicleMap.put(vehicle.licensePlate(), nextAvailable); // add the vehicle to the map with its license plate as the key
        //     return true;
        // }

        // System.out.println("No available parking spot found for vehicle with license plate " + vehicle.licensePlate() + ".");
        // return false;
    }

    // remove the vehicle with the given license plate from the parking lot
    public boolean removeVehicle(String licensePlate) {
        // find the parking spot associated with the given license plate
        // if a spot is found, mark it as available and remove the vehicle from the map
        // return true if the vehicle was removed successfully, false otherwise
        if (vehicleMap.containsKey(licensePlate)) {
            ParkingSpot spot = vehicleMap.get(licensePlate);
            spot.removeVehicle();
            vehicleMap.remove(licensePlate);
            return true;
        }

        System.out.println("No vehicle with license plate " + licensePlate + " was found in the parking lot.");
        return false;
    }

    
    // find all available parking spots in the parking lot
    private List<ParkingSpot> findAvailableSpots() {
        return parkingLot.getAvailableSpots(); // return a list of all parking spots that are not currently occupied
    }
    
    // display the current status of the vehicle if it is parked in the parking lot USING vehicle object
    public void displayVehicleStatus(Vehicle vehicle) {
        // // check if the vehicle is parked in the parking lot by looking it up in the map
        // // if the vehicle is found, display its current parking spot and status
        // if (vehicleMap.containsKey(vehicle.licensePlate())) {
        //     ParkingSpot spot = vehicleMap.get(vehicle.licensePlate());

        //     displayVSFormat(vehicle, spot);

        // } else {
        //     System.out.println("Vehicle with license plate " + vehicle.licensePlate() + " is not parked in the parking lot.");
        // }
        
        // optional container to handle null values and avoid NullPointerException
        // if the vehicle is found in the map, display its current parking spot and status using the displayVSFormat method
        Optional.ofNullable(
            vehicleMap.get(vehicle.licensePlate()))
            .ifPresentOrElse(
                spot -> {
                    displayVSFormat(vehicle, spot);
                }, 
                () -> {
                    System.out.println("Vehicle with license plate " + vehicle.licensePlate() + " is not parked in the parking lot.");
                }
            );
    }

    // display the current status of the vehicle if it is parked in the parking lot USING String licensePlate
    public void displayVehicleStatus(String licensePlate) {
        // // check if the vehicle is parked in the parking lot by looking it up in the map
        // // if the vehicle is found, display its current parking spot and status
        // if (vehicleMap.containsKey(licensePlate)) {
        //     ParkingSpot spot = vehicleMap.get(licensePlate);

        //     displayVSFormat(spot.getVehicle(), spot);
        // } else {
        //     System.out.println("Vehicle with license plate " + licensePlate + " is not parked in the parking lot.");
        // }

        // optional container to handle null values and avoid NullPointerException
        // if the vehicle is found in the map, display its current parking spot and status using the displayVSFormat method
        Optional.ofNullable(
            vehicleMap.get(licensePlate))
            .ifPresentOrElse(
                spot -> {
                    displayVSFormat(spot.getVehicle(), spot);
                }, 
                () -> {
                    System.out.println("Vehicle with license plate " + licensePlate + " is not parked in the parking lot.");
                }
            );
    }

    private void displayVSFormat(Vehicle vehicle, ParkingSpot spot) {
        System.out.print(
            """
            
            === VEHICLE PARKING STATUS ===

            License Plate: %s
            Vehicle Size: %s
            Parking Spot Size: %s
            Parking Spot Number: %d
            """.formatted(
                vehicle.licensePlate(),
                vehicle.size(),
                spot.getSize(),
                spot.getSpotNumber()
            )
        );

        // System.out.printf("=== VEHICLE PARKING STATUS ===%n");
        // System.out.printf("License Plate: %s%n", vehicle.licensePlate());
        // System.out.printf("Vehicle Size: %s%n", vehicle.size());
        // System.out.printf("Parking Spot Size: %s%n", spot.getSize());
        // System.out.printf("Parking Spot Number: %d%n", spot.getSpotNumber());
    }

    public void displayParkingLotStatus() {
        // System.out.printf("=== PARKING LOT STATUS ===%n");
        System.out.printf("Total Spots: %d%n", parkingLot.getParkingSize());

        List<ParkingSpot> lot = parkingLot.getParkingSpots();

        for (ParkingSpot spot : lot) {
            if (spot.isOccupied()) {
                System.out.printf("Spot Number: %d, Spot Size: %s -> %s%n", spot.getSpotNumber(), spot.getSize(), spot.getVehicle().licensePlate());
            } else {
                System.out.printf("Spot Number: %d, Spot Size: %s -> _%n", spot.getSpotNumber(), spot.getSize());
            }
        }
    }
}
