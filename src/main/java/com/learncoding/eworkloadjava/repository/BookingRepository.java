package com.learncoding.eworkloadjava.repository;

import com.learncoding.eworkloadjava.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookingRepository extends PagingAndSortingRepository<Booking, Long> {
    Page<Booking> findByClientId(Long clientId, Pageable pageable);
}
