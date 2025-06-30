package com.example.securitymanagement.service;

import com.example.securitymanagement.dto.*;
import com.example.securitymanagement.entity.*;
import com.example.securitymanagement.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecurityService {
    @Autowired
    private CacheService cacheService;
    @Autowired
    private SecurityRepository repository;

    public List<RoleResponseDto> getRoles() {
        List<SecLkpRole> roles = cacheService.getRoles();
        if (roles == null) roles = repository.findActiveRoles(LocalDate.now());
        return roles.stream().map(r -> {
            RoleResponseDto dto = new RoleResponseDto();
            dto.setRoleId(r.getRoleId());
            dto.setRoleNm(r.getRoleNm());
            dto.setAdGrp(r.getAdGrp());
            dto.setRecOrd(r.getRecOrd());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<ChannelResponseDto> getChannels() {
        List<SecLkpChan> chans = cacheService.getChannels();
        if (chans == null) chans = repository.findActiveChannels(LocalDate.now());
        return chans.stream().map(c -> {
            ChannelResponseDto dto = new ChannelResponseDto();
            dto.setChanId(c.getChanId());
            dto.setChanNm(c.getChanNm());
            dto.setAdGrp(c.getAdGrp());
            dto.setRecOrd(c.getRecOrd());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<AuditLevelResponseDto> getAuditLevels() {
        List<SecLkpAudLvl> audLvls = cacheService.getAuditLevels();
        if (audLvls == null) audLvls = repository.findActiveAuditLevels(LocalDate.now());
        return audLvls.stream().map(a -> {
            AuditLevelResponseDto dto = new AuditLevelResponseDto();
            dto.setAudLvlId(a.getAudLvlId());
            dto.setAudLvlNm(a.getAudLvlNm());
            dto.setAdGrp(a.getAdGrp());
            dto.setAppvrSoeid(a.getAppvrSoeid());
            dto.setAppvrFname(a.getAppvrFname());
            dto.setAppvrLname(a.getAppvrLname());
            dto.setAppvrGrp(a.getAppvrGrp());
            dto.setResrId(a.getResrId());
            dto.setRecOrd(a.getRecOrd());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<PermissionResponseDto> getPermission(String roleId, String permsCatNm) {
        List<SecLkpPerms> perms = repository.findPermissionsByRoleAndCategory(roleId, permsCatNm, LocalDate.now());
        return perms.stream().map(p -> {
            PermissionResponseDto dto = new PermissionResponseDto();
            dto.setPermissionId(p.getPermsId());
            dto.setPermissionNm(p.getPermsNm());
            dto.setAdGroup(p.getAdGrp());
            return dto;
        }).collect(Collectors.toList());
    }
} 