package design_patterns.behavioural.state;

// State interface
interface TrafficLightState {
    void change(TrafficLight light);
    String getColor();
}

// Concrete states
class RedLightState implements TrafficLightState {
    @Override
    public void change(TrafficLight light) {
        light.setCurrentState(new GreenLightState());
    }

    @Override
    public String getColor() {
        return "Red";
    }
}

class GreenLightState implements TrafficLightState {
    @Override
    public void change(TrafficLight light) {
        light.setCurrentState(new YellowLightState());
    }

    @Override
    public String getColor() {
        return "Green";
    }
}

class YellowLightState implements TrafficLightState {
    @Override
    public void change(TrafficLight light) {
        light.setCurrentState(new RedLightState());
    }

    @Override
    public String getColor() {
        return "Yellow";
    }
}

// Context class
class TrafficLight {
    private TrafficLightState currentState;

    public TrafficLight() {
        currentState = new RedLightState();
    }

    public void change() {
        currentState.change(this);
    }

    public String getCurrentColor() {
        return currentState.getColor();
    }

    public void setCurrentState(TrafficLightState state) {
        this.currentState = state;
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();

        for (int i = 0; i < 3; i++) {
            System.out.println("Current Traffic Light: " + trafficLight.getCurrentColor());
            trafficLight.change();
        }
    }
}
