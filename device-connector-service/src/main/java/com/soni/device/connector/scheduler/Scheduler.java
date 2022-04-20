package com.soni.device.connector.scheduler;

import com.soni.device.connector.service.ConnectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Scheduler {
    private final ConnectionService connectionService;

    Scheduler(ConnectionService greetingService) {
        this.connectionService = greetingService;
    }

    @Scheduled(fixedRateString = "6000", initialDelayString = "0")
    public void schedulingTask() {
        //log.info("Send messages due to schedule");
    }
}
