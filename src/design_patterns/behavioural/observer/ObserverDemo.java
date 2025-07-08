package design_patterns.behavioural.observer;
import java.util.*;

/**
 * The Observer Pattern is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they're observing.
 * There are two main players in the Observer pattern: the Subject and the Observer.
 * The Subject is the object being observed. It is responsible for notifying the observers when it is changed.
 * The Observer is the object that is doing the observing. It is responsible for reacting to changes in the subject.
 */
  //
interface Observer {
    void update(String weather);
}

class Phone implements Observer {
    private String weather;
    @Override
    public void update(String weather) {
        this.weather = weather;
      display();
    }
    private void display(){
        System.out.println("Phone display : weather updated " + weather);}
}
class Tablet implements Observer {
    private String weather;
    @Override
    public void update(String weather) {
        this.weather = weather;
       display();
    }
    private void display(){
        System.out.println("Tablet display : weather updated " + weather);}
}

interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

class WeatherStation implements Subject {
    private String weather;
    private List<Observer> observers = new ArrayList<>();
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(weather);
        }
    }
    public void setWeather(String weather) {
        this.weather = weather;
        notifyObservers();
    }

}
public class ObserverDemo {

    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        Phone phone = new Phone();
        Tablet tablet = new Tablet();
        weatherStation.registerObserver(phone);
        weatherStation.registerObserver(tablet);
        weatherStation.setWeather("Sunny");
        weatherStation.removeObserver(phone);
        weatherStation.setWeather("Rainy");
    }

}
