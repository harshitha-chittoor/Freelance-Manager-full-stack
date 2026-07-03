package com.harshitha.freelancemanager.controller;

import com.harshitha.freelancemanager.dto.ClientNoteRequestDto;
import com.harshitha.freelancemanager.dto.ClientNoteResponseDto;
import com.harshitha.freelancemanager.service.ClientNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ClientNoteController {

    @Autowired
    private ClientNoteService clientNoteService;

    // Add Note
    @PostMapping("/clients/{clientId}/notes")
    public ResponseEntity<ClientNoteResponseDto> addNote(
            @PathVariable Long clientId,
            @RequestBody ClientNoteRequestDto request) {

        return ResponseEntity.ok(
                clientNoteService.addNote(clientId, request)
        );
    }

    // Get Notes
    @GetMapping("/clients/{clientId}/notes")
    public ResponseEntity<List<ClientNoteResponseDto>> getNotes(
            @PathVariable Long clientId) {

        return ResponseEntity.ok(
                clientNoteService.getNotes(clientId)
        );
    }

    // Update Note
    @PutMapping("/notes/{noteId}")
    public ResponseEntity<ClientNoteResponseDto> updateNote(
            @PathVariable Long noteId,
            @RequestBody ClientNoteRequestDto request) {

        return ResponseEntity.ok(
                clientNoteService.updateNote(noteId, request)
        );
    }

    // Delete Note
    @DeleteMapping("/notes/{noteId}")
    public ResponseEntity<String> deleteNote(
            @PathVariable Long noteId) {

        clientNoteService.deleteNote(noteId);

        return ResponseEntity.ok("Note deleted successfully");
    }

    // Highlight / Unhighlight
    @PatchMapping("/notes/{noteId}/highlight")
    public ResponseEntity<ClientNoteResponseDto> toggleHighlight(
            @PathVariable Long noteId) {

        return ResponseEntity.ok(
                clientNoteService.toggleHighlight(noteId)
        );
    }
}