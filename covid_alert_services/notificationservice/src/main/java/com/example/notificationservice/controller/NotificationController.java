package com.example.notificationservice.controller;

import com.example.notificationservice.model.Message;
import com.example.notificationservice.model.ResponseMessage;
import com.example.notificationservice.model.UserAlert;
import com.example.notificationservice.services.WSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private WSService service;

    @GetMapping
    @RequestMapping("post_notification/{user_id}")
    public void postMessage(@PathVariable("user_id") String id) {
        ArrayList<UserAlert> usersAtRisk = new ArrayList<>();
        usersAtRisk.add(new UserAlert("0bcd3199-b211-4538-8ab5-fbf69c69ffd3"));
        usersAtRisk.add(new UserAlert("c7ae1c2c-643b-4a7e-b764-79bc81d5d3b5"));

        service.notifyUsersAtRisk(usersAtRisk);
    }

}