package com.example.securitymanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sec_lkp_chan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecLkpChan {
    @Id
    @Column(name = "chan_id", length = 50)
    private String chanId;
    @Column(name = "chan_nm", length = 50)
    private String chanNm;
    @Column(name = "ad_grp", length = 100)
    private String adGrp;
    @Column(name = "rec_ord")
    private Integer recOrd;
    @Column(name = "actvd_dt")
    private LocalDate actvdDt;
    @Column(name = "deprecated_dt")
    private LocalDate deprecatedDt;
    @Column(name = "created_by", length = 10)
    private String createdBy;
    @Column(name = "created_on")
    private LocalDate createdOn;
    @Column(name = "updated_by", length = 10)
    private String updatedBy;
    @Column(name = "updated_on")
    private LocalDate updatedOn;
} 