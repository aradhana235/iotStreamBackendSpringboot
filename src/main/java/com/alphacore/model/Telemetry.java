package com.alphacore.model;

import jakarta.persistence.*;

@Entity
public class Telemetry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double inletTemp;
	private Double outletTemp;
	private Double temperatureDiff;

	private String trapStatus;
	private String statusColor;

	private Double steamLossKgHr;
	private Double steamLossKgDay;
	private Double costLoss;

	private Double steamSavedKg;
	private Double costSaved;

	private Integer batteryPercent;
	private String signalStatus;
	
	private String deviceName;
	
	private String sensorId;
	
	
	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	
	
@ManyToOne
@JoinColumn(name="device_ref_id")
	private Device device;
	
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	private String deviceId;
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	
	
	private String conditionDurationReadable;
	private Double durationCostLoss;
	
	private Long timestamp;
	
	
	public String getConditionDurationReadable() {
		return conditionDurationReadable;
	}
	
	public void setConditionDurationReadable(String conditionDurationReadable ) {
		this.conditionDurationReadable=conditionDurationReadable;
	}
	
	public Double getDurationCostLoss() {
		return durationCostLoss;
	}
	
	public void setDurationCostLoss(Double durationCostLoss) {
		this.durationCostLoss=durationCostLoss;
	}

	public Double getTemperatureDiff() {
		return temperatureDiff;

	}

	public void setTemperatureDiff(Double temperatureDiff) {
		this.temperatureDiff = temperatureDiff;

	}

	public Long id() {
		return id;
	}

	public Double getInletTemp() {
		return inletTemp;
	}

	public void setInletTemp(Double inletTemp) {
		this.inletTemp = inletTemp;

	}

	public Double getOutletTemp() {
		return outletTemp;
	}

	public void setOutletTemp(Double outletTemp) {
		this.outletTemp = outletTemp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrapStatus() {
		return trapStatus;
	}

	public void setTrapStatus(String trapStatus) {
		this.trapStatus = trapStatus;
	}

	public String getStatusColor() {
		return statusColor;
	}

	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}

	public Double getSteamLossKgHr() {
		return steamLossKgHr;
	}

	public void setSteamLossKgHr(Double steamLossKgHr) {
		this.steamLossKgHr = steamLossKgHr;
	}

	public Double getSteamLossKgDay() {
		return steamLossKgDay;
	}

	public void setSteamLossKgDay(Double steamLossKgDay) {
		this.steamLossKgDay = steamLossKgDay;
	}

	public Double getCostLoss() {
		return costLoss;
	}

	public void setCostLoss(Double costLoss) {
		this.costLoss = costLoss;
	}

	public Double getSteamSavedKg() {
		return steamSavedKg;
	}

	public void setSteamSavedKg(Double steamSavedKg) {
		this.steamSavedKg = steamSavedKg;
	}

	public Double getCostSaved() {
		return costSaved;
	}

	public void setCostSaved(Double costSaved) {
		this.costSaved = costSaved;
	}

	public Integer getBatteryPercent() {
		return batteryPercent;
	}

	public void setBatteryPercent(Integer batteryPercent) {
		this.batteryPercent = batteryPercent;
	}

	public String getSignalStatus() {
		return signalStatus;
	}

	public void setSignalStatus(String signalStatus) {
		this.signalStatus = signalStatus;
	}

	

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
