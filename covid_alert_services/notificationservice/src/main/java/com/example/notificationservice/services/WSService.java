package com.example.notificationservice.services;

import com.example.notificationservice.model.Message;
import com.example.notificationservice.model.ResponseMessage;
import com.example.notificationservice.model.UserAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WSService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void notifyFrontend(final String message) {
        ResponseMessage response = new ResponseMessage(message);
        simpMessagingTemplate.convertAndSend("/topic/messages", response);
    }

    public void notifyUsersAtRisk(List<UserAlert> usersAtRisk) {
        for(UserAlert userAlert : usersAtRisk) {
            simpMessagingTemplate.convertAndSendToUser(userAlert.getUser_id(),"/queue/messages", new UserAlert(userAlert.getUser_id(), userAlert.getLocation_date()));
        }
    }

}
