package com.learncoding.eworkloadjava.controller;

import com.learncoding.eworkloadjava.exception.BadResourceException;
import com.learncoding.eworkloadjava.exception.ResourceAlreadyExistsException;
import com.learncoding.eworkloadjava.exception.ResourceNotFoundException;
import com.learncoding.eworkloadjava.model.Booking;
import com.learncoding.eworkloadjava.model.Client;
import com.learncoding.eworkloadjava.repository.BookingRepository;
import com.learncoding.eworkloadjava.service.BookingService;
import com.learncoding.eworkloadjava.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int ROWS_PER_PAGE = 10;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/clients/{clientId}/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Booking>> getAll(
            @PathVariable(value = "clientId") Long clientId, Pageable pageable,
            @RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(required = false) String title) throws ResourceNotFoundException {

        return ResponseEntity.ok(bookingRepository.findByClientId(clientId, pageable));
    }

    @PostMapping(value = "/clients/{clientId}/bookings")
    public ResponseEntity<Booking> save(@PathVariable(name = "clientId") Long clientId,
                                        @RequestBody  Booking booking)  throws ResourceAlreadyExistsException {
        try {
            return ResponseEntity.ok(bookingService.save(clientId, booking));
        } catch (ResourceNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
