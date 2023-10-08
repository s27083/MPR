package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Order;
import org.example.model.Pizza;
import org.example.exceptions.PizzaNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PizzaService {
    private static final Logger logger = LogManager.getLogger(PizzaService.class);
    private List<Order> orderList = new ArrayList<>();
    private List<Pizza> pizzaList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public Order makeOrder(List<Pizza> pizza){

        // oczekujemy tutaj zwrocenia informacji o zamowieniu
        // numer zm pizza cena

        // sprawdamy czy pizze sa dostepne

        // Tworzymy obiekt order
        // Numer zamowienia bedzie bazaowal na rozmiarze orderLsit
        // Dodajemy order do OrderList
        // Zwracamy order

        List<Pizza> aviablePizzas = getAvailablePizzas();
        logger.info("Getting available pizzas list");

        if (!aviablePizzas.containsAll(pizza)) {
            throw new PizzaNotFoundException("Mamma mia!! One of the pizzas is not available");
        }
        logger.info("Calculating order price");
        double price = pizza.stream().map(p -> p.getPrice()).reduce(0.0,(sum, value) -> sum + value);
        Order order = new Order(this.orderList.size() +1,pizza,price);
        logger.info("Adding order to order list");
        this.orderList.add(order);

        return order;
    }

    public PizzaService(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public List<Pizza> getAvailablePizzas(){
        // oczekujmey listy dostpenych pizza
//        Zwrocenie listy dostepnych pizz filtrowanie po isAvailab
        logger.info("returing available pizza list");
        return pizzaList.stream().filter(p -> p.isAvailable() == true).collect(Collectors.toList());
    };

}
