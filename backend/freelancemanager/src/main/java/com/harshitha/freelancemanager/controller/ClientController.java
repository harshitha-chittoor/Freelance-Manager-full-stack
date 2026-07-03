package com.harshitha.freelancemanager.controller;

import com.harshitha.freelancemanager.dto.ApiResponse;
import com.harshitha.freelancemanager.dto.ClientRequestDto;
import com.harshitha.freelancemanager.dto.ClientResponseDto;
import com.harshitha.freelancemanager.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<ClientResponseDto>> addClient(
            @Valid @RequestBody ClientRequestDto dto) {

        ApiResponse<ClientResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Client created successfully",
                        clientService.addClient(dto)
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientResponseDto>>> getAllClients() {

        ApiResponse<List<ClientResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Clients fetched successfully",
                        clientService.getAllClients()
                );

        return ResponseEntity.ok(response);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientResponseDto>> getClientById(
            @PathVariable Long id) {

        ApiResponse<ClientResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Client fetched successfully",
                        clientService.getClientById(id)
                );

        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientResponseDto>> updateClient(
            @PathVariable Long id,
            @Valid @RequestBody ClientRequestDto dto) {

        ApiResponse<ClientResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Client updated successfully",
                        clientService.updateClient(id, dto)
                );

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteClient(
            @PathVariable Long id) {

        clientService.deleteClient(id);

        ApiResponse<String> response =
                new ApiResponse<>(
                        true,
                        "Client deleted successfully",
                        "Deleted"
                );

        return ResponseEntity.ok(response);
    }
    @GetMapping("/search/name")
    public ResponseEntity<ApiResponse<List<ClientResponseDto>>> searchByName(
            @RequestParam String name) {

        ApiResponse<List<ClientResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Client search by name completed",
                        clientService.searchByName(name)
                );

        return ResponseEntity.ok(response);
    }
    @GetMapping("/search/company")
    public ResponseEntity<ApiResponse<List<ClientResponseDto>>> searchByCompany(
            @RequestParam String company) {

        ApiResponse<List<ClientResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Client search by company completed",
                        clientService.searchByCompany(company)
                );

        return ResponseEntity.ok(response);
    }
    @GetMapping("/search/name/partial")
    public ResponseEntity<ApiResponse<List<ClientResponseDto>>> searchByNamePartial(
            @RequestParam String name) {

        ApiResponse<List<ClientResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Partial name search completed",
                        clientService.searchByNamePartial(name)
                );

        return ResponseEntity.ok(response);
    }
    @GetMapping("/search/company/partial")
    public ResponseEntity<ApiResponse<List<ClientResponseDto>>> searchByCompanyPartial(
            @RequestParam String company) {

        ApiResponse<List<ClientResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Partial company search completed",
                        clientService.searchByCompanyPartial(company)
                );

        return ResponseEntity.ok(response);
    }
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<ClientResponseDto>>> getPaginatedClients(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String field,
            @RequestParam String direction) {

        ApiResponse<Page<ClientResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Client pagination completed",
                        clientService.getClientsPaginated(
                                page,
                                size,
                                field,
                                direction)
                );

        return ResponseEntity.ok(response);
    }
}