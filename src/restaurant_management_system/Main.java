package restaurant_management_system;

import restaurant_management_system.dto.Category;
import restaurant_management_system.service.AdminService;
import restaurant_management_system.service.CustomerService;
import restaurant_management_system.service.InMemoryService;
import restaurant_management_system.service.KitchenService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AdminService adminService=new AdminService();
        CustomerService customerService=new CustomerService();
        KitchenService kitchenService=new KitchenService();
        InMemoryService inMemoryService=new InMemoryService();
        while(true){
            System.out.println("\n--- Restaurant Management System ---");
            System.out.println("Select role:");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Kitchen Staff");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            Scanner sc=new Scanner(System.in);
            int role= sc.nextInt();
            switch (role){
                 case 1->adminMenu(sc,adminService);

                 case 2-> customerMenu(sc,customerService);
                 case 3-> kitchenMenu(sc,kitchenService);
                 case 4-> {System.out.println("Exiting system. Goodbye!"); return;}

                default -> {
                     System.out.println("Invalid Option");
                }

            }


        }

    }
    public static  void adminMenu(Scanner sc,AdminService adminService){





          //Allow restaurant admin to manage tables in the restaurant as in number of tables.
        // ● Allow the restaurant admin to define the number of tables.
        //● Allow restaurant admin to add, update, or remove items from the menu. Each menu item
        //should have attributes such as name, price, veg / non-veg and category (starter, main
        //course, dessert).
        while (true) {
            System.out.println("<------------Welcome to Admin menu-------------->");

            System.out.println("1. Add Items to menu");
            System.out.println("2. define tables");
            System.out.println("3. remove table");
            System.out.println("4. Add table");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice=sc.nextInt();
            switch (choice){
                case 1->{
                    System.out.println("Enter cost of item");
                    double cost= sc.nextDouble();
                    System.out.println("Enter name of the item");
                    String name= sc.next();
                    System.out.println("is item veg ");
                    boolean flag=sc.nextBoolean();
                    System.out.println("enter the category ");
                    Category category=Category.valueOf(Category.class,sc.next());
                    adminService.addItemstoMenu(cost,name,flag,category);
                }
                case 2->{
                    System.out.println("Define no of tables");
                    int tables= sc.nextInt();
                    adminService.defineTables(tables);}
                case 3-> {
                    System.out.println("Enter table id to be removed");
                    int tableId= sc.nextInt();
                    adminService.removeTable(tableId);;
                }
                case 4-> {

                    System.out.println("Enter the table no to be added");
                    int table_no= sc.nextInt();
                    adminService.addTable(table_no);
                }

                case 5->{
                    System.out.println("Exiting Admin menu. Good bye");
                    return;
                }
                default -> {
                    System.out.println("Invalid Option");
                }
            }

        }

    }
    public static void kitchenMenu(Scanner sc,KitchenService kitchenService){
// Provide a system for kitchen staff to view incoming orders, mark them as prepared, and
//notify wait-staff when orders are ready for serving.



        while (true) {
            System.out.println("<------------Welcome to Kitchen menu-------------->");

            System.out.println("1. View Incoming Orders");
            System.out.println("2. View Items in order");
            System.out.println("3. serve the order");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int choice=sc.nextInt();
            switch (choice){
                case 1->{
                    kitchenService.viewIncomingOrders();}
                case 2->{
                    System.out.println("Enter order id to show items in it");
                    int orderId= sc.nextInt();
                    kitchenService.viewItemsInOrder(orderId);}
                case 3-> {
                    System.out.println("Enter order id to be served");
                    int orderId= sc.nextInt();
                    kitchenService.serveTheOrder(orderId);
                }

                case 4->{
                    System.out.println("Exiting Customer menu. Good bye");
                    return;
                }
                default -> {
                    System.out.println("Invalid Option");
                }
            }

        }
        //
    }
    public static void customerMenu(Scanner sc,CustomerService customerService){
// Allow customers to browse the menu and allow filtering on factors (veg / non-veg,
//category), add items to their order, specify quantities, and place the order.
        //Allow multiple orders for the same table (AddItems even after 1 order was placed)
//        Calculate the total bill for each order, including taxes and any additional charges.
//                Provide options for customers to pay the bill via various payment options, such as cash,
//        credit/debit card, or online payment (card payment levy additional charges).
//        Bonus Functionality:
        while (true) {
            System.out.println("<------------Welcome to Customer menu-------------->");

            System.out.println("1. View Menu by veg/non-veg");
            System.out.println("2. View Menu by itemtype and category");
            System.out.println("3. Place Order");
            System.out.println("4. add items to existing order");
            System.out.println("5. Calculate bill");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice=sc.nextInt();
            switch (choice){
                case 1->{
                    System.out.println("Enter type \n1.veg \n2.non-veg \n3.any");
                    int type= sc.nextInt();
                customerService.viewMenuItems(type);}
                case 2->{
                    System.out.println("Enter the type and category");
                    System.out.println("Enter type \n1.veg \n2.non-veg \n3.any");
                    int itemType= sc.nextInt();
                    System.out.println("Enter the category \n STARTER \n MAIN \n COURSE \n DESSERT");
                    Category category=Category.valueOf(Category.class,sc.next());
                    customerService.viewMenuItemsByTypeAndCategory(itemType,category);}
                case 3->{
                    System.out.println("Enter the table no ");
                    int tableNo=sc.nextInt();
                    customerService.placeOrder(tableNo);}
                case 4-> {
                    System.out.println("enter the order id to add items");
                    int orderId= sc.nextInt();
                    System.out.println("enter the menu Item id");
                    int menuItemid= sc.nextInt();
                    System.out.println("enter the quantity");
                    int quantity= sc.nextInt();
                    customerService.addItemstoExistingorder(orderId,menuItemid,quantity);
                }
                case 5->{
                    System.out.println("enter the order id to calculate the bill");
                    int orderId= sc.nextInt();
            customerService.CalculateBill(orderId);}
                case 6->{
                    System.out.println("Exiting Customer menu. Good bye");
                    return;
                }
                default -> {
                    System.out.println("Invalid Option");
                }
            }

        }
    }

}
