package com.harshitha.freelancemanager.service.impl;

import com.harshitha.freelancemanager.dto.ClientReminderRequestDto;
import com.harshitha.freelancemanager.dto.ClientReminderResponseDto;
import com.harshitha.freelancemanager.entity.Client;
import com.harshitha.freelancemanager.entity.ClientReminder;
import com.harshitha.freelancemanager.repository.ClientReminderRepository;
import com.harshitha.freelancemanager.repository.ClientRepository;
import com.harshitha.freelancemanager.service.ClientReminderService;
import org.springframework.stereotype.Service;

@Service
public class ClientReminderServiceImpl
        implements ClientReminderService {

    private final ClientReminderRepository reminderRepository;
    private final ClientRepository clientRepository;

    public ClientReminderServiceImpl(
            ClientReminderRepository reminderRepository,
            ClientRepository clientRepository) {

        this.reminderRepository = reminderRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientReminderResponseDto saveReminder(
            Long clientId,
            ClientReminderRequestDto request) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() ->
                        new RuntimeException("Client not found"));

        ClientReminder reminder =
                reminderRepository.findByClientId(clientId)
                        .orElse(new ClientReminder());

        reminder.setClient(client);
        reminder.setTitle(request.getTitle());
        reminder.setDescription(request.getDescription());
        reminder.setReminderDate(request.getReminderDate());

        reminder = reminderRepository.save(reminder);

        ClientReminderResponseDto dto =
                new ClientReminderResponseDto();

        dto.setId(reminder.getId());
        dto.setTitle(reminder.getTitle());
        dto.setDescription(reminder.getDescription());
        dto.setReminderDate(reminder.getReminderDate());

        return dto;
    }

    @Override
    public ClientReminderResponseDto getReminder(Long clientId) {

        ClientReminder reminder =
                reminderRepository.findByClientId(clientId)
                        .orElse(null);

        if (reminder == null)
            return null;

        ClientReminderResponseDto dto =
                new ClientReminderResponseDto();

        dto.setId(reminder.getId());
        dto.setTitle(reminder.getTitle());
        dto.setDescription(reminder.getDescription());
        dto.setReminderDate(reminder.getReminderDate());

        return dto;
    }

    @Override
    public void deleteReminder(Long reminderId) {

        reminderRepository.deleteById(reminderId);

    }

}