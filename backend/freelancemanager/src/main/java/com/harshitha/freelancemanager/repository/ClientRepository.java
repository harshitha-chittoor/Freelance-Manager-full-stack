package com.harshitha.freelancemanager.repository;

import com.harshitha.freelancemanager.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByName(String name);

    List<Client> findByCompany(String company);

    List<Client> findByNameContainingIgnoreCase(String name);

    List<Client> findByCompanyContainingIgnoreCase(String company);
}