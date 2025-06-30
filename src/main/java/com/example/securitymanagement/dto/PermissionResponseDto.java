package com.example.securitymanagement.dto;

import lombok.Data;

@Data
public class PermissionResponseDto {
    private String permissionId;
    private String permissionNm;
    private String adGroup;
    private String defaultFlag = null;
} 