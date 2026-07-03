package com.harshitha.freelancemanager.service.impl;

import com.harshitha.freelancemanager.dto.ClientAttachmentResponseDto;
import com.harshitha.freelancemanager.entity.Client;
import com.harshitha.freelancemanager.entity.ClientAttachment;
import com.harshitha.freelancemanager.repository.ClientAttachmentRepository;
import com.harshitha.freelancemanager.repository.ClientRepository;
import com.harshitha.freelancemanager.service.ClientAttachmentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientAttachmentServiceImpl implements ClientAttachmentService {

    private final ClientAttachmentRepository attachmentRepository;
    private final ClientRepository clientRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ClientAttachmentServiceImpl(
            ClientAttachmentRepository attachmentRepository,
            ClientRepository clientRepository) {

        this.attachmentRepository = attachmentRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientAttachmentResponseDto uploadAttachment(
            Long clientId,
            MultipartFile file) throws IOException {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Files.createDirectories(Paths.get(uploadDir));

        String uniqueFileName =
                UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path filePath =
                Paths.get(uploadDir, uniqueFileName);

        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        ClientAttachment attachment = new ClientAttachment();

        attachment.setClient(client);
        attachment.setFileName(file.getOriginalFilename());
        attachment.setFileType(file.getContentType());
        attachment.setFilePath(filePath.toString());
        attachment.setFileSize(file.getSize());

        attachment = attachmentRepository.save(attachment);

        ClientAttachmentResponseDto dto =
                new ClientAttachmentResponseDto();

        dto.setId(attachment.getId());
        dto.setFileName(attachment.getFileName());
        dto.setFileType(attachment.getFileType());
        dto.setFilePath(attachment.getFilePath());
        dto.setFileSize(attachment.getFileSize());

        return dto;
    }

    @Override
    public List<ClientAttachmentResponseDto> getAttachments(Long clientId) {

        return attachmentRepository.findByClientId(clientId)
                .stream()
                .map(a -> {

                    ClientAttachmentResponseDto dto =
                            new ClientAttachmentResponseDto();

                    dto.setId(a.getId());
                    dto.setFileName(a.getFileName());
                    dto.setFileType(a.getFileType());
                    dto.setFilePath(a.getFilePath());
                    dto.setFileSize(a.getFileSize());

                    return dto;

                }).collect(Collectors.toList());
    }

    @Override
    public void deleteAttachment(Long attachmentId)
            throws IOException {

        ClientAttachment attachment =
                attachmentRepository.findById(attachmentId)
                        .orElseThrow(() ->
                                new RuntimeException("Attachment not found"));

        Files.deleteIfExists(Paths.get(attachment.getFilePath()));

        attachmentRepository.delete(attachment);
    }

}