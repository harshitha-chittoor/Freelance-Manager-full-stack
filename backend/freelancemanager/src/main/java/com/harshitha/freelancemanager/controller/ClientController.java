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

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // ---------------- CREATE ----------------
    @PostMapping
    public ResponseEntity<ApiResponse<ClientResponseDto>> addClient(
            @Valid @RequestBody ClientRequestDto dto,
            HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Client created successfully",
                        clientService.addClient(dto, userId)
                ));
    }

    // ---------------- GET ALL ----------------
    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientResponseDto>>> getAllClients(
            HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Clients fetched successfully",
                        clientService.getAllClients(userId)
                )
        );
    }

    // ---------------- GET BY ID ----------------
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientResponseDto>> getClientById(@PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Client fetched successfully",
                        clientService.getClientById(id)
                )
        );
    }

    // ---------------- UPDATE ----------------
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientResponseDto>> updateClient(
            @PathVariable Long id,
            @Valid @RequestBody ClientRequestDto dto) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Client updated successfully",
                        clientService.updateClient(id, dto)
                )
        );
    }

    // ---------------- DELETE ----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteClient(@PathVariable Long id) {

        clientService.deleteClient(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Client deleted successfully",
                        "Deleted"
                )
        );
    }

    // ---------------- SEARCH ----------------
    @GetMapping("/search/name")
    public ResponseEntity<ApiResponse<List<ClientResponseDto>>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Search complete", clientService.searchByName(name))
        );
    }

    @GetMapping("/search/company")
    public ResponseEntity<ApiResponse<List<ClientResponseDto>>> searchByCompany(@RequestParam String company) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Search complete", clientService.searchByCompany(company))
        );
    }

    @GetMapping("/search/name/partial")
    public ResponseEntity<ApiResponse<List<ClientResponseDto>>> searchByNamePartial(@RequestParam String name) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Search complete", clientService.searchByNamePartial(name))
        );
    }

    @GetMapping("/search/company/partial")
    public ResponseEntity<ApiResponse<List<ClientResponseDto>>> searchByCompanyPartial(@RequestParam String company) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Search complete", clientService.searchByCompanyPartial(company))
        );
    }

    // ---------------- PAGINATION ----------------
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<ClientResponseDto>>> getPaginatedClients(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String field,
            @RequestParam String direction) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Pagination complete",
                        clientService.getClientsPaginated(page, size, field, direction)
                )
        );
    }
}