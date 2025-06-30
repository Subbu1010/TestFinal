package com.example.securitymanagement.repository;

import com.example.securitymanagement.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SecurityRepository extends JpaRepository<SecLkpRole, String> {
    // Role
    @Query("SELECT r FROM SecLkpRole r WHERE (r.deprecatedDt IS NULL OR r.deprecatedDt > :currentDate) ORDER BY r.recOrd")
    List<SecLkpRole> findActiveRoles(@Param("currentDate") LocalDate currentDate);

    // Channel
    @Query("SELECT c FROM SecLkpChan c WHERE (c.deprecatedDt IS NULL OR c.deprecatedDt > :currentDate) ORDER BY c.recOrd")
    List<SecLkpChan> findActiveChannels(@Param("currentDate") LocalDate currentDate);

    // Audit Level
    @Query("SELECT a FROM SecLkpAudLvl a WHERE (a.deprecatedDt IS NULL OR a.deprecatedDt > :currentDate) ORDER BY a.recOrd")
    List<SecLkpAudLvl> findActiveAuditLevels(@Param("currentDate") LocalDate currentDate);

    // Permission
    @Query("SELECT p FROM SecLkpPerms p WHERE (p.deprecatedDt IS NULL OR p.deprecatedDt > :currentDate)")
    List<SecLkpPerms> findActivePermissions(@Param("currentDate") LocalDate currentDate);

    // Role-Permission
    @Query("SELECT rp FROM SecLkpRolePerms rp WHERE (rp.deprecatedDt IS NULL OR rp.deprecatedDt > :currentDate)")
    List<SecLkpRolePerms> findActiveRolePerms(@Param("currentDate") LocalDate currentDate);

    // Permission Category
    @Query("SELECT pc FROM SecLkpPermsCat pc WHERE (pc.deprecatedDt IS NULL OR pc.deprecatedDt > :currentDate)")
    List<SecLkpPermsCat> findActivePermsCats(@Param("currentDate") LocalDate currentDate);

    // getPermission endpoint
    @Query("SELECT p FROM SecLkpPerms p " +
           "JOIN SecLkpRolePerms rp ON p.permsId = rp.permsId " +
           "JOIN SecLkpRole r ON r.roleId = rp.roleId " +
           "JOIN SecLkpPermsCat pc ON p.permsCatId = pc.permsCatId " +
           "WHERE r.roleId = :roleId " +
           "AND pc.permsCatNm = :permsCatNm " +
           "AND (p.deprecatedDt IS NULL OR p.deprecatedDt > :currentDate) " +
           "AND (r.deprecatedDt IS NULL OR r.deprecatedDt > :currentDate) " +
           "AND (rp.deprecatedDt IS NULL OR rp.deprecatedDt > :currentDate) " +
           "AND (pc.deprecatedDt IS NULL OR pc.deprecatedDt > :currentDate)")
    List<SecLkpPerms> findPermissionsByRoleAndCategory(@Param("roleId") String roleId, @Param("permsCatNm") String permsCatNm, @Param("currentDate") LocalDate currentDate);
} 