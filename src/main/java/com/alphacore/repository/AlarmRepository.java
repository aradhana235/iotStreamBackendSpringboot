package com.alphacore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alphacore.model.Alarm;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    @Query("SELECT COUNT(a) FROM Alarm a WHERE a.alarmType = 'MEDIUM'")
    Long getMediumCount();

    @Query("SELECT COUNT(a) FROM Alarm a WHERE a.alarmType = 'trap_blocked'")
    Long getBlockCount();

    @Query("SELECT COUNT(a) FROM Alarm a WHERE a.alarmType = 'SteamTrapPartialLeak'")
    Long getFullBlockCount();

    @Query("SELECT COUNT(a) FROM Alarm a WHERE a.alarmType = 'trap_fullleak'")
    Long getFullLeakCount();
}