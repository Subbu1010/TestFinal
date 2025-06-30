package com.example.securitymanagement.scheduler;

import com.example.securitymanagement.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataRefreshScheduler {
    @Autowired
    private CacheService cacheService;

    @Scheduled(cron = "0 0 4 * * ?")
    public void refreshCache() {
        cacheService.refreshAll();
        System.out.println("[Scheduler] Refreshed cache at 4 AM");
    }
} 