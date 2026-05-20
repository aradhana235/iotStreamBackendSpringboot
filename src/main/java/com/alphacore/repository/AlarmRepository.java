package com.alphacore.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alphacore.model.Alarm;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, UUID> {

    @Query("SELECT COUNT(a) FROM Alarm a WHERE a.type = 'MEDIUM'")
    Long getMediumCount();

    @Query("SELECT COUNT(a) FROM Alarm a WHERE a.type = 'trap_blocked'")
    Long getBlockCount();

    @Query("SELECT COUNT(a) FROM Alarm a WHERE a.type = 'SteamTrapPartialLeak'")
    Long getPartialLeakCount();

    @Query("SELECT COUNT(a) FROM Alarm a WHERE a.type = 'trap_fullleak'")
    Long getFullLeakCount();
}