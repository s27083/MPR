package org.carrental.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.carrental.exception.ValidationException;
import org.carrental.model.CarStatus;
import org.carrental.model.Client;
import org.carrental.model.ClientStatus;
import org.carrental.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

public class ClientService {
    private static final Logger logger = LogManager.getLogger(ClientService.class);

    private final ClientRepository clientRepository;

    public Optional<Client> getById(Integer id) {
        logger.info("Getting client by id:" + id);
        return clientRepository.getById(id);
    }
    public Optional<Client> getBySurname(String surname) {
        logger.info("Getting client by surname:" + surname);

        if (surname.isBlank()) throw new ValidationException("is blank","surname");
        return clientRepository.getBySurname(surname);
    }
    public void removeById(Integer id){
        logger.info("Removing client with id:"+id);
        clientRepository.removById(id);
    }

    public Optional<Client> updateName(Integer id, String newName) {
        logger.info("Updating client name with id"+id+" to "+newName);
        return clientRepository.updateName(id,newName);

    }
    public List<Client> getAllPremium() {
        logger.info("Getting alle premium Users");
        return clientRepository.getByStatus(ClientStatus.PREMIUM);
    }
}
