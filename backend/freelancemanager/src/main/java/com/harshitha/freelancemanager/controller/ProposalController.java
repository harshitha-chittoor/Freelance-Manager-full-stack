package com.harshitha.freelancemanager.controller;

import com.harshitha.freelancemanager.dto.ApiResponse;
import com.harshitha.freelancemanager.dto.ProposalRequestDto;
import com.harshitha.freelancemanager.dto.ProposalResponseDto;
import com.harshitha.freelancemanager.service.ProposalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/proposals")
public class ProposalController {

    @Autowired
    private ProposalService proposalService;

    // =========================
    // GET ALL
    // =========================
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProposalResponseDto>>> getAllProposals() {

        ApiResponse<List<ProposalResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Fetched all proposals",
                        proposalService.getAllProposals()
                );

        return ResponseEntity.ok(response);
    }

    // =========================
    // CREATE
    // =========================
    @PostMapping
    public ResponseEntity<ApiResponse<ProposalResponseDto>> addProposal(
            @Valid @RequestBody ProposalRequestDto dto) {

        ApiResponse<ProposalResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Proposal created successfully",
                        proposalService.addProposal(dto)
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // =========================
    // GET BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProposalResponseDto>> getProposalById(
            @PathVariable Long id) {

        ApiResponse<ProposalResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Proposal fetched successfully",
                        proposalService.getProposalById(id)
                );

        return ResponseEntity.ok(response);
    }

    // =========================
    // UPDATE
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProposalResponseDto>> updateProposal(
            @PathVariable Long id,
            @Valid @RequestBody ProposalRequestDto dto) {

        ApiResponse<ProposalResponseDto> response =
                new ApiResponse<>(
                        true,
                        "Proposal updated successfully",
                        proposalService.updateProposal(id, dto)
                );

        return ResponseEntity.ok(response);
    }

    // =========================
    // DELETE
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProposal(
            @PathVariable Long id) {

        proposalService.deleteProposal(id);

        ApiResponse<String> response =
                new ApiResponse<>(
                        true,
                        "Proposal deleted successfully",
                        "Deleted"
                );

        return ResponseEntity.ok(response);
    }

    // =========================
    // SEARCH BY STATUS
    // =========================
    @GetMapping("/search/status")
    public ResponseEntity<ApiResponse<List<ProposalResponseDto>>> searchByStatus(
            @RequestParam String status) {

        ApiResponse<List<ProposalResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Status search completed",
                        proposalService.searchByStatus(status)
                );

        return ResponseEntity.ok(response);
    }

    // =========================
    // PARTIAL STATUS SEARCH
    // =========================
    @GetMapping("/search/status/partial")
    public ResponseEntity<ApiResponse<List<ProposalResponseDto>>> searchByStatusPartial(
            @RequestParam String status) {

        ApiResponse<List<ProposalResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Partial status search completed",
                        proposalService.searchByStatusPartial(status)
                );

        return ResponseEntity.ok(response);
    }
    // =========================
// PAGINATION + SORTING
// =========================
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<ProposalResponseDto>>> getPaginatedProposals(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String field,
            @RequestParam String direction) {

        ApiResponse<Page<ProposalResponseDto>> response =
                new ApiResponse<>(
                        true,
                        "Proposal pagination completed",
                        proposalService.getProposalsPaginated(
                                page,
                                size,
                                field,
                                direction)
                );

        return ResponseEntity.ok(response);
    }
}