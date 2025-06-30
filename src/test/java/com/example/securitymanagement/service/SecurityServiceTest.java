package com.example.securitymanagement.service;

import com.example.securitymanagement.dto.*;
import com.example.securitymanagement.entity.*;
import com.example.securitymanagement.repository.SecurityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecurityServiceTest {
    @Mock
    private CacheService cacheService;
    @Mock
    private SecurityRepository repository;
    @InjectMocks
    private SecurityService securityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRoles() {
        SecLkpRole role = new SecLkpRole("R1", "User", "desc", "GRP1", 1, LocalDate.now(), null, "SYS", LocalDate.now(), "SYS", LocalDate.now());
        when(cacheService.getRoles()).thenReturn(Arrays.asList(role));
        List<RoleResponseDto> result = securityService.getRoles();
        assertEquals(1, result.size());
        assertEquals("R1", result.get(0).getRoleId());
    }

    @Test
    void testGetChannels() {
        SecLkpChan chan = new SecLkpChan("C1", "Web", "GRP1", 1, LocalDate.now(), null, "SYS", LocalDate.now(), "SYS", LocalDate.now());
        when(cacheService.getChannels()).thenReturn(Arrays.asList(chan));
        List<ChannelResponseDto> result = securityService.getChannels();
        assertEquals(1, result.size());
        assertEquals("C1", result.get(0).getChanId());
    }

    @Test
    void testGetAuditLevels() {
        SecLkpAudLvl aud = new SecLkpAudLvl("A1", "L1", "GRP1", "SOE1", "John", "Doe", "APP_GRP", "RES1", 1, LocalDate.now(), null, "SYS", LocalDate.now(), "SYS", LocalDate.now());
        when(cacheService.getAuditLevels()).thenReturn(Arrays.asList(aud));
        List<AuditLevelResponseDto> result = securityService.getAuditLevels();
        assertEquals(1, result.size());
        assertEquals("A1", result.get(0).getAudLvlId());
    }

    @Test
    void testGetPermission() {
        SecLkpPerms perms = new SecLkpPerms(1, 1, "P1", "Read", "GRP1", 1, LocalDate.now(), null, "SYS", LocalDate.now(), "SYS", LocalDate.now());
        when(repository.findPermissionsByRoleAndCategory(eq("R1"), eq("USER"), any())).thenReturn(Arrays.asList(perms));
        List<PermissionResponseDto> result = securityService.getPermission("R1", "USER");
        assertEquals(1, result.size());
        assertEquals("P1", result.get(0).getPermissionId());
    }
} 