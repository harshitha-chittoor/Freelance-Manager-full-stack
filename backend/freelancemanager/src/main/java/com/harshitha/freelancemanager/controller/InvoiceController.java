package com.harshitha.freelancemanager.controller;

import com.harshitha.freelancemanager.dto.InvoiceRequestDto;
import com.harshitha.freelancemanager.dto.InvoiceResponseDto;
import com.harshitha.freelancemanager.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    // CREATE INVOICE
    @PostMapping
    public InvoiceResponseDto createInvoice(@Valid @RequestBody InvoiceRequestDto requestDto){
        return invoiceService.createInvoice(requestDto);
    }

    // GET ALL INVOICES
    @GetMapping
    public List<InvoiceResponseDto> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    // GET INVOICE BY ID
    @GetMapping("/{id}")
    public InvoiceResponseDto getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }

    // DELETE INVOICE
    @DeleteMapping("/{id}")
    public String deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return "Invoice deleted successfully";
    }
    //update invoice
    @PutMapping("/{id}/status")
    public InvoiceResponseDto updateInvoiceStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return invoiceService.updateInvoiceStatus(id, status);
    }
    //find by status
    @GetMapping("/status/{status}")
    public List<InvoiceResponseDto> getInvoicesByStatus(
            @PathVariable String status) {

        return invoiceService.getInvoicesByStatus(status);
    }
}