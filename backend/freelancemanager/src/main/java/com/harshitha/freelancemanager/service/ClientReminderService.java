package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.dto.ClientReminderRequestDto;
import com.harshitha.freelancemanager.dto.ClientReminderResponseDto;

public interface ClientReminderService {

    ClientReminderResponseDto saveReminder(
            Long clientId,
            ClientReminderRequestDto request
    );

    ClientReminderResponseDto getReminder(Long clientId);

    void deleteReminder(Long reminderId);

}