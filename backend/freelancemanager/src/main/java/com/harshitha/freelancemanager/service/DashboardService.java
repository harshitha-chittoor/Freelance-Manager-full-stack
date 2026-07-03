package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.repository.ClientRepository;
import com.harshitha.freelancemanager.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.harshitha.freelancemanager.dto.DashboardResponseDto;

import com.harshitha.freelancemanager.entity.Invoice;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientRepository clientRepository;
    public DashboardResponseDto getDashboardSummary() {

        DashboardResponseDto response = new DashboardResponseDto();
        List<Invoice> invoices = invoiceRepository.findAll();
        double totalRevenue = 0;
        double pendingRevenue = 0;
        for (Invoice invoice : invoices) {
            if ("PAID".equalsIgnoreCase(invoice.getStatus())) {
                totalRevenue += invoice.getAmount();
            }
            if ("PENDING".equalsIgnoreCase(invoice.getStatus())) {
                pendingRevenue += invoice.getAmount();
            }
        }
        response.setTotalRevenue(totalRevenue);
        response.setPendingRevenue(pendingRevenue);
        response.setTotalClients(
                clientRepository.count()
        );
        response.setTotalInvoices(
                invoiceRepository.count()
        );
        response.setOverdueInvoices(
                invoiceRepository.countByStatus("OVERDUE")
        );
        return response;
    }
}