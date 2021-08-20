package com.learncoding.eworkloadapi.repository;

import com.learncoding.eworkloadapi.model.Client;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long>, JpaSpecificationExecutor<Client> {
}
