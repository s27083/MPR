package org.carrental.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.carrental.Main;
import org.carrental.exception.CarNotFoundException;
import org.carrental.exception.ValidationException;
import org.carrental.model.Car;
import org.carrental.model.CarStatus;
import org.carrental.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CarService {
    private static final Logger logger = LogManager.getLogger(CarService.class);

    private final CarRepository carRepository;

    public Car createCar(Car car){
        logger.info("Validating car data");
        if (car.getMake() == null || car.getMake().isBlank()) {
            throw new ValidationException("cannot be blank","make");
        }
        if (car.getVin().length() > 3) {
            throw new ValidationException("max lenght is 3", "vin");
        }
        logger.info("Creating new car");
        carRepository.create(car);
        return car;
    }
    public Car getById(Integer id) {
        logger.info("Getting car by id:" + id);
        Optional<Car> car = carRepository.getById(id);
        return car.orElseThrow(()-> new CarNotFoundException("Car does not exist"));
    }
    public List<Car> getAvailableCars() {
        logger.info("Getting available cars");
        return carRepository.getByStatus(CarStatus.AVAILABLE);
    }

    public Car updateModel (Integer id, String model) {
        logger.info("Updating car");
        if (model == null || model.isBlank()) throw new ValidationException("cannot be blaknk","model");
       return carRepository.updateModel(id,model).orElseThrow(()-> new CarNotFoundException("car does not exist"));

    }
    public List<Car> getAll() {
        return carRepository.getAll();
    }
}
