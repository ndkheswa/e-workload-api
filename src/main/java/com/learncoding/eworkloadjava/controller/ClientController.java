package com.learncoding.eworkloadjava.controller;

import com.learncoding.eworkloadjava.exception.BadResourceException;
import com.learncoding.eworkloadjava.exception.ResourceAlreadyExistsException;
import com.learncoding.eworkloadjava.exception.ResourceNotFoundException;
import com.learncoding.eworkloadjava.model.Client;
import com.learncoding.eworkloadjava.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int ROWS_PER_PAGE = 10;

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Client>> findAll(
        @RequestParam(value = "page", defaultValue = "1") int pageNumber,
        @RequestParam(required = false) String lastName
    ) {
        if (StringUtils.isEmpty(lastName)) {
            return ResponseEntity.ok(clientService.findAll(pageNumber, ROWS_PER_PAGE));
        } else {
            return ResponseEntity.ok(clientService.findByLastName(lastName, pageNumber, ROWS_PER_PAGE));
        }
    }

    @PostMapping(value = "/clients")
    public ResponseEntity<Client> save(@Valid @RequestBody Client client) throws URISyntaxException {
        try {
            client.setAge(client.getBirthDate());
            var newClient = clientService.save(client);
            return ResponseEntity.created(new URI("/api/v1/clients/" + newClient.getId()))
                    .body(client);
        } catch (ResourceAlreadyExistsException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (BadResourceException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<Client> update(@Valid @RequestBody Client client, @PathVariable int id) throws URISyntaxException {
        try {
            clientService.update(client);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (BadResourceException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        try {
            clientService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
