package com.soni.device.connector.controller;

import com.soni.device.connector.constant.AppConstant;
import com.soni.device.connector.service.ConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class ConnectionController {

    private final ConnectionService connectionService;

    @Autowired
    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @MessageMapping(AppConstant.PUSH_TO_ADMIN)
    public void getFromAgent(Message<byte[]> message) {
        connectionService.sendToAdmin(AppConstant.BINARY_TOPIC, message);
    }

    @MessageMapping(AppConstant.PUSH_TO_AGENT)
    public void getFromAdmin(Message<byte[]> message) {
        connectionService.sendToAgent(AppConstant.BINARY_TOPIC, message);
    }

}