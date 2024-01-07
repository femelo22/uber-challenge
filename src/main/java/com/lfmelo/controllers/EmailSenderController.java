package com.lfmelo.controllers;

import com.lfmelo.application.EmailSenderService;
import com.lfmelo.core.EmailRequest;
import com.lfmelo.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {
    private final EmailSenderService service;

    @Autowired
    public EmailSenderController(EmailSenderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        try {
            this.service.sendEmail(request.to(), request.subject(), request.body());
            return ResponseEntity.ok("Email send successfully");
        } catch (EmailServiceException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while sending email");
        }

    }
}
