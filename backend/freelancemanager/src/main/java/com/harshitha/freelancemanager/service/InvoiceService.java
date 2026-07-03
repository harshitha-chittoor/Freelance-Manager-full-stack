package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.dto.*;
import com.harshitha.freelancemanager.entity.Invoice;
import com.harshitha.freelancemanager.exception.FreelancerNotFoundException;
import com.harshitha.freelancemanager.repository.InvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.harshitha.freelancemanager.entity.Proposal;
import com.harshitha.freelancemanager.repository.ProposalRepository;

import java.io.ByteArrayInputStream;
@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private PdfService pdfService;
    // ================= CREATE =================
    public InvoiceResponseDto createInvoice(InvoiceRequestDto request, Long userId) {

        Invoice invoice = new Invoice();

        invoice.setClientId(request.getClientId());
        invoice.setProposalId(request.getProposalId());
        invoice.setAmount(request.getAmount());

        invoice.setIssueDate(request.getIssueDate());
        invoice.setDueDate(request.getDueDate());

        invoice.setStatus("PENDING");
        invoice.setCreatedAt(LocalDate.now());

        invoice.setInvoiceNumber(generateInvoiceNumber());

        invoice.setUserId(userId);

        return convertToDto(invoiceRepository.save(invoice));
    }

    // ================= GET ALL (USER SAFE) =================
    public List<InvoiceResponseDto> getAllInvoices(Long userId) {

        List<Invoice> invoices = invoiceRepository.findByUserId(userId);

        LocalDate today = LocalDate.now();

        invoices.forEach(invoice -> {
            if (!"PAID".equals(invoice.getStatus())
                    && invoice.getDueDate() != null
                    && invoice.getDueDate().isBefore(today)) {
                invoice.setStatus("OVERDUE");
            }
        });

        invoiceRepository.saveAll(invoices);

        return invoices.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ================= GET BY ID (FIXED SECURITY) =================
    public InvoiceResponseDto getInvoiceById(Long id) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() ->
                        new FreelancerNotFoundException("Invoice not found with id: " + id));

        return convertToDto(invoice);
    }

    // ================= DELETE =================
    public void deleteInvoice(Long id) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() ->
                        new FreelancerNotFoundException("Invoice not found with id: " + id));

        invoiceRepository.delete(invoice);
    }

    // ================= STATUS UPDATE =================
    public InvoiceResponseDto updateInvoiceStatus(Long id, String status) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() ->
                        new FreelancerNotFoundException("Invoice not found with id: " + id));

        invoice.setStatus(status);

        return convertToDto(invoiceRepository.save(invoice));
    }

    // ================= FILTER =================
    public List<InvoiceResponseDto> getInvoicesByStatus(String status) {

        return invoiceRepository.findByStatus(status)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ================= DTO CONVERTER =================
    private InvoiceResponseDto convertToDto(Invoice invoice) {

        InvoiceResponseDto dto = new InvoiceResponseDto();

        dto.setId(invoice.getId());
        dto.setInvoiceNumber(invoice.getInvoiceNumber());

        dto.setClientId(invoice.getClientId());
        dto.setProposalId(invoice.getProposalId());

        dto.setAmount(invoice.getAmount());
        dto.setStatus(invoice.getStatus());

        dto.setIssueDate(invoice.getIssueDate());
        dto.setDueDate(invoice.getDueDate());
        dto.setCreatedAt(invoice.getCreatedAt());

        return dto;
    }

    // ================= INVOICE NUMBER =================
    private String generateInvoiceNumber() {
        return "INV-" + System.currentTimeMillis();
    }

    public InvoiceResponseDto generateInvoiceFromProposal(
            Long proposalId,
            LocalDate dueDate,
            Long userId) {

        Proposal proposal = proposalRepository
                .findByIdAndUserId(proposalId, userId)
                .orElseThrow(() ->
                        new FreelancerNotFoundException("Proposal not found"));

        if (!proposal.getStatus().equalsIgnoreCase("ACCEPTED")) {
            throw new RuntimeException(
                    "Only ACCEPTED proposals can generate invoices");
        }

        if (invoiceRepository.existsByProposalId(proposalId)) {
            throw new RuntimeException(
                    "Invoice already generated for this proposal");
        }

        Invoice invoice = new Invoice();

        invoice.setProposalId(proposal.getId());
        invoice.setClientId(proposal.getClientId());
        invoice.setAmount(proposal.getAmount());

        invoice.setIssueDate(LocalDate.now());
        invoice.setDueDate(dueDate);

        invoice.setStatus("PENDING");
        invoice.setCreatedAt(LocalDate.now());
        invoice.setInvoiceNumber(generateInvoiceNumber());

        invoice.setUserId(userId);

        return convertToDto(invoiceRepository.save(invoice));
    }
    public ByteArrayInputStream generateInvoicePdf(Long invoiceId) {

        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() ->
                        new FreelancerNotFoundException(
                                "Invoice not found with id: " + invoiceId));

        return pdfService.generateInvoicePdf(
                invoice.getInvoiceNumber(),
                invoice.getClientId(),
                invoice.getProposalId(),
                invoice.getAmount(),
                invoice.getStatus(),
                invoice.getIssueDate().toString(),
                invoice.getDueDate().toString()
        );
    }
}
