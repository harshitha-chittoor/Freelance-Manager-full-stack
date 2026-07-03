package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.dto.*;
import com.harshitha.freelancemanager.entity.Proposal;
import com.harshitha.freelancemanager.exception.ProposalNotFoundException;
import com.harshitha.freelancemanager.repository.ProposalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.stream.Collectors;
import com.harshitha.freelancemanager.repository.ProjectRepository;
import com.harshitha.freelancemanager.repository.InvoiceRepository;
@Service
public class ProposalService {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProjectRepository projectRepository;
    // ================= DTO =================
    private ProposalResponseDto convertToDto(Proposal proposal) {

        ProposalResponseDto dto = new ProposalResponseDto();

        dto.setId(proposal.getId());
        dto.setTitle(proposal.getTitle());
        dto.setDescription(proposal.getDescription());
        dto.setAmount(proposal.getAmount());
        dto.setStatus(proposal.getStatus());
        dto.setClientId(proposal.getClientId());
        dto.setInvoiceGenerated(
                invoiceRepository.existsByProposalId(
                        proposal.getId()
                )
        );
        dto.setProjectCreated(
                projectRepository.existsByProposalId(proposal.getId())
        );
        return dto;
    }

    private Proposal convertToEntity(ProposalRequestDto dto) {

        Proposal proposal = new Proposal();

        proposal.setTitle(dto.getTitle());
        proposal.setDescription(dto.getDescription());
        proposal.setAmount(dto.getAmount());
        proposal.setStatus(dto.getStatus());
        proposal.setClientId(dto.getClientId());

        return proposal;
    }

    // ================= CREATE =================
    public ProposalResponseDto addProposal(ProposalRequestDto dto, Long userId) {

        Proposal proposal = convertToEntity(dto);
        proposal.setUserId(userId);

        return convertToDto(proposalRepository.save(proposal));
    }

    // ================= GET ALL =================
    public List<ProposalResponseDto> getAllProposals(Long userId) {

        return proposalRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ================= GET BY ID =================
    public ProposalResponseDto getProposalById(Long id) {

        Proposal proposal = proposalRepository.findById(id)
                .orElseThrow(() ->
                        new ProposalNotFoundException("Proposal not found with id " + id));

        return convertToDto(proposal);
    }

    // ================= DELETE =================
    public void deleteProposal(Long id) {

        Proposal proposal = proposalRepository.findById(id)
                .orElseThrow(() ->
                        new ProposalNotFoundException("Proposal not found with id " + id));

        proposalRepository.delete(proposal);
    }

    // ================= UPDATE =================
    public ProposalResponseDto updateProposal(Long id, ProposalRequestDto dto) {

        Proposal proposal = proposalRepository.findById(id)
                .orElseThrow(() ->
                        new ProposalNotFoundException("Proposal not found with id " + id));

        proposal.setTitle(dto.getTitle());
        proposal.setDescription(dto.getDescription());
        proposal.setAmount(dto.getAmount());
        proposal.setStatus(dto.getStatus());
        proposal.setClientId(dto.getClientId());

        return convertToDto(proposalRepository.save(proposal));
    }

    // ================= SEARCH =================
    public List<ProposalResponseDto> searchByStatus(String status) {
        return proposalRepository.findByStatus(status)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProposalResponseDto> searchByStatusPartial(String status) {
        return proposalRepository.findByStatusContainingIgnoreCase(status)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ================= PAGINATION =================
    public Page<ProposalResponseDto> getProposalsPaginated(
            int page, int size, String field, String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(field).ascending()
                : Sort.by(field).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return proposalRepository.findAll(pageable)
                .map(this::convertToDto);
    }
}