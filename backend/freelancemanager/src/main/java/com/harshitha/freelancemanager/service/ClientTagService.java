package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.dto.ClientTagRequestDto;
import com.harshitha.freelancemanager.dto.ClientTagResponseDto;

import java.util.List;

public interface ClientTagService {

    ClientTagResponseDto addTag(Long clientId, ClientTagRequestDto request);

    List<ClientTagResponseDto> getTags(Long clientId);

    void deleteTag(Long tagId);

}