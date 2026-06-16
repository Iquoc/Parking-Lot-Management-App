package com.parkinglot.services;

import com.parkinglot.models.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ParkingLotServiceTest {

    private ParkingLotService service;
    private ParkingLot parkingLot;

    @Before
    public void setUp() {
        parkingLot = new ParkingLot();
        service = new ParkingLotService(parkingLot);
        
        // Add some parking spots
        for (int i = 1; i <= 5; i++) {
            VehicleSize size = (i % 2 == 0) ? VehicleSize.LARGE : VehicleSize.SMALL;
            ParkingSpot spot = new ParkingSpot(i, size);
            parkingLot.addParkingSpot(spot);
        }
    }

    @Test
    public void testParkVehicle() {
        Vehicle vehicle = new Vehicle("ABC123", VehicleSize.SMALL);
        boolean result = service.parkVehicle(vehicle);
        assertTrue(result);
    }

    @Test
    public void testParkMultipleVehicles() {
        Vehicle vehicle1 = new Vehicle("ABC123", VehicleSize.SMALL);
        Vehicle vehicle2 = new Vehicle("XYZ789", VehicleSize.LARGE);
        
        boolean result1 = service.parkVehicle(vehicle1);
        boolean result2 = service.parkVehicle(vehicle2);
        
        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void testParkSizeOptimize() {
        Vehicle smallVehicle1 = new Vehicle("SMALL123", VehicleSize.SMALL);
        Vehicle smallVehicle2 = new Vehicle("SMALL456", VehicleSize.SMALL);
        Vehicle smallVehicle3 = new Vehicle("SMALL789", VehicleSize.SMALL);
        Vehicle smallVehicle4 = new Vehicle("SMALL1011", VehicleSize.SMALL);
        Vehicle largeVehicle = new Vehicle("LARGE123", VehicleSize.LARGE);
        
        service.parkVehicle(smallVehicle1);
        service.parkVehicle(smallVehicle2);
        service.parkVehicle(smallVehicle3);
        boolean resultSmall4 = service.parkVehicle(smallVehicle4);
        boolean resultLarge = service.parkVehicle(largeVehicle);
        
        
        assertTrue(resultSmall4); // should park in the first large spot
        assertTrue(resultLarge);
    }

    @Test
    public void testParkVehicleInFullLot() {
        // Fill all spots
        Vehicle[] vehicles = new Vehicle[5];
        for (int i = 0; i < 5; i++) {
            vehicles[i] = new Vehicle("PLATE" + i, VehicleSize.SMALL);
            service.parkVehicle(vehicles[i]);
        }
        
        // Try to park in full lot
        Vehicle extraVehicle = new Vehicle("EXTRA", VehicleSize.SMALL);
        boolean result = service.parkVehicle(extraVehicle);
        assertFalse(result);
    }

    @Test
    public void testRemoveVehicle() {
        Vehicle vehicle = new Vehicle("ABC123", VehicleSize.SMALL);
        service.parkVehicle(vehicle);
        
        boolean result = service.removeVehicle("ABC123");
        assertTrue(result);
    }

    @Test
    public void testRemoveNonexistentVehicle() {
        boolean result = service.removeVehicle("NONEXISTENT");
        assertFalse(result);
    }

    @Test
    public void testFindAvailableSpotsAfterParking() {
        Vehicle vehicle = new Vehicle("ABC123", VehicleSize.SMALL);
        service.parkVehicle(vehicle);
        
        // After parking one vehicle, should have 4 spots available
        java.util.List<ParkingSpot> availableSpots = parkingLot.getAvailableSpots();
        assertEquals(4, availableSpots.size());
    }

    @Test
    public void testDisplayVehicleStatusBeforeParking() {
        Vehicle vehicle = new Vehicle("ABC123", VehicleSize.SMALL);

        // This should not throw an exception
        service.displayVehicleStatus(vehicle);
    }

    @Test
    public void testDisplayVehicleStatusAfterAdd() {
        Vehicle vehicle = new Vehicle("DEF456", VehicleSize.SMALL);
        service.parkVehicle(vehicle);

        // This should not throw an exception
        service.displayVehicleStatus(vehicle);
    }

    @Test
    public void testDisplayVehicleStatusAfterRemove() {
        Vehicle vehicle = new Vehicle("GHI789", VehicleSize.SMALL);
        service.parkVehicle(vehicle);
        
        service.removeVehicle(vehicle.licensePlate());

        // This should not throw an exception
        service.displayVehicleStatus(vehicle);
    }

    @Test
    public void testDisplayParkingLotStatus() {
        Vehicle vehicle1 = new Vehicle("ABC123", VehicleSize.SMALL);
        Vehicle vehicle2 = new Vehicle("XYZ789", VehicleSize.LARGE);
        
        service.parkVehicle(vehicle1);
        service.parkVehicle(vehicle2);

        // This should not throw an exception
        service.displayParkingLotStatus();
    }

    @Test
    public void testVehicleSizeCompatibility() {
        // Create a large vehicle
        Vehicle largeVehicle = new Vehicle("LARGE123", VehicleSize.LARGE);
        boolean result = service.parkVehicle(largeVehicle);
        
        // Should park successfully in large spot
        assertTrue(result);
    }

}
