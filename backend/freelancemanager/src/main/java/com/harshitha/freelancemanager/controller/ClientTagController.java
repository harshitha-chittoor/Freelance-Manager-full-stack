package com.harshitha.freelancemanager.controller;

import com.harshitha.freelancemanager.dto.ClientTagRequestDto;
import com.harshitha.freelancemanager.dto.ClientTagResponseDto;
import com.harshitha.freelancemanager.service.ClientTagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ClientTagController {

    private final ClientTagService clientTagService;

    public ClientTagController(ClientTagService clientTagService) {
        this.clientTagService = clientTagService;
    }

    @PostMapping("/clients/{clientId}/tags")
    public ClientTagResponseDto addTag(
            @PathVariable Long clientId,
            @RequestBody ClientTagRequestDto request) {

        return clientTagService.addTag(clientId, request);
    }

    @GetMapping("/clients/{clientId}/tags")
    public List<ClientTagResponseDto> getTags(
            @PathVariable Long clientId) {

        return clientTagService.getTags(clientId);
    }

    @DeleteMapping("/tags/{tagId}")
    public void deleteTag(@PathVariable Long tagId) {

        clientTagService.deleteTag(tagId);
    }
}