package Elevator_design;

public class Request {
    private final Integer source;
    private final Integer Destination;

    public Request(Integer source, Integer destination) {
        this.source = source;
        Destination = destination;
    }

    public Integer getSource() {
        return source;
    }

    public Integer getDestination() {
        return Destination;
    }
}
