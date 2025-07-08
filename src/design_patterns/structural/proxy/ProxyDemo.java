package design_patterns.structural.proxy;
/*
* Proxy design pattern intent according to GoF is: Provide a surrogate or placeholder for another object to control access to it.
* The definition itself is very clear and proxy design pattern is used when we want to provide controlled access of a functionality.
*/
interface image{
    void display();
}
class RealImage implements image{
    String imageName;
    public RealImage(String imageName){
        this.imageName=imageName;
        loadImageFromDisk();
    }
   public void loadImageFromDisk() {
        System.out.println("Loading image: " + imageName);
    }
    public void display(){
        System.out.println("Displaying image "+imageName);
    }
}

class ProxyImage implements image{
    String imageName;
    RealImage realImage;
    public ProxyImage(String imageName){
        this.imageName=imageName;
    }
    public void display(){
        if(realImage==null){
            realImage=new RealImage(imageName);
        }
        realImage.display();
    }
    //This code demonstrates how the Proxy Pattern efficiently manages the loading and displaying of images by introducing a proxy that controls access to the real image object,
    // providing additional functionality such as lazy loading.
}
public class ProxyDemo{

    public static void main(String[] args) {

        image image=new ProxyImage("image1.jpg");
        image.display();
        image.display();
    }

}
