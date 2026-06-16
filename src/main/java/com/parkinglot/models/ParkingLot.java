package com.parkinglot.models;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ParkingLot {
    
    private int parkingLotSize = 0;
    private List<ParkingSpot> parkingSpots = new ArrayList<>();

    // getter
    public int getParkingSize() {
        return parkingLotSize;
    }

    // add a parking spot to the parking lot
    public void addParkingSpot(ParkingSpot spot) {
        parkingSpots.add(spot);
        parkingLotSize++;
    }

    // return a list of available parking spots in the parking lot
    public List<ParkingSpot> getAvailableSpots() {
        // List<ParkingSpot> availableSpots = new ArrayList<>();
        
        // for (ParkingSpot spot : parkingSpots) {
        //     if (!spot.isOccupied()) {
        //         // add the available spot to the list
        //         availableSpots.add(spot);
        //     }
        // }

        // return availableSpots;

        return parkingSpots.stream().filter(Predicate.not(ParkingSpot::isOccupied)).toList(); // toList() is unmodifiable
    }

    public List<ParkingSpot> getParkingSpots() {
        parkingSpots.sort(Comparator.comparingInt(ParkingSpot::getSpotNumber));
        return List.copyOf(parkingSpots); // immutable view instead of exposing the internal list
    }
}
