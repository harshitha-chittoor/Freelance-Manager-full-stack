package com.harshitha.freelancemanager.controller;

import com.harshitha.freelancemanager.dto.*;
import com.harshitha.freelancemanager.security.JwtUtil;
import com.harshitha.freelancemanager.service.ProposalService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/proposals")
public class ProposalController {

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private JwtUtil jwtUtil;

    // ================= helper =================
    private Long extractUserId(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid token");
        }

        String token = authHeader.substring(7);
        return jwtUtil.extractUserId(token);
    }

    // ================= GET ALL =================
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProposalResponseDto>>> getAllProposals(
            HttpServletRequest request) {

        Long userId = extractUserId(request);

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Fetched all proposals",
                        proposalService.getAllProposals(userId))
        );
    }

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<ApiResponse<ProposalResponseDto>> addProposal(
            @Valid @RequestBody ProposalRequestDto dto,
            HttpServletRequest request) {

        Long userId = extractUserId(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(true,
                        "Proposal created successfully",
                        proposalService.addProposal(dto, userId))
        );
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProposalResponseDto>> getProposalById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Proposal fetched successfully",
                        proposalService.getProposalById(id))
        );
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProposalResponseDto>> updateProposal(
            @PathVariable Long id,
            @Valid @RequestBody ProposalRequestDto dto) {

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Proposal updated successfully",
                        proposalService.updateProposal(id, dto))
        );
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProposal(
            @PathVariable Long id) {

        proposalService.deleteProposal(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Proposal deleted successfully",
                        "Deleted")
        );
    }

    // ================= SEARCH =================
    @GetMapping("/search/status")
    public ResponseEntity<ApiResponse<List<ProposalResponseDto>>> searchByStatus(
            @RequestParam String status) {

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Status search completed",
                        proposalService.searchByStatus(status))
        );
    }

    @GetMapping("/search/status/partial")
    public ResponseEntity<ApiResponse<List<ProposalResponseDto>>> searchByStatusPartial(
            @RequestParam String status) {

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Partial status search completed",
                        proposalService.searchByStatusPartial(status))
        );
    }

    // ================= PAGINATION =================
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<ProposalResponseDto>>> getPaginatedProposals(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String field,
            @RequestParam String direction) {

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        "Proposal pagination completed",
                        proposalService.getProposalsPaginated(page, size, field, direction))
        );
    }
}