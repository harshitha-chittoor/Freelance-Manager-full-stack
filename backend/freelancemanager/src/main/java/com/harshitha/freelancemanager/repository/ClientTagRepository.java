package com.harshitha.freelancemanager.repository;

import com.harshitha.freelancemanager.entity.ClientTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientTagRepository extends JpaRepository<ClientTag,Long>{

    List<ClientTag> findByClientId(Long clientId);

}