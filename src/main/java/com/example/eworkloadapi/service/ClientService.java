package com.example.eworkloadapi.service;

import com.example.eworkloadapi.model.Client;
import com.example.eworkloadapi.repository.ClientRepository;
import com.example.eworkloadapi.exception.BadResourceException;
import com.example.eworkloadapi.exception.ResourceAlreadyExists;
import com.example.eworkloadapi.exception.ResourceNotFoundException;
import com.example.eworkloadapi.specification.ClientSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    private boolean existsById(Long id) { return existsById(id); }

    public Client findById(Long id) throws ResourceNotFoundException {

        Client client = clientRepository.findById(id).orElse(null);

        if (client == null) {
            throw new ResourceNotFoundException("Cannot find client with id: " + id);
        } else {
            return client;
        }

    }

    public List<Client> findAll(int pageNumber, int rowsPerPage) {
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll(QPageRequest.of(pageNumber - 1, rowsPerPage)).forEach(clients::add);
        return clients;
    }

    public List<Client> findByLastName(String lastName, int pageNumber, int rowsPerPage) {
        Client filter = new Client();
        filter.setLastName(lastName);
        Specification<Client> spec = new ClientSpecification(filter);

        List<Client> clients = new ArrayList<>();
        clientRepository.findAll(spec, QPageRequest.of(pageNumber - 1, rowsPerPage)).forEach(clients::add);

        return clients;
    }

    public Client save(Client client) throws BadResourceException, ResourceAlreadyExists {
        if (!StringUtils.isEmpty(client.getLastName())) {
            if (client.getLastName() != null && existsById(client.getId())) {
                throw new ResourceAlreadyExists("Client with id " + client.getId() +
                        " already exists!");
            }
            return clientRepository.save(client);
        } else {
            BadResourceException e = new BadResourceException("Failed to save client.");
            e.addErrorMessages("Client is null or empty!");
            throw e;
        }
    }

    public Client update(Client client) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(client.getLastName())) {
            if (!existsById(client.getId())) {
                throw new ResourceNotFoundException("Client with id " + client.getId() +
                        " doesn't exist!");
            }
            return clientRepository.save(client);
        } else {
            BadResourceException e = new BadResourceException("Failed to update client!");
            e.addErrorMessages("Client is null or empty!");
            throw e;
        }
    }
}
