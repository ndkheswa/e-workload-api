package com.learncoding.eworkloadjava.service;

import com.learncoding.eworkloadjava.Specification.ClientSpecification;
import com.learncoding.eworkloadjava.exception.BadResourceException;
import com.learncoding.eworkloadjava.exception.ResourceAlreadyExistsException;
import com.learncoding.eworkloadjava.exception.ResourceNotFoundException;
import com.learncoding.eworkloadjava.model.Client;
import com.learncoding.eworkloadjava.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public boolean existsById(Long id) {
        return clientRepository.existsById(id);
    }

    public Client findById(Long id) throws ResourceNotFoundException {

        Client client = clientRepository.findById(id).orElse(null);

        if (client == null) {
            throw new ResourceNotFoundException("Cannot find client with id " + id);
        }
        return client;
    }

    public Page<Client> findAll(int pageNumber, int rowsPerPage) {
        return clientRepository.findAll(PageRequest.of(pageNumber - 1, rowsPerPage));
    }

    public Page<Client> findByLastName(String lastName, int pageNumber, int rowsPerPage) {
        var filter = new Client();

        filter.setLastName(lastName);
        Specification<Client> spec = new ClientSpecification(filter);

        return clientRepository.findAll(spec, PageRequest.of(pageNumber - 1, rowsPerPage));
    }

    public Client save(Client client) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(client.getLastName())) {
            if (client.getId() != null) {
                if (this.existsById(client.getId())) {
                    throw new ResourceAlreadyExistsException("Client with id " + client.getId() +
                            " already exists.");
                }
            }
            return clientRepository.save(client);

        } else {
            var e = new BadResourceException("Failed to save contact.");
            e.addErrorMessage("Client is null or empty");
            throw e;
        }
    }

    public Client update(Client client) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(client.getLastName())) {
            if (!existsById(client.getId())) {
                throw new ResourceNotFoundException("Client with id " + client.getId() +
                        " not found!");
            }
            return clientRepository.save(client);
        } else {
            var e = new BadResourceException("Failed to save contact!");
            e.addErrorMessage("Client is null or empty");
            throw e;
        }

    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id))
            throw new ResourceNotFoundException("Client with id " + id + " not found!");
        clientRepository.deleteById(id);
    }

    public Long count() {
        return clientRepository.count();
    }

}
