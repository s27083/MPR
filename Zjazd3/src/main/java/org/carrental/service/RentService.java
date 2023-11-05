package org.carrental.service;

import lombok.RequiredArgsConstructor;
import org.carrental.exception.CarNotFoundException;
import org.carrental.exception.RentalException;
import org.carrental.model.Car;
import org.carrental.model.rent.Rent;
import org.carrental.repository.RentRepository;

import java.time.LocalDate;

@RequiredArgsConstructor
public class RentService {
    private final CarService carService;
    private final RentRepository rentRepository;
    private final ClientService clientService;

    public Rent create(Integer carId, Integer clientId, LocalDate startDate, int rentLenght) {
        // start date nie moze byc w przeszlosci
        // carId != null
        // calientId != null
        // rentLenght > 0
        // czy pojazd jest dostepny
        Car selectedCar;
        try {
            selectedCar =  carService.getById(carId);

        } catch (CarNotFoundException exception) {
            throw new RentalException("Cannot create rent", exception);
        }

        // update status to rented

        Double price = selectedCar.getDailyRate() * rentLenght;
        // Sprawdz testem czy cena jest dobrze wyliczona

        LocalDate endDate = startDate.plusDays(rentLenght);
        // sprawdz testem czy data jest ok

        Rent rent = new Rent(carId,clientId,startDate,endDate,price);
        return  rentRepository.create(rent);
    }

    // metoda na pobrania wszystkcih nadchodzacaych rezerwacji
}
