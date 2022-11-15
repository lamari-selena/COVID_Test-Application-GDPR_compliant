package com.example.notificationservice.services;

import com.example.notificationservice.model.Message;
import com.example.notificationservice.model.UserAlert;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class Consumer {

    private static final String TOPIC = "UserAlertTopic";

    @Autowired
    private WSService service;

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void consume(String json)
    {
        Gson gson = new GsonBuilder().create();
        Type listOfUserAtRisk = new TypeToken<ArrayList<UserAlert>>(){}.getType();
        System.out.println("User Alert message consumed -> String "+ json);
        try {
            List<UserAlert> listUserAlert = gson.fromJson(json, listOfUserAtRisk);
            System.out.println("List User Alert " + listUserAlert);
            service.notifyUsersAtRisk(listUserAlert);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

}
