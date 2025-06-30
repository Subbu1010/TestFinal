package com.example.securitymanagement.config;

import com.example.securitymanagement.entity.*;
import com.example.securitymanagement.repository.SecurityRepository;
import com.example.securitymanagement.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private SecurityRepository repository;
    @Autowired
    private CacheService cacheService;

    @Override
    public void run(String... args) {
        // Insert random data for all tables
        LocalDate now = LocalDate.now();
        Random rand = new Random();

        // Permission Categories
        SecLkpPermsCat cat1 = repository.save(new SecLkpPermsCat(null, "USER", now, null, "SYS", now, "SYS", now));
        SecLkpPermsCat cat2 = repository.save(new SecLkpPermsCat(null, "ADMIN", now, null, "SYS", now, "SYS", now));
        SecLkpPermsCat cat3 = repository.save(new SecLkpPermsCat(null, "AUDIT", now, null, "SYS", now, "SYS", now));

        // Roles
        repository.save(new SecLkpRole("R1", "User", "Normal user", "GRP1", 1, now, null, "SYS", now, "SYS", now));
        repository.save(new SecLkpRole("R2", "Admin", "Administrator", "GRP2", 2, now, null, "SYS", now, "SYS", now));
        repository.save(new SecLkpRole("R3", "Auditor", "Audit user", "GRP3", 3, now, null, "SYS", now, "SYS", now));

        // Channels
        repository.save(new SecLkpChan("C1", "Web", "WEB_GRP", 1, now, null, "SYS", now, "SYS", now));
        repository.save(new SecLkpChan("C2", "Mobile", "MOB_GRP", 2, now, null, "SYS", now, "SYS", now));
        repository.save(new SecLkpChan("C3", "API", "API_GRP", 3, now, null, "SYS", now, "SYS", now));

        // Audit Levels
        repository.save(new SecLkpAudLvl("A1", "Level 1", "AUD_GRP1", "SOE1", "John", "Doe", "APP_GRP1", "RES1", 1, now, null, "SYS", now, "SYS", now));
        repository.save(new SecLkpAudLvl("A2", "Level 2", "AUD_GRP2", "SOE2", "Jane", "Smith", "APP_GRP2", "RES2", 2, now, null, "SYS", now, "SYS", now));
        repository.save(new SecLkpAudLvl("A3", "Level 3", "AUD_GRP3", "SOE3", "Bob", "Brown", "APP_GRP3", "RES3", 3, now, null, "SYS", now, "SYS", now));

        // Permissions
        repository.save(new SecLkpPerms(null, cat1.getPermsCatId(), "P1", "Read", "GRP1", 1, now, null, "SYS", now, "SYS", now));
        repository.save(new SecLkpPerms(null, cat2.getPermsCatId(), "P2", "Write", "GRP2", 2, now, null, "SYS", now, "SYS", now));
        repository.save(new SecLkpPerms(null, cat3.getPermsCatId(), "P3", "Audit", "GRP3", 3, now, null, "SYS", now, "SYS", now));

        // Role-Permissions
        repository.save(new SecLkpRolePerms(null, "R1", "P1", "Y", now, null, "SYS", now, "SYS", now));
        repository.save(new SecLkpRolePerms(null, "R2", "P2", "Y", now, null, "SYS", now, "SYS", now));
        repository.save(new SecLkpRolePerms(null, "R3", "P3", "Y", now, null, "SYS", now, "SYS", now));

        // Refresh cache after data load
        cacheService.refreshAll();
        System.out.println("[DataInitializer] Inserted random data and loaded cache");
    }
} 