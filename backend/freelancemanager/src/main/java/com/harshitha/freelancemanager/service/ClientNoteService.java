package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.dto.ClientNoteRequestDto;
import com.harshitha.freelancemanager.dto.ClientNoteResponseDto;
import com.harshitha.freelancemanager.entity.Client;
import com.harshitha.freelancemanager.entity.ClientNote;
import com.harshitha.freelancemanager.repository.ClientNoteRepository;
import com.harshitha.freelancemanager.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientNoteService {

    @Autowired
    private ClientNoteRepository clientNoteRepository;

    @Autowired
    private ClientRepository clientRepository;

    // Add Note
    public ClientNoteResponseDto addNote(Long clientId, ClientNoteRequestDto request) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        ClientNote note = new ClientNote();
        note.setNote(request.getNote());
        note.setHighlighted(false);
        note.setClient(client);

        ClientNote saved = clientNoteRepository.save(note);

        return mapToDto(saved);
    }

    // Get Notes
    public List<ClientNoteResponseDto> getNotes(Long clientId) {

        return clientNoteRepository
                .findByClientIdOrderByHighlightedDescCreatedAtDesc(clientId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Update Note
    public ClientNoteResponseDto updateNote(Long noteId,
                                            ClientNoteRequestDto request) {

        ClientNote note = clientNoteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        note.setNote(request.getNote());

        return mapToDto(clientNoteRepository.save(note));
    }

    // Delete Note
    public void deleteNote(Long noteId) {

        clientNoteRepository.deleteById(noteId);

    }

    // Highlight / Unhighlight
    public ClientNoteResponseDto toggleHighlight(Long noteId) {

        ClientNote note = clientNoteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        note.setHighlighted(!note.getHighlighted());

        return mapToDto(clientNoteRepository.save(note));
    }

    private ClientNoteResponseDto mapToDto(ClientNote note) {

        ClientNoteResponseDto dto = new ClientNoteResponseDto();

        dto.setId(note.getId());
        dto.setNote(note.getNote());
        dto.setHighlighted(note.getHighlighted());
        dto.setCreatedAt(note.getCreatedAt());

        return dto;
    }
}