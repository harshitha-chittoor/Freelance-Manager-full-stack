package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.dto.ClientRequestDto;
import com.harshitha.freelancemanager.dto.ClientResponseDto;
import com.harshitha.freelancemanager.entity.Client;
import com.harshitha.freelancemanager.exception.FreelancerNotFoundException;
import com.harshitha.freelancemanager.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // CREATE
    public ClientResponseDto addClient(ClientRequestDto dto) {

        Client client = new Client();

        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setCompany(dto.getCompany());
        client.setPhoneNumber(dto.getPhoneNumber());

        Client savedClient = clientRepository.save(client);

        return convertToDto(savedClient);
    }

    // GET ALL
    public List<ClientResponseDto> getAllClients() {

        return clientRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public ClientResponseDto getClientById(Long id) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new FreelancerNotFoundException(
                                "Client not found with id: " + id));

        return convertToDto(client);
    }

    // UPDATE
    public ClientResponseDto updateClient(Long id,
                                          ClientRequestDto dto) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new FreelancerNotFoundException(
                                "Client not found with id: " + id));

        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setCompany(dto.getCompany());
        client.setPhoneNumber(dto.getPhoneNumber());

        Client updatedClient = clientRepository.save(client);

        return convertToDto(updatedClient);
    }

    // DELETE
    public void deleteClient(Long id) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new FreelancerNotFoundException(
                                "Client not found with id: " + id));

        clientRepository.delete(client);
    }

    // DTO Conversion
    private ClientResponseDto convertToDto(Client client) {

        ClientResponseDto dto = new ClientResponseDto();

        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setCompany(client.getCompany());
        dto.setPhoneNumber(client.getPhoneNumber());

        return dto;
    }
    // SEARCH BY NAME
    public List<ClientResponseDto> searchByName(String name) {

        return clientRepository.findByName(name)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    // SEARCH BY COMPANY
    public List<ClientResponseDto> searchByCompany(String company) {

        return clientRepository.findByCompany(company)
                .stream()
                .map(this::convertToDto)
                .toList();
    }
    // PARTIAL NAME SEARCH
    public List<ClientResponseDto> searchByNamePartial(String name) {

        return clientRepository
                .findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    // PARTIAL COMPANY SEARCH
    public List<ClientResponseDto> searchByCompanyPartial(String company) {

        return clientRepository
                .findByCompanyContainingIgnoreCase(company)
                .stream()
                .map(this::convertToDto)
                .toList();
    }
    //pagination
    public Page<ClientResponseDto> getClientsPaginated(
            int page,
            int size,
            String field,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(field).ascending()
                : Sort.by(field).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return clientRepository.findAll(pageable)
                .map(this::convertToDto);
    }
}