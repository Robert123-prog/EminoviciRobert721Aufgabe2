package controller;

import model.Product;
import model.Charakter;
import repository.IRepository;


import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private IRepository<Charakter> charakterRepo;

    private IRepository<Product> productRepo;

    public Controller(IRepository<Charakter> charakterRepo, IRepository<Product> productRepo) {
        this.charakterRepo = charakterRepo;
        this.productRepo = productRepo;
    }


    public Integer getNewCharakterID() {
        if (charakterRepo.getKeys().isEmpty()){
            return 1;
        }else {
            Set<Integer> keys = charakterRepo.getKeys();
            return keys.stream().max(Integer::compare).get() + 1;
        }
    }

    public Integer getNewProductID() {
        if (productRepo.getKeys().isEmpty()){
            return 1;
        }else {
            Set<Integer> keys = productRepo.getKeys();
            return keys.stream().max(Integer::compare).get() + 1;
        }
    }

    public void createCharakter(String name, String herkunftsort, List<Product> products){
        Charakter charakter = new Charakter(getNewCharakterID(), name, herkunftsort, products);
        charakterRepo.create(charakter);
    }

    public Charakter readCharakter(Integer ID){
        return charakterRepo.read(ID);
    }

    public void updateCharakter(Integer ID, String newHerkunftsort){
        Charakter charakter = readCharakter(ID);
        charakter.setName(newHerkunftsort);
        charakterRepo.update(ID, charakter);
    }

    public void deleteCharakter(Integer ID){
        charakterRepo.delete(ID);
    }

    public void createProduct(String name, Integer price, String herkunftsregion){
        Product product = new Product(getNewProductID(), name, price, herkunftsregion);
        productRepo.create(product);
    }

    public Product readProduct(Integer ID){
        return productRepo.read(ID);
    }

    public void updateProduct(Integer ID, Integer newPrice){
        Product product = readProduct(ID);
        product.setPrice(newPrice);
        productRepo.update(ID, product);
    }

    public void deleteProduct(Integer ID){
        productRepo.delete(ID);

    }

    public List<Charakter> getCharakterRepo() {
        return charakterRepo.getAll();
    }

    public List<Product> getProductRepo() {
        return productRepo.getAll();
    }

    public List<Charakter> filterByOrt(String ort){
        List<Charakter> charakters = charakterRepo.getAll().stream()
                .filter(charakter -> charakter.getHerkunftsort().equalsIgnoreCase(ort))
                .toList();
        return charakters;
    }

    public List<Charakter> filterByProductHerkunft(String herkunft){
        List<Charakter> charakters = charakterRepo.getAll().stream()
                .filter(charakter -> charakter.getProducts().stream().anyMatch(product -> product.getHerkunftsregion().equalsIgnoreCase(herkunft)))
                .toList();
        return charakters;
    }
//
//    public List<Commodity> filterByCategory(String category){
//        List<Commodity> commodities = commodityRepo.getAll().stream()
//                .filter(commodity -> commodity.getCategory().equalsIgnoreCase(category))
//                .toList();
//        return commodities;
//    }
//
//    public List<Charakter> filterCustomersByChosenCategory(String category){
//        List<Customer> customers = customerRepo.getAll().stream()
//                .filter(customer -> customer.getCommodities().stream().anyMatch(commodity -> commodity.getCategory().equalsIgnoreCase(category)))
//                .collect(Collectors.toList());
//        return customers;
//    }
//
//    public List<Commodity> sortCustomerCommodities(Integer customerID, String sortingOrder){
//        Customer customer = readCustomer(customerID);
//
//        if (sortingOrder.equalsIgnoreCase("asc")){
//            return customer.getCommodities().stream()
//                    .sorted(Comparator.comparing(Commodity::getPrice))
//                    .toList();
//        } else if (sortingOrder.equalsIgnoreCase("desc")) {
//            return customer.getCommodities().stream()
//                    .sorted(Comparator.comparing(Commodity::getPrice).reversed())
//                    .toList();
//        }else {
//            throw new RuntimeException("Invalid sorting order");
//        }
//
//    }
}
