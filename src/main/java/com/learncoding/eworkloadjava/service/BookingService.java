package com.learncoding.eworkloadjava.service;

import com.learncoding.eworkloadjava.exception.ResourceNotFoundException;
import com.learncoding.eworkloadjava.model.Booking;
import com.learncoding.eworkloadjava.model.Client;
import com.learncoding.eworkloadjava.repository.BookingRepository;
import com.learncoding.eworkloadjava.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Long existById(Long id) { return existById(id); }

    public Page<Booking> findAll(int pageNumber, int rowsPerPage) {
        return bookingRepository.findAll(PageRequest.of(pageNumber, rowsPerPage));
    }

    public Page<Booking> findByClientId(Long clientId, Pageable pageable) {
        return bookingRepository.findByClientId(clientId, pageable);
    }

    public Booking save(Long clientId, Booking booking) throws ResourceNotFoundException {
        Set<Booking> bookings = new HashSet<>();

        Optional<Client> client1 = clientRepository.findById(clientId);

        if (client1.isEmpty()) {
            throw new ResourceNotFoundException("Client with id " +clientId + " doesn't exist.");
        }

        Client client = client1.get();

        // tie client to booking
        booking.setClient(client);

        Booking booking1 = bookingRepository.save(booking);
        // tie booking to client
        bookings.add(booking1);
        client.setBookings(bookings);

        return booking1;
    }
}
