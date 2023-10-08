package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exceptions.PizzaNotFoundException;
import org.example.model.Pizza;

import org.example.service.PizzaService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {

        logger.info("Pizza Shop");
        try {
            Pizza pizzaPeperoni = new Pizza("pepperoni",35.0,true);
            Pizza pizzaMargherita = new Pizza("margherita",28.0,true);
            Pizza pizzaCapriciosa = new Pizza("capriciosa",36.0,false);
            Pizza pizzaMarinara = new Pizza("marinara",26.0,true);


            List<Pizza> pizzaList = new ArrayList<>();
            pizzaList.add(pizzaPeperoni);
            pizzaList.add(pizzaMargherita);
            pizzaList.add(pizzaCapriciosa);

            PizzaService pizzaService = new PizzaService(pizzaList);
            logger.info(pizzaService.getAvailablePizzas().toString());
            List<Pizza> pizzasToOrder = new ArrayList<>();
            pizzasToOrder.add(pizzaMargherita);
            pizzaService.makeOrder(pizzasToOrder);
            pizzaService.makeOrder(List.of(pizzaMargherita,pizzaPeperoni));
            pizzaService.makeOrder(List.of(pizzaMarinara));
            logger.info(pizzaService.getOrderList().toString());

        }
        catch (PizzaNotFoundException e) {
            logger.error(e);
        }
    }
}