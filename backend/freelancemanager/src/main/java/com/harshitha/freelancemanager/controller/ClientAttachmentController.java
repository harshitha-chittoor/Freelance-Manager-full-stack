package com.harshitha.freelancemanager.controller;

import com.harshitha.freelancemanager.dto.ClientAttachmentResponseDto;
import com.harshitha.freelancemanager.service.ClientAttachmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/clients")
public class ClientAttachmentController {

    private final ClientAttachmentService attachmentService;

    public ClientAttachmentController(ClientAttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/{clientId}/attachments")
    public ClientAttachmentResponseDto uploadAttachment(
            @PathVariable Long clientId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        return attachmentService.uploadAttachment(clientId, file);

    }

    @GetMapping("/{clientId}/attachments")
    public List<ClientAttachmentResponseDto> getAttachments(
            @PathVariable Long clientId
    ) {

        return attachmentService.getAttachments(clientId);

    }

    @DeleteMapping("/attachments/{id}")
    public void deleteAttachment(
            @PathVariable Long id
    ) throws IOException {

        attachmentService.deleteAttachment(id);

    }

}