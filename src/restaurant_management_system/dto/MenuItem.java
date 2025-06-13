package restaurant_management_system.dto;



public class MenuItem {
    private static int counter=0;

    private  Integer id;
    private double price;
    private String name;
    private  boolean veg;
    private Category category;

    public MenuItem(double price, String name, boolean veg, Category category) {
        this.id=counter++;
        this.price = price;
        this.name = name;
        this.veg = veg;
        this.category = category;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVeg() {
        return veg;
    }

    public void setVeg(boolean veg) {
        this.veg = veg;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }
}
