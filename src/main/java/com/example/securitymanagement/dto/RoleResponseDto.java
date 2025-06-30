package com.example.securitymanagement.dto;

import lombok.Data;

@Data
public class RoleResponseDto {
    private String roleId;
    private String roleNm;
    private String adGrp;
    private Integer recOrd;
    private String defaultFlag = null;
} 