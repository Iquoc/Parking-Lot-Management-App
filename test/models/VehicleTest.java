package test.models;

import src.models.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class VehicleTest {

    private Vehicle vehicle;

    @Before
    public void setUp() {
        vehicle = new Vehicle("ABC123", VehicleSize.LARGE);
    }

    @Test
    public void testVehicleCreation() {
        assertNotNull(vehicle);
        assertEquals("ABC123", vehicle.getLicensePlate());
        assertEquals(VehicleSize.LARGE, vehicle.getSize());
    }

    @Test
    public void testGetLicensePlate() {
        assertEquals("ABC123", vehicle.getLicensePlate());
    }

    @Test
    public void testGetSize() {
        assertEquals(VehicleSize.LARGE, vehicle.getSize());
    }

    @Test
    public void testSetLicensePlate() {
        vehicle.setLicensePlate("XYZ789");
        assertEquals("XYZ789", vehicle.getLicensePlate());
    }

    @Test
    public void testSetSize() {
        vehicle.setSize(VehicleSize.SMALL);
        assertEquals(VehicleSize.SMALL, vehicle.getSize());
    }

    @Test
    public void testVehicleWithSmallSize() {
        Vehicle smallVehicle = new Vehicle("DEF456", VehicleSize.SMALL);
        assertEquals(VehicleSize.SMALL, smallVehicle.getSize());
    }

}
