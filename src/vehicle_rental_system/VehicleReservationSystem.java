package vehicle_rental_system;

import java.time.LocalDate;

public class VehicleReservationSystem {
    public static void main(String[] args) {
        VehicleInventory inventory = new VehicleInventory();

        // Adding vehicles
        inventory.addVehicle(new Vehicle("1", "Car", "Toyota Camry", 50.0));
        inventory.addVehicle(new Vehicle("2", "Car", "Honda Civic", 45.0));
        inventory.addVehicle(new Vehicle("3", "Bike", "Yamaha MT-15", 20.0));

        // Adding reservations
        inventory.addReservation(new Reservation(
                new Vehicle("1", "Car", "Toyota Camry", 50.0),
                LocalDate.of(2025, 1, 15),
                LocalDate.of(2025, 1, 18)
        ));
        inventory.addReservation(new Reservation(
                new Vehicle("2", "Car", "Honda Civic", 45.0),
                LocalDate.of(2025, 1, 10),
                LocalDate.of(2025, 1, 14)
        ));

        // Get available vehicles
        LocalDate startDate = LocalDate.of(2025, 1, 19);
        LocalDate endDate = LocalDate.of(2025, 1, 21);

        System.out.println("Available vehicles from " + startDate + " to " + endDate + ":");
        for (Vehicle vehicle : inventory.getAvailableVehicles(startDate, endDate)) {
            System.out.println(vehicle);
            double cost = inventory.calculateTotalCost(vehicle.getId(), startDate, endDate);
            System.out.println("Estimated cost: $" + cost);
        }

        // Print reservations for a specific vehicle
        System.out.println("\nReservations for Vehicle ID 1:");
        for (Reservation reservation : inventory.getReservations("1")) {
            System.out.println(reservation);
        }
    }
}

