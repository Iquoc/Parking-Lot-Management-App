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
        assertEquals("ABC123", vehicle.licensePlate());
        assertEquals(VehicleSize.LARGE, vehicle.size());
    }

    @Test
    public void testlicensePlate() {
        assertEquals("ABC123", vehicle.licensePlate());
    }

    @Test
    public void testsize() {
        assertEquals(VehicleSize.LARGE, vehicle.size());
    }

    @Test
    public void testVehicleWithSmallSize() {
        Vehicle smallVehicle = new Vehicle("DEF456", VehicleSize.SMALL);
        assertEquals(VehicleSize.SMALL, smallVehicle.size());
    }

}
