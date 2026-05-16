public class ParkingSpot {

    private int spotNumber;
    private boolean isOccupied;
    private Vehicle vehicle;
    private VehicleSize size;

    public ParkingSpot(int spotNumber, VehicleSize size) {
        this.spotNumber = spotNumber;
        this.size = size;
        this.isOccupied = false;
        this.vehicle = null;
    }
    
    // GETTERS AND SETTERS

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleSize getSize() {
        return size;
    }

    // returns true if the parking spot is occupied, false otherwise
    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    // OTHER METHODS

    // add a vehicle to the parking spot
    public void addVehicle(Vehicle vehicle) {

        // check if the parking spot is already occupied
        if (isOccupied) {
            System.out.println("Parking spot " + spotNumber + " is already occupied.");
            return;
        }

        // check if the vehicle size is compatible with the parking spot size
        if (vehicle.getSize().getRank() > size.getRank()) {
            System.out.println("Vehicle size is too large for parking spot " + spotNumber + ".");
            return;
        }

        // park the vehicle in the spot
        this.vehicle = vehicle;
        this.isOccupied = true;
        System.out.println("Vehicle with license plate " + vehicle.getLicensePlate() + " parked in spot " + spotNumber + ".");
    }

    // remove a vehicle from the parking spot
    public void removeVehicle() {

        // check if the parking spot is already empty
        if (!isOccupied) {
            System.out.println("Parking spot " + spotNumber + " is already empty.");
            return;
        }

        // remove the vehicle from the spot
        System.out.println("Vehicle with license plate " + vehicle.getLicensePlate() + " removed from spot " + spotNumber + ".");
        this.vehicle = null;
        this.isOccupied = false;
    }
}