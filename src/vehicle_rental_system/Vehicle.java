package vehicle_rental_system;

class Vehicle {
    private String id;
    private String type;  // e.g., Car, Bike
    private String model;
    private double pricePerDay;

    public Vehicle(String id, String type, String model, double pricePerDay) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.pricePerDay = pricePerDay;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}
