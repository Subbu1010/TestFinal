package com.example.securitymanagement.service;

import com.example.securitymanagement.entity.*;
import com.example.securitymanagement.repository.SecurityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CacheServiceTest {
    @Mock
    private SecurityRepository repository;
    @InjectMocks
    private CacheService cacheService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRefreshAllAndGetters() {
        LocalDate now = LocalDate.now();
        List<SecLkpRole> roles = Arrays.asList(new SecLkpRole("R1", "User", "desc", "GRP1", 1, now, null, "SYS", now, "SYS", now));
        List<SecLkpChan> chans = Arrays.asList(new SecLkpChan("C1", "Web", "GRP1", 1, now, null, "SYS", now, "SYS", now));
        List<SecLkpAudLvl> audLvls = Arrays.asList(new SecLkpAudLvl("A1", "L1", "GRP1", "SOE1", "John", "Doe", "APP_GRP", "RES1", 1, now, null, "SYS", now, "SYS", now));
        List<SecLkpPerms> perms = Arrays.asList(new SecLkpPerms(1, 1, "P1", "Read", "GRP1", 1, now, null, "SYS", now, "SYS", now));
        List<SecLkpRolePerms> rolePerms = Arrays.asList(new SecLkpRolePerms(1, "R1", "P1", "Y", now, null, "SYS", now, "SYS", now));
        List<SecLkpPermsCat> permsCats = Arrays.asList(new SecLkpPermsCat(1, "USER", now, null, "SYS", now, "SYS", now));

        when(repository.findActiveRoles(now)).thenReturn(roles);
        when(repository.findActiveChannels(now)).thenReturn(chans);
        when(repository.findActiveAuditLevels(now)).thenReturn(audLvls);
        when(repository.findActivePermissions(now)).thenReturn(perms);
        when(repository.findActiveRolePerms(now)).thenReturn(rolePerms);
        when(repository.findActivePermsCats(now)).thenReturn(permsCats);

        cacheService.refreshAll();

        assertEquals(roles, cacheService.getRoles());
        assertEquals(chans, cacheService.getChannels());
        assertEquals(audLvls, cacheService.getAuditLevels());
        assertEquals(perms, cacheService.getPermissions());
        assertEquals(rolePerms, cacheService.getRolePerms());
        assertEquals(permsCats, cacheService.getPermsCats());
    }
} 