package vehicle_rental_system;

import java.time.LocalDate;

class Reservation {
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;

    public Reservation(Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean overlaps(LocalDate startDate, LocalDate endDate) {
        return !(this.endDate.isBefore(startDate) || this.startDate.isAfter(endDate));
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "vehicle=" + vehicle +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
