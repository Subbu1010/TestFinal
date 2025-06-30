package com.example.securitymanagement.controller;

import com.example.securitymanagement.dto.*;
import com.example.securitymanagement.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/security")
public class SecurityController {
    @Autowired
    private SecurityService service;

    @GetMapping("/getRoles")
    public ResponseEntity<List<RoleResponseDto>> getRoles() {
        return ResponseEntity.ok(service.getRoles());
    }

    @GetMapping("/getChannels")
    public ResponseEntity<List<ChannelResponseDto>> getChannels() {
        return ResponseEntity.ok(service.getChannels());
    }

    @GetMapping("/getAuditLevels")
    public ResponseEntity<List<AuditLevelResponseDto>> getAuditLevels() {
        return ResponseEntity.ok(service.getAuditLevels());
    }

    @GetMapping("/getPermission")
    public ResponseEntity<List<PermissionResponseDto>> getPermission(
            @RequestParam String roleId,
            @RequestParam String permsCategoryName) {
        return ResponseEntity.ok(service.getPermission(roleId, permsCategoryName));
    }
} 