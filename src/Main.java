import it.epicode.classes.Customer;
import it.epicode.classes.Order;
import it.epicode.classes.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("Javascript to Top", "Books", 5000.0);
        Product product2 = new Product("Java Explained", "Books", 3000.0);
        Product product3 = new Product("Java Advanced", "Books", 80.0);
        Product product4 = new Product("Gel", "Baby", 30.0);
        Product product5 = new Product("Hat", "Baby", 20.0);
        Product product6 = new Product("Skirt", "Baby", 50.0);
        Product product7 = new Product("Superhero", "Boys", 50.0);
        Product product8 = new Product("Ball", "Boys", 10.0);

        Customer customer1 = new Customer("Stefano", 5);
        Customer customer2 = new Customer("Luca", 2);
        Customer customer3 = new Customer("Antonio", 2);

        List<Product> orderList1 = new ArrayList<>();
        orderList1.add(product1);
        orderList1.add(product2);
        orderList1.add(product8);

        List<Product> orderList2 = new ArrayList<>();
        orderList2.add(product4);
        orderList2.add(product5);
        orderList2.add(product1);

        List<Product> orderList3 = new ArrayList<>();
        orderList3.add(product6);
        orderList3.add(product7);
        orderList3.add(product3);

        Order order1 = new Order("Completed", LocalDate.parse("2021-05-28"), LocalDate.parse("2021-05-30"), orderList1, customer1);
        Order order2 = new Order("Completed", LocalDate.parse("2021-02-28"), LocalDate.parse("2021-03-31"), orderList2, customer2);
        Order order3 = new Order("Completed", LocalDate.parse("2021-02-28"), LocalDate.parse("2021-03-31"), orderList3, customer3);


        //Esercizio 1
        System.out.println("ES_1");
        Product[] allProductsArray = {product1, product2, product3, product4, product5, product6, product7, product8};

        List<Product> allProducts = new ArrayList<>(Arrays.asList(allProductsArray));

        Predicate<Product> moreThanHundred = product -> product.getPrice() > 100;
        Predicate<Product> isBook = product -> product.getCategory().equals("Books");

        allProducts.stream()
                .filter(moreThanHundred.and(isBook))
                //.map(Product::getName) //Oppure così .map(product -> product.getName())
                .forEach(System.out::println);


        //Esercizio 2
        System.out.println(" ");
        System.out.println("ES_2");

        allProducts.stream()
                .filter(product -> product.getCategory().equals("Baby"))
                .forEach(System.out::println);

        //Esercizio 3
        System.out.println(" ");
        System.out.println("ES_3");

        allProducts.stream()
                .filter(product -> product.getCategory().equals("Boys"))
                .peek(product -> product.setPrice(product.getPrice() * 0.9))
                //Oppure il map al posto di peek ma il map vuole un return
//                .map(product -> {
//                    product.setPrice(product.getPrice() * 0.9);
//                    return product;
//                })
                .forEach(System.out::println);

        //Esercizio 4
        System.out.println(" ");
        System.out.println("ES_4");

        Order[] allOrdersArray = {order1, order2, order3};
        List<Order> allOrders = new ArrayList<>(Arrays.asList(allOrdersArray));

        Predicate<Order> isAfterFeb = order -> order.getOrderDate().isAfter(LocalDate.parse("2021-02-01"));
        Predicate<Order> isBeforeApril = order -> order.getOrderDate().isBefore(LocalDate.parse("2021-04-01"));

        allOrders.stream()
                .filter(order -> order.getCustomer().getTier().equals(2))
                .filter(isAfterFeb.and(isBeforeApril))
                .forEach(order -> {
                            System.out.println("Ordine di: " + order.getCustomer().getName());
                            System.out.println(
                                    order.getProducts()
                                            .stream()
                                            .map(product -> product.getName() + " " + product.getPrice() + "€")
                                            .toList()
                            );
                        }
                );

    }
}