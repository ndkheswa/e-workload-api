package com.example.eworkloadapi.controller;

import com.example.eworkloadapi.exception.BadResourceException;
import com.example.eworkloadapi.exception.ResourceAlreadyExists;
import com.example.eworkloadapi.model.Client;
import com.example.eworkloadapi.service.ClientService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int ROWS_PER_PAGE = 10;

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(required = false) String lastName ) {
        if (StringUtils.isEmpty(lastName)) {
            return ResponseEntity.ok(clientService.findAll(pageNumber, ROWS_PER_PAGE));
        } else {
            return ResponseEntity.ok(clientService.findByLastName(lastName, pageNumber, ROWS_PER_PAGE));
        }
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@Valid @RequestBody Client client) throws URISyntaxException {
        try {
            Client newClient = clientService.save(client);
            return ResponseEntity.created(new URI("/api/v1/clients" + newClient.getId()))
                    .body(client);
        } catch (ResourceAlreadyExists ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
