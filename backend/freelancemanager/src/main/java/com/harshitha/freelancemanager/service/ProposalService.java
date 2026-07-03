package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.dto.ProposalRequestDto;
import com.harshitha.freelancemanager.dto.ProposalResponseDto;
import com.harshitha.freelancemanager.entity.Proposal;
import com.harshitha.freelancemanager.exception.ProposalNotFoundException;
import com.harshitha.freelancemanager.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class ProposalService {

    @Autowired
    private ProposalRepository proposalRepository;
    private ProposalResponseDto convertToDto(Proposal proposal) {

        ProposalResponseDto dto = new ProposalResponseDto();

        dto.setId(proposal.getId());
        dto.setTitle(proposal.getTitle());
        dto.setDescription(proposal.getDescription());
        dto.setAmount(proposal.getAmount());
        dto.setStatus(proposal.getStatus());

        return dto;
    }
    private Proposal convertToEntity(ProposalRequestDto dto) {

        Proposal proposal = new Proposal();

        proposal.setTitle(dto.getTitle());
        proposal.setDescription(dto.getDescription());
        proposal.setAmount(dto.getAmount());
        proposal.setStatus(dto.getStatus());

        return proposal;
    }
    public ProposalResponseDto addProposal(ProposalRequestDto dto) {

        Proposal proposal = convertToEntity(dto);

        Proposal savedProposal =
                proposalRepository.save(proposal);

        return convertToDto(savedProposal);
    }
    public List<ProposalResponseDto> getAllProposals() {

        return proposalRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }
    public ProposalResponseDto getProposalById(Long id) {

        Proposal proposal = proposalRepository.findById(id)
                .orElseThrow(() ->
                        new ProposalNotFoundException(
                                "Proposal not found with id " + id));

        return convertToDto(proposal);
    }
    public void deleteProposal(Long id) {

        Proposal proposal = proposalRepository.findById(id)
                .orElseThrow(() ->
                        new ProposalNotFoundException(
                                "Proposal not found with id " + id));

        proposalRepository.delete(proposal);
    }
    public ProposalResponseDto updateProposal(
            Long id,
            ProposalRequestDto dto) {

        Proposal proposal = proposalRepository.findById(id)
                .orElseThrow(() ->
                        new ProposalNotFoundException(
                                "Proposal not found with id " + id));

        proposal.setTitle(dto.getTitle());
        proposal.setDescription(dto.getDescription());
        proposal.setAmount(dto.getAmount());
        proposal.setStatus(dto.getStatus());

        Proposal updatedProposal =
                proposalRepository.save(proposal);

        return convertToDto(updatedProposal);
    }
    public List<ProposalResponseDto> searchByStatus(String status) {

        return proposalRepository.findByStatus(status)
                .stream()
                .map(this::convertToDto)
                .toList();
    }
    public List<ProposalResponseDto> searchByStatusPartial(String status) {

        return proposalRepository
                .findByStatusContainingIgnoreCase(status)
                .stream()
                .map(this::convertToDto)
                .toList();
    }
    public Page<ProposalResponseDto> getProposalsPaginated(
            int page,
            int size,
            String field,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(field).ascending()
                : Sort.by(field).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return proposalRepository.findAll(pageable)
                .map(this::convertToDto);
    }
}