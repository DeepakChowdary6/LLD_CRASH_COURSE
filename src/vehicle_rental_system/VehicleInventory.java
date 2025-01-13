package vehicle_rental_system;

import java.time.LocalDate;
import java.util.*;

class VehicleInventory {
    private List<Vehicle> vehicles;
    private Map<String, List<Reservation>> reservations; // Key: Vehicle ID, Value: List of Reservations

    public VehicleInventory() {
        this.vehicles = new ArrayList<>();
        this.reservations = new HashMap<>();
    }

    public void addVehicle(Vehicle vehicle) {
        if (!vehicles.stream().anyMatch(v -> v.getId().equals(vehicle.getId()))) {
            vehicles.add(vehicle);
            reservations.put(vehicle.getId(), new ArrayList<>());
        }
    }

    public void addReservation(Reservation reservation) {
        List<Reservation> vehicleReservations = reservations.get(reservation.getVehicle().getId());
        if (vehicleReservations != null) {
            vehicleReservations.add(reservation);
        }
    }

    public List<Vehicle> getAvailableVehicles(LocalDate startDate, LocalDate endDate) {
        List<Vehicle> availableVehicles = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            List<Reservation> vehicleReservations = reservations.get(vehicle.getId());
            boolean isAvailable = true;

            if (vehicleReservations != null) {
                for (Reservation reservation : vehicleReservations) {
                    if (reservation.overlaps(startDate, endDate)) {
                        isAvailable = false;
                        break;
                    }
                }
            }

            if (isAvailable) {
                availableVehicles.add(vehicle);
            }
        }

        return availableVehicles;
    }

    public double calculateTotalCost(String vehicleId, LocalDate startDate, LocalDate endDate) {
        Vehicle vehicle = vehicles.stream()
                .filter(v -> v.getId().equals(vehicleId))
                .findFirst()
                .orElse(null);

        if (vehicle != null) {
            long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
            return days * vehicle.getPricePerDay();
        }

        return 0.0;
    }

    public List<Reservation> getReservations(String vehicleId) {
        return reservations.get(vehicleId);
    }
}
