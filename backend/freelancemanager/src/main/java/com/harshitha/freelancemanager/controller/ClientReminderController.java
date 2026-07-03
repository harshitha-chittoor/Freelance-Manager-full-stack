package com.harshitha.freelancemanager.controller;

import com.harshitha.freelancemanager.dto.ClientReminderRequestDto;
import com.harshitha.freelancemanager.dto.ClientReminderResponseDto;
import com.harshitha.freelancemanager.service.ClientReminderService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/clients")
public class ClientReminderController {

    private final ClientReminderService reminderService;

    public ClientReminderController(ClientReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping("/{clientId}/reminder")
    public ClientReminderResponseDto saveReminder(
            @PathVariable Long clientId,
            @RequestBody ClientReminderRequestDto request) {

        return reminderService.saveReminder(clientId, request);
    }

    @GetMapping("/{clientId}/reminder")
    public ClientReminderResponseDto getReminder(
            @PathVariable Long clientId) {

        return reminderService.getReminder(clientId);
    }

    @DeleteMapping("/reminders/{id}")
    public void deleteReminder(@PathVariable Long id) {

        reminderService.deleteReminder(id);
    }
}