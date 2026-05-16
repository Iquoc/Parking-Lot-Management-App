package test.models;

import src.models.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class VehicleSizeTest {

    @Test
    public void testSmallRank() {
        assertEquals(1, VehicleSize.SMALL.getRank());
    }

    @Test
    public void testLargeRank() {
        assertEquals(2, VehicleSize.LARGE.getRank());
    }

    @Test
    public void testOversizeRank() {
        assertEquals(3, VehicleSize.OVERSIZE.getRank());
    }

    @Test
    public void testRankOrdering() {
        assertTrue(VehicleSize.SMALL.getRank() < VehicleSize.LARGE.getRank());
        assertTrue(VehicleSize.LARGE.getRank() < VehicleSize.OVERSIZE.getRank());
    }

    @Test
    public void testEnumValues() {
        VehicleSize[] sizes = VehicleSize.values();
        assertEquals(3, sizes.length);
    }

}
