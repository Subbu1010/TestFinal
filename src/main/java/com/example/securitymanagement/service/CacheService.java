package com.example.securitymanagement.service;

import com.example.securitymanagement.entity.*;
import com.example.securitymanagement.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CacheService {
    private List<SecLkpRole> roles;
    private List<SecLkpChan> channels;
    private List<SecLkpAudLvl> auditLevels;
    private List<SecLkpPerms> permissions;
    private List<SecLkpRolePerms> rolePerms;
    private List<SecLkpPermsCat> permsCats;

    @Autowired
    private SecurityRepository repository;

    public void refreshAll() {
        LocalDate now = LocalDate.now();
        roles = repository.findActiveRoles(now);
        channels = repository.findActiveChannels(now);
        auditLevels = repository.findActiveAuditLevels(now);
        permissions = repository.findActivePermissions(now);
        rolePerms = repository.findActiveRolePerms(now);
        permsCats = repository.findActivePermsCats(now);
    }

    public List<SecLkpRole> getRoles() { return roles; }
    public List<SecLkpChan> getChannels() { return channels; }
    public List<SecLkpAudLvl> getAuditLevels() { return auditLevels; }
    public List<SecLkpPerms> getPermissions() { return permissions; }
    public List<SecLkpRolePerms> getRolePerms() { return rolePerms; }
    public List<SecLkpPermsCat> getPermsCats() { return permsCats; }
} 