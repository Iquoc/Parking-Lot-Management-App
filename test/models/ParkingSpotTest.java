package test.models;

import src.models.*;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ParkingSpotTest {

    private ParkingSpot spot;

    @Before
    public void setUp() {
        spot = new ParkingSpot(1, VehicleSize.LARGE);
    }

    @Test
    public void testParkingSpotCreation() {
        assertNotNull(spot);
        assertEquals(1, spot.getSpotNumber());
        assertEquals(VehicleSize.LARGE, spot.getSize());
        assertFalse(spot.isOccupied());
    }

    @Test
    public void testAddValidVehicle() {
        Vehicle vehicle = new Vehicle("ABC123", VehicleSize.LARGE);
        spot.addVehicle(vehicle);
        assertTrue(spot.isOccupied());
    }

    @Test
    public void testAddVehicleToOccupiedSpot() {
        Vehicle vehicle1 = new Vehicle("ABC123", VehicleSize.LARGE);
        Vehicle vehicle2 = new Vehicle("XYZ789", VehicleSize.SMALL);
        
        spot.addVehicle(vehicle1);
        assertTrue(spot.isOccupied());
        
        // Try adding another vehicle to occupied spot
        spot.addVehicle(vehicle2);
        // Should still be occupied by first vehicle
        assertTrue(spot.isOccupied());
    }

    @Test
    public void testAddOversizeVehicle() {
        ParkingSpot smallSpot = new ParkingSpot(2, VehicleSize.SMALL);
        Vehicle largeVehicle = new Vehicle("BIG123", VehicleSize.LARGE);
        
        smallSpot.addVehicle(largeVehicle);
        // Should remain unoccupied because vehicle is too large
        assertFalse(smallSpot.isOccupied());
    }

    @Test
    public void testRemoveVehicle() {
        Vehicle vehicle = new Vehicle("ABC123", VehicleSize.LARGE);
        spot.addVehicle(vehicle);
        assertTrue(spot.isOccupied());
        
        spot.removeVehicle();
        assertFalse(spot.isOccupied());
    }

    @Test
    public void testRemoveVehicleFromEmptySpot() {
        assertFalse(spot.isOccupied());
        spot.removeVehicle(); // Should handle gracefully
        assertFalse(spot.isOccupied());
    }

    @Test
    public void testGetSpotNumber() {
        assertEquals(1, spot.getSpotNumber());
    }

    @Test
    public void testGetSize() {
        assertEquals(VehicleSize.LARGE, spot.getSize());
    }

}
