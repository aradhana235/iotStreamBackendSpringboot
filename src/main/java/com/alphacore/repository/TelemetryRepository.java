package com.alphacore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alphacore.model.Telemetry;



public interface TelemetryRepository  extends JpaRepository<Telemetry, Long>{

	@Query("SELECT SUM(t.steamLossKgDay) FROM Telemetry t")
	Double getTotalSteamLoss();

	@Query("SELECT SUM(t.costLoss) FROM Telemetry t")
	Double getTotalCostLoss();

	@Query("SELECT SUM(t.steamSavedKg) FROM Telemetry t")
	Double getTotalSteamSavedKg();
	
	
	@Query("SELECT SUM(t.costSaved) FROm Telemetry t")
	Double getTotalcostSaved();
	
	@Query("SELECT COUNT(t) FROM Telemetry t WHERE t.trapStatus = 'FullLeak'")
	Long getFullLeakCount();

	@Query("SELECT COUNT(t) FROM Telemetry t WHERE t.trapStatus = 'Normal'")
	Long getNormalCount();
	
	@Query("SELECT COUNT(t) FROM Telemetry t WHERE t.trapStatus = 'PartialLeak'")
	Long getPartialLeakCount();
	
	@Query("SELECT COUNT(t) FROM Telemetry t WHERE t.trapStatus ='Blocked'")
	Long getBlockedCount();
	
	@Query("SELECT COUNT(t) FROM Telemetry t WHERE t.trapStatus ='Invalid'")
	Long getInvalidCount();
}
