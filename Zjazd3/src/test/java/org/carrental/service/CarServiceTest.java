package org.carrental.service;

import org.carrental.exception.CarNotFoundException;
import org.carrental.exception.ValidationException;
import org.carrental.model.Car;
import org.carrental.model.CarClass;
import org.carrental.model.CarStatus;
import org.carrental.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {
    private static CarService carService;
    private static CarRepository carRepository;

    @BeforeAll
    static void setup() {
        carRepository = new CarRepository();
        carService = new CarService(carRepository);
    }

    public static Stream<Arguments> provideMakeValues() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("  "),
                null
        );
    }

    @AfterEach
    void cleanUp() {
        carRepository.removeAll();
    }
    @Test
    void shouldCorrectlyCreateCar() {
        Car car= new Car(null,"merc","elo","123", CarClass.PREMIUM, CarStatus.AVAILABLE);
        Car result = assertDoesNotThrow(()-> carService.createCar(car));

        assertEquals(car.getMake(),result.getMake());
        assertNotNull(result.getId());
    }
    @Test
    void shouldNotReturnAnyCar() {
        List<Car> result =carService.getAll();
        assertTrue(result.isEmpty());
    }
    @Test
    void shouldNotCreateCarDueToValidation() {
        Car car= new Car(null,"merc","elo","1235", CarClass.PREMIUM, CarStatus.IN_REPAIR);
        ValidationException result = assertThrows(ValidationException.class,
                () -> carService.createCar(car));
        assertEquals("vin",result.getField());
        assertEquals("vin max lenght is 3",result.getMessage());
    }

    @Test
    void shouldThrowCarNotFoundExceptionWhenIdDoesNotExist() {
        assertThrows(CarNotFoundException.class,
                () -> carService.getById(32));
    }
    @ParameterizedTest
    @MethodSource(value = "provideMakeValues")
    void ShouldCorrectlyValidateMake(String make) {
        Car car= new Car(null,make,"elo","123", CarClass.PREMIUM, CarStatus.IN_REPAIR);
        ValidationException result = assertThrows(ValidationException.class,
                () -> carService.createCar(car));
        assertEquals("make",result.getField());
        assertEquals("make cannot be blank",result.getMessage());
    }

    @Test
    void shouldCorrectlyCreateAvailableCar() {
        Car car= new Car(null,"merc","elo","123", CarClass.PREMIUM, CarStatus.AVAILABLE);
        Car result = assertDoesNotThrow(()-> carService.createCar(car));

        assertEquals(car.getMake(),result.getMake());
        assertNotNull(result.getId());
    }

    @Test
    void shouldReturnOnlyAvailableCars() {
        shouldCorrectlyCreateCar();
        shouldCorrectlyCreateAvailableCar();
        shouldCorrectlyCreateAvailableCar();
        List<Car> result = carService.getAvailableCars();
        assertFalse(result.isEmpty());
        assertTrue((result
                .stream()
                .allMatch(it -> it.getStatus().equals(CarStatus.AVAILABLE))));
    }

    @Test
    void shouldUpdateModelOfTheCar() {
        Car car= new Car(null,"merc","elo","123", CarClass.PREMIUM, CarStatus.AVAILABLE,23);
        Car createdCar = carService.createCar(car);
        Car result = assertDoesNotThrow(() -> carService.updateModel(createdCar.getId(),"BMW"));
        assertEquals(result.getModel(),"BMW");
    }

    @Test
    void shouldThrowExceptionWhenUpdatingCarThatDoesNotExist() {
        assertThrows(CarNotFoundException.class, ()-> carService.updateModel(1,"asd"));
    }
    @ParameterizedTest
    @ValueSource(strings = {"","  "})
    void shouldThrowValidationExceptionWhenUpdatingCarWithInvalidModel(String model) {
        assertThrows(ValidationException.class,()-> carService.updateModel(1,model));
    }
}
