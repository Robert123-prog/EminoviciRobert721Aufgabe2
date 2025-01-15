package view;

import controller.Controller;
import model.Product;
import model.Charakter;
import repository.IRepository;
import repository.InMemoryRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UI {
    private Scanner scanner = new Scanner(System.in);
    private Controller controller;

    public UI() {
        IRepository<Charakter> customerRepo = createInMemoryCharakterRepo();
        IRepository<Product> commodityRepo = createInMemoryProductRepo();

        this.controller = new Controller(customerRepo, commodityRepo);
        mainMenu();
    }

    public void createInMemoryRepo(){
        IRepository<Charakter> customerRepo = createInMemoryCharakterRepo();
        IRepository<Product> productIRepository = createInMemoryProductRepo();

    }

    public IRepository<Charakter> createInMemoryCharakterRepo(){
        IRepository<Charakter> charakterIRepository = new InMemoryRepository<>();

        Charakter charakter1 = new Charakter(1, "Robert", "Middlearth", Arrays.asList(new Product(1, "Bread", 1000, "Earth"), new Product(3, "Computer", 12000, "Earth")));
        charakterIRepository.create(charakter1);

        Charakter charakter2 = new Charakter(2, "Daria", "Hell", Arrays.asList(new Product(1, "Mana", 20000, "Heaven"), new Product(2, "Dark Mana", 9000, "Hell")));
        charakterIRepository.create(charakter2);

        Charakter charakter3 = new Charakter(3, "Marcel", "Earth" , Arrays.asList(new Product(2, "Horse", 300, "Earth"), new Product(3, "Sword", 1234, "Middleearth")));
        charakterIRepository.create(charakter3);

        return charakterIRepository;
    }

    public IRepository<Product> createInMemoryProductRepo(){
        IRepository<Product> productIRepository = new InMemoryRepository<>();
        Product commodity1 = new Product(3, "Computer", 12000, "Earth");
        productIRepository.create(commodity1);

        Product commodity2 = new Product(2, "Dark Mana", 9000, "Hell");
        productIRepository.create(commodity2);

        Product commodity3 = new Product(3, "Sword", 1234, "Middleearth");
        productIRepository.create(commodity3);

        return productIRepository;
    }

    public void mainMenu(){
        System.out.println("1. Product Menu");
        System.out.println("2. Charakter Menu");

        System.out.println("Choice: ");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                commodityMenu();
                break;
            case 2:
                customerMenu();
                break;
        }

    }

    public void commodityMenu(){
        System.out.println("1. Create");
        System.out.println("2. Read");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        System.out.println("5. Read all");
        System.out.println("6. Filter by category");

        System.out.println("Choice: ");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                createProduct();
                break;
            case 2:
                readProduct();
                break;
            case 3:
                updateProduct();
                break;
            case 4:
                deleteProduct();
                break;
            case 5:
                readAllCommodities();
                break;
//            case 6:
//                filterByCategory();
//                break;
            default:
                throw new RuntimeException("Invalid Option!");
        }
    }

    public void createProduct(){
        System.out.println("Complete the following fields: ");

        System.out.println("Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.println("Price: ");
        Integer price = scanner.nextInt();
        scanner.nextLine();


        System.out.println("Herkunftsregion: ");
        String herkunftsregion = scanner.nextLine();


        controller.createProduct(name, price, herkunftsregion);
        commodityMenu();
    }

    public void readProduct(){
        System.out.println("Enter the Product ID: ");
        Integer ID = scanner.nextInt();
        scanner.nextLine();

        System.out.println(controller.readProduct(ID).toString());
        commodityMenu();
    }

    public void updateProduct(){
        System.out.println("Enter the Product ID: ");
        Integer ID = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the new Verleihungspreis: ");
        Integer preis = scanner.nextInt();
        scanner.nextLine();

        controller.updateProduct(ID, preis);
        commodityMenu();
    }

    public void deleteProduct(){
        System.out.println("Enter the Product ID: ");
        Integer ID = scanner.nextInt();
        scanner.nextLine();

        controller.deleteProduct(ID);
        commodityMenu();
    }

    public void readAllCommodities(){
        controller.getProductRepo().forEach(Product -> System.out.println(Product.toString()));
        commodityMenu();
    }


    public void customerMenu(){
        System.out.println("1. Create");
        System.out.println("2. Read");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        System.out.println("5. Read all");
        System.out.println("6. Filter by chosen ort");
        System.out.println("7. Filter by Herkunft");
        System.out.println("8. Sort by Product price");


        System.out.println("Choice: ");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                createCharakter();
                break;
            case 2:
                readCharakter();
                break;
            case 3:
                updateCharakter();
                break;
            case 4:
                deleteCharakter();
                break;
            case 5:
                readAllCharakter();
                break;
            case 6:
                filterCustomerByChosenOrt();
                break;
            case 7:
                filterByProductHerkunft();
                break;
            case 8:
                sortClientProducts();
                break;
            default:
                throw new RuntimeException("Invalid Option!");
        }

    }

    public void createCharakter() {
        System.out.println("Complete the following fields: ");

        System.out.println("Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();


        System.out.println("Herkunftsort: ");
        scanner.nextLine();
        String herkunftsort = scanner.nextLine();

        if (!controller.getProductRepo().isEmpty()) {

            System.out.println("How many products do you want? ");
            Integer numberOfProducts = scanner.nextInt();
            scanner.nextLine();

            List<Integer> desiredProductsIds = new ArrayList<>();

            while (numberOfProducts > 0) {
                controller.getProductRepo().forEach(Product -> System.out.println(Product.toString()));

                System.out.println("Enter the ID of the desired product: ");
                Integer desiredId = scanner.nextInt();
                scanner.nextLine();

                desiredProductsIds.add(desiredId);
                numberOfProducts--;
            }

            List<Product> desiredProducts = new ArrayList<>();

            for (Integer Id : desiredProductsIds) {
                Product commodity = controller.readProduct(Id);
                desiredProducts.add(commodity);
            }

            controller.createCharakter(name, herkunftsort, desiredProducts);
            customerMenu();

        }else {
            List<Product> desiredProducts = null;

            controller.createCharakter(name, herkunftsort, desiredProducts);
            customerMenu();
        }


    }

    public void readCharakter(){
        System.out.println("Enter the Charakter ID: ");
        Integer ID = scanner.nextInt();
        scanner.nextLine();

        System.out.println(controller.readCharakter(ID).toString());
        customerMenu();
    }

    public void updateCharakter(){
        System.out.println("Enter the Charakter ID: ");
        Integer ID = scanner.nextInt();


        System.out.println("Enter the new Herkuftsort: ");
        scanner.nextLine();
        String herkunftsort = scanner.nextLine();

        controller.updateCharakter(ID, herkunftsort);
        customerMenu();
    }

    public void deleteCharakter(){
        System.out.println("Enter the Charakter ID: ");
        Integer ID = scanner.nextInt();
        scanner.nextLine();

        controller.deleteCharakter(ID);
        customerMenu();
    }

    public void readAllCharakter(){
        controller.getCharakterRepo().forEach(Charakter -> System.out.println(Charakter.toString()));
        customerMenu();
    }

    public void filterCustomerByChosenOrt(){
        System.out.println("Enter ort: ");
        scanner.nextLine();
        String ort = scanner.nextLine();

        controller.filterByOrt(ort).forEach(charakter -> System.out.println(charakter.toString()));
    }

    public void filterByProductHerkunft(){
        System.out.println("Enter Herkunft: ");
        scanner.nextLine();
        String herkunft = scanner.nextLine();


        controller.filterByProductHerkunft(herkunft).forEach(charakter -> System.out.println(charakter.toString()));
    }

    public void sortClientProducts(){
        System.out.println("Enter customer ID");
        Integer ID = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter sorting order: ");
        String sortingOrder = scanner.nextLine();

        controller.sortClientProducts(ID, sortingOrder).forEach(Product -> System.out.println(Product.toString()));
    }

    public static void main(String[] args) {
        new UI();
    }
}
