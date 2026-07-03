package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.dto.ClientAttachmentResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ClientAttachmentService {

    ClientAttachmentResponseDto uploadAttachment(
            Long clientId,
            MultipartFile file
    ) throws IOException;

    List<ClientAttachmentResponseDto> getAttachments(
            Long clientId
    );

    void deleteAttachment(Long attachmentId) throws IOException;

}