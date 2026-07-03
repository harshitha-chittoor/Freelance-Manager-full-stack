package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.dto.InvoiceRequestDto;
import com.harshitha.freelancemanager.dto.InvoiceResponseDto;
import com.harshitha.freelancemanager.entity.Invoice;
import com.harshitha.freelancemanager.exception.FreelancerNotFoundException;
import com.harshitha.freelancemanager.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    // CREATE INVOICE
    public InvoiceResponseDto createInvoice(InvoiceRequestDto request) {

        Invoice invoice = new Invoice();

        invoice.setClientId(request.getClientId());
        invoice.setProposalId(request.getProposalId());
        invoice.setAmount(request.getAmount());

        invoice.setIssueDate(request.getIssueDate());
        invoice.setDueDate(request.getDueDate());

        invoice.setStatus("PENDING");
        invoice.setCreatedAt(LocalDate.now());

        invoice.setInvoiceNumber(generateInvoiceNumber());

        Invoice saved = invoiceRepository.save(invoice);

        return convertToDto(saved);
    }

    // GET ALL INVOICES
    public List<InvoiceResponseDto> getAllInvoices() {

        return invoiceRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public InvoiceResponseDto getInvoiceById(Long id) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() ->
                        new FreelancerNotFoundException("Invoice not found with id: " + id));

        return convertToDto(invoice);
    }

    // DELETE INVOICE
    public void deleteInvoice(Long id) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() ->
                        new FreelancerNotFoundException("Invoice not found with id: " + id));

        invoiceRepository.delete(invoice);
    }

    // INVOICE NUMBER GENERATOR
    private String generateInvoiceNumber() {
        return "INV-" + System.currentTimeMillis();
    }

    // ENTITY → DTO
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
    //update
    public InvoiceResponseDto updateInvoiceStatus(Long id, String status) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() ->
                        new FreelancerNotFoundException(
                                "Invoice not found with id: " + id));

        invoice.setStatus(status);

        Invoice updated = invoiceRepository.save(invoice);

        return convertToDto(updated);
    }
    //find by status
    public List<InvoiceResponseDto> getInvoicesByStatus(String status) {

        return invoiceRepository.findByStatus(status)
                .stream()
                .map(this::convertToDto)
                .toList();
    }
}