package test.models;
import org.junit.Test;
import src.models.*;

import org.junit.Before;
import static org.junit.Assert.*;
import java.util.List;

public class ParkingLotTest {

    private ParkingLot parkingLot;

    @Before
    public void setUp() {
        parkingLot = new ParkingLot();
    }

    @Test
    public void testParkingLotCreation() {
        assertNotNull(parkingLot);
    }

    @Test
    public void testAddParkingSpot() {
        ParkingSpot spot = new ParkingSpot(1, VehicleSize.LARGE);
        parkingLot.addParkingSpot(spot);
        
        List<ParkingSpot> availableSpots = parkingLot.getAvailableSpots();
        assertEquals(1, availableSpots.size());
    }

    @Test
    public void testAddMultipleParkingSpots() {
        for (int i = 1; i <= 5; i++) {
            ParkingSpot spot = new ParkingSpot(i, VehicleSize.LARGE);
            parkingLot.addParkingSpot(spot);
        }
        
        List<ParkingSpot> availableSpots = parkingLot.getAvailableSpots();
        assertEquals(5, availableSpots.size());
    }

    @Test
    public void testGetAvailableSpots() {
        ParkingSpot spot1 = new ParkingSpot(1, VehicleSize.LARGE);
        ParkingSpot spot2 = new ParkingSpot(2, VehicleSize.SMALL);
        
        parkingLot.addParkingSpot(spot1);
        parkingLot.addParkingSpot(spot2);
        
        List<ParkingSpot> availableSpots = parkingLot.getAvailableSpots();
        assertEquals(2, availableSpots.size());
    }

    @Test
    public void testGetAvailableSpotsAfterOccupying() {
        ParkingSpot spot1 = new ParkingSpot(1, VehicleSize.LARGE);
        ParkingSpot spot2 = new ParkingSpot(2, VehicleSize.SMALL);
        
        parkingLot.addParkingSpot(spot1);
        parkingLot.addParkingSpot(spot2);
        
        // Occupy first spot
        Vehicle vehicle = new Vehicle("ABC123", VehicleSize.LARGE);
        spot1.addVehicle(vehicle);
        
        List<ParkingSpot> availableSpots = parkingLot.getAvailableSpots();
        assertEquals(1, availableSpots.size());
    }

    @Test
    public void testEmptyParkingLot() {
        List<ParkingSpot> availableSpots = parkingLot.getAvailableSpots();
        assertEquals(0, availableSpots.size());
    }

}
