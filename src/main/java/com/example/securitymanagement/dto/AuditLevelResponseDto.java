package com.example.securitymanagement.dto;

import lombok.Data;

@Data
public class AuditLevelResponseDto {
    private String audLvlId;
    private String audLvlNm;
    private String adGrp;
    private String appvrSoeid;
    private String appvrFname;
    private String appvrLname;
    private String appvrGrp;
    private String resrId;
    private Integer recOrd;
    private String defaultFlag = null;
} 