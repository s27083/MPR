package org.carrental.repository;

import org.carrental.model.Car;
import org.carrental.model.Client;
import org.carrental.model.ClientStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientRepository {
    List<Client> clientsList = new ArrayList<>();
    public Client create(Client client) {
        client.setId(clientsList.size());
        clientsList.add(client);
        return client;
    }
    public Optional<Client> getById(Integer id) {
        return clientsList.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst();
    };

    public Optional<Client> getBySurname(String surname) {
        return clientsList.stream()
                .filter(client -> client.getSurName().equals(surname))
                .findFirst();

    }
    public List<Client> getAll() {
        return clientsList;
    }
    public Optional<Client> updateName(Integer id, String newModel) {
        Optional<Client> optionalCar = getById(id);
        return optionalCar.map(car -> {
            car.setName(newModel);
            return car;
        });
    }

    public void removById(Integer id) {
        Optional<Client> optionalClient = getById(id);
        optionalClient.ifPresent(client -> clientsList.remove(client));
    }

    public void removAll() {
        clientsList = new ArrayList<>();
    }
    public List<Client> getByStatus (ClientStatus status) {
        return clientsList.stream()
                .filter(client -> client.getStatus().equals(status))
                .collect(Collectors.toList());
    }
}
