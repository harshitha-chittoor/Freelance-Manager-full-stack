package com.harshitha.freelancemanager.controller;

import com.harshitha.freelancemanager.dto.*;
import com.harshitha.freelancemanager.security.JwtUtil;
import com.harshitha.freelancemanager.service.InvoiceService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;

import org.springframework.core.io.InputStreamResource;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

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

    // ================= CREATE =================
    @PostMapping
    public InvoiceResponseDto createInvoice(
            @Valid @RequestBody InvoiceRequestDto requestDto,
            HttpServletRequest request) {

        Long userId = extractUserId(request);

        return invoiceService.createInvoice(requestDto, userId);
    }

    // ================= GET ALL =================
    @GetMapping
    public List<InvoiceResponseDto> getAllInvoices(HttpServletRequest request) {

        Long userId = extractUserId(request);

        return invoiceService.getAllInvoices(userId);
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public InvoiceResponseDto getInvoiceById(@PathVariable Long id) {

        return invoiceService.getInvoiceById(id);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public String deleteInvoice(@PathVariable Long id) {

        invoiceService.deleteInvoice(id);
        return "Invoice deleted successfully";
    }

    // ================= STATUS UPDATE =================
    @PutMapping("/{id}/status")
    public InvoiceResponseDto updateInvoiceStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return invoiceService.updateInvoiceStatus(id, status);
    }

    // ================= FILTER =================
    @GetMapping("/status/{status}")
    public List<InvoiceResponseDto> getInvoicesByStatus(
            @PathVariable String status) {

        return invoiceService.getInvoicesByStatus(status);
    }

    @PostMapping("/generate/{proposalId}")
    public InvoiceResponseDto generateInvoiceFromProposal(
            @PathVariable Long proposalId,
            @RequestBody GenerateInvoiceRequestDto dto,
            HttpServletRequest request) {

        Long userId = extractUserId(request);

        return invoiceService.generateInvoiceFromProposal(
                proposalId,
                dto.getDueDate(),
                userId
        );
    }
    @GetMapping("/{id}/pdf")
    public ResponseEntity<InputStreamResource> downloadInvoicePdf(
            @PathVariable Long id) {

        ByteArrayInputStream pdf =
                invoiceService.generateInvoicePdf(id);

        HttpHeaders headers = new HttpHeaders();

        headers.add(
                "Content-Disposition",
                "attachment; filename=invoice_" + id + ".pdf"
        );

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
}
