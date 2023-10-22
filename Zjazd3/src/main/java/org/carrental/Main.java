package org.carrental;

import org.carrental.model.Car;
import org.carrental.model.CarClass;
import org.carrental.model.CarStatus;
import org.carrental.repository.CarRepository;
import org.carrental.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {

        CarRepository carRepository = new CarRepository();
        CarService carService = new CarService(carRepository);
        Car car= new Car(null,"merc","elo","123", CarClass.PREMIUM, CarStatus.IN_REPAIR);
        Car car2= new Car(null,"volf","golf","1", CarClass.PREMIUM, CarStatus.AVAILABLE);
        Car car3= new Car(null,"merc","benc","12", CarClass.PREMIUM, CarStatus.AVAILABLE);

        Car createdCar = carService.createCar(car);
        carService.createCar(car2);
        carService.createCar(car2);
        carService.createCar(car3);
        System.out.println(createdCar);
        Car carById = carService.getById(0);
        System.out.println(carById);
      //  Car invalidCar= new Car(null,"merc2","elo","123", CarClass.PREMIUM, CarStatus.AVAILABLE);
      //  carService.createCar(invalidCar);
        carService.updateModel(2,"polo");
        List<Car> availableCars = carService.getAvailableCars();
        System.out.println(availableCars);
        // Plan
        // 1 zainplematntowanie pozostaltych moeto z carRepository
        // 2 Utworzenie modelu serwis i repozytorium dla klienta
        // 3 dodanie logow do carService i clientService wkorzystaja np log4j

        /// kolejny zjazd
        // pokrycie porojektu testami jednostkowymi i pczatke prac na system rezerwacji
    }

}