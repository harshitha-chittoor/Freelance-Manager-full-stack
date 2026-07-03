package com.harshitha.freelancemanager.repository;

import com.harshitha.freelancemanager.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByStatus(String status);
    long countByStatus(String status);
    List<Invoice> findByUserId(Long userId);
    long countByUserId(Long userId);

    long countByUserIdAndStatus(Long userId, String status);
    boolean existsByProposalId(Long proposalId);
    @Query("""
    SELECT DATE_FORMAT(i.createdAt, '%b'),
           SUM(i.amount)
    FROM Invoice i
    WHERE i.userId = :userId
    GROUP BY DATE_FORMAT(i.createdAt, '%b'), MONTH(i.createdAt)
    ORDER BY MONTH(i.createdAt)
    """)
    List<Object[]> getMonthlyRevenue(@Param("userId") Long userId);
}