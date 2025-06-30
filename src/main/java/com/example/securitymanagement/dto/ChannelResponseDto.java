package com.example.securitymanagement.dto;

import lombok.Data;

@Data
public class ChannelResponseDto {
    private String chanId;
    private String chanNm;
    private String adGrp;
    private Integer recOrd;
    private String defaultFlag = null;
} 