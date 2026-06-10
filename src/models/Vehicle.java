package src.models;

public record Vehicle(String licensePlate, VehicleSize size) {
    // Java 17 features https://docs.oracle.com/en/java/javase/17/language/records.html

    public Vehicle {
        if (licensePlate == null || licensePlate.isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be null or empty.");
        }
        if (size == null) {
            throw new IllegalArgumentException("Vehicle size cannot be null.");
        }
    }
}