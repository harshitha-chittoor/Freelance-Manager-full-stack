package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.dto.ClientRequestDto;
import com.harshitha.freelancemanager.dto.ClientResponseDto;
import com.harshitha.freelancemanager.entity.Client;
import com.harshitha.freelancemanager.exception.FreelancerNotFoundException;
import com.harshitha.freelancemanager.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ActivityService activityService;

    // ---------------- CREATE ----------------
    @Transactional
    public ClientResponseDto addClient(ClientRequestDto dto, Long userId) {

        Client client = new Client();

        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setCompany(dto.getCompany());
        client.setPhoneNumber(dto.getPhoneNumber());

        // 🔥 IMPORTANT FIX
        client.setUserId(userId);

        Client savedClient = clientRepository.save(client);

// Log activity
        activityService.logActivity(
                "Client '" + savedClient.getName() + "' added",
                "CLIENT"
        );

        return convertToDto(savedClient);
    }

    // ---------------- GET ALL ----------------
    public List<ClientResponseDto> getAllClients(Long userId) {

        System.out.println("FETCHING CLIENTS FOR USER ID: " + userId);

        List<Client> clients = clientRepository.findByUserId(userId);

        System.out.println("FOUND CLIENTS: " + clients.size());

        return clients.stream()
                .map(this::convertToDto)
                .toList();
    }

    // ---------------- GET BY ID ----------------
    public ClientResponseDto getClientById(Long id) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new FreelancerNotFoundException("Client not found with id: " + id));

        return convertToDto(client);
    }

    // ---------------- UPDATE ----------------
    @Transactional
    public ClientResponseDto updateClient(Long id, ClientRequestDto dto) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new FreelancerNotFoundException("Client not found with id: " + id));

        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setCompany(dto.getCompany());
        client.setPhoneNumber(dto.getPhoneNumber());

        Client updated = clientRepository.save(client);

        return convertToDto(updated);
    }

    // ---------------- DELETE ----------------
    public void deleteClient(Long id) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new FreelancerNotFoundException("Client not found with id: " + id));

        clientRepository.delete(client);
    }

    // ---------------- DTO CONVERTER ----------------
    private ClientResponseDto convertToDto(Client client) {

        ClientResponseDto dto = new ClientResponseDto();

        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setCompany(client.getCompany());
        dto.setPhoneNumber(client.getPhoneNumber());

        return dto;
    }

    // ---------------- SEARCH ----------------
    public List<ClientResponseDto> searchByName(String name) {
        return clientRepository.findByName(name)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ClientResponseDto> searchByCompany(String company) {
        return clientRepository.findByCompany(company)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ClientResponseDto> searchByNamePartial(String name) {
        return clientRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ClientResponseDto> searchByCompanyPartial(String company) {
        return clientRepository.findByCompanyContainingIgnoreCase(company)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ---------------- PAGINATION ----------------
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