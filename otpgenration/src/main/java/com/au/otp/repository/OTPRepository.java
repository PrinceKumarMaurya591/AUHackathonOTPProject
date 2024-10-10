package com.au.otp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import com.au.otp.entity.OTPEntity;

@Repository
public interface OTPRepository extends RevisionRepository<OTPEntity, Long,Integer>, JpaRepository<OTPEntity, Long> {
@Query("select otp from OTPEntity otp where otp.requestId=:requestId")
public Optional<OTPEntity>findByRequestId(String requestId);
public Optional<OTPEntity> findByCustRef(String requestId);
}
