package src.models;
public enum VehicleSize{
    SMALL (1), 
    LARGE(2), 
    OVERSIZE(3);

    private final int rank;

    VehicleSize(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}