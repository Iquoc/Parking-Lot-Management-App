package src.models;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    
    private List<ParkingSpot> parkingSpots = new ArrayList<>();

    public void addParkingSpot(ParkingSpot spot) {

        parkingSpots.add(spot);
    }

    public List<ParkingSpot> getAvailableSpots() {
        List<ParkingSpot> availableSpots = new ArrayList<>();
        
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isOccupied()) {
                // add the available spot to the list
                availableSpots.add(spot);
            }
        }
        // return a list of available parking spots
        return availableSpots;
    }
}
