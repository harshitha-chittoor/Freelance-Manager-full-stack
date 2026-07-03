package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.repository.ClientRepository;
import com.harshitha.freelancemanager.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.harshitha.freelancemanager.dto.DashboardResponseDto;

import com.harshitha.freelancemanager.entity.Invoice;
import java.util.List;

import java.util.stream.Collectors;
import com.harshitha.freelancemanager.dto.RevenueChartDto;
import com.harshitha.freelancemanager.repository.InvoiceRepository;
@Service
public class DashboardService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    public DashboardResponseDto getDashboardSummary(Long userId) {

        DashboardResponseDto response = new DashboardResponseDto();

        // ✅ FIX: fetch only user's invoices (not all)
        List<Invoice> invoices = invoiceRepository.findByUserId(userId);

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

        // ✅ FIX: user-specific counts
        response.setTotalClients(
                clientRepository.countByUserId(userId)
        );

        response.setTotalInvoices(
                invoiceRepository.countByUserId(userId)
        );

        response.setOverdueInvoices(
                invoiceRepository.countByUserIdAndStatus(userId, "OVERDUE")
        );

        return response;
    }
    public List<RevenueChartDto> getMonthlyRevenue(Long userId) {

        List<Object[]> results = invoiceRepository.getMonthlyRevenue(userId);

        return results.stream()
                .map(obj -> {

                    String month = obj[0] != null ? obj[0].toString() : "NA";

                    double revenue = 0.0;

                    if (obj[1] != null) {
                        revenue = ((Number) obj[1]).doubleValue();
                    }

                    return new RevenueChartDto(month, revenue);
                })
                .toList();
    }
}