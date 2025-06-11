package com.api.notifications.push.controller;

import com.api.notifications.push.services.ErrorService;
import com.api.notifications.push.services.PushService;
import dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/push")
public class PushController {

    @Autowired
    PushService pushService;
    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody NotificationDTO notification) throws ErrorService {
        pushService.send(notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }
}
