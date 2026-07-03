package com.harshitha.freelancemanager.service.impl;

import com.harshitha.freelancemanager.dto.ClientTagRequestDto;
import com.harshitha.freelancemanager.dto.ClientTagResponseDto;
import com.harshitha.freelancemanager.entity.Client;
import com.harshitha.freelancemanager.entity.ClientTag;
import com.harshitha.freelancemanager.repository.ClientRepository;
import com.harshitha.freelancemanager.repository.ClientTagRepository;
import com.harshitha.freelancemanager.service.ClientTagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientTagServiceImpl implements ClientTagService {

    private final ClientRepository clientRepository;
    private final ClientTagRepository tagRepository;

    public ClientTagServiceImpl(ClientRepository clientRepository,
                                ClientTagRepository tagRepository) {
        this.clientRepository = clientRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public ClientTagResponseDto addTag(Long clientId,
                                       ClientTagRequestDto request) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        ClientTag tag = new ClientTag();
        tag.setName(request.getName());
        tag.setClient(client);

        tag = tagRepository.save(tag);

        ClientTagResponseDto dto = new ClientTagResponseDto();
        dto.setId(tag.getId());
        dto.setName(tag.getName());

        return dto;
    }

    @Override
    public List<ClientTagResponseDto> getTags(Long clientId) {

        return tagRepository.findByClientId(clientId)
                .stream()
                .map(tag -> {
                    ClientTagResponseDto dto = new ClientTagResponseDto();
                    dto.setId(tag.getId());
                    dto.setName(tag.getName());
                    return dto;
                })
                .collect(Collectors.toList());

    }

    @Override
    public void deleteTag(Long tagId) {

        tagRepository.deleteById(tagId);

    }

}