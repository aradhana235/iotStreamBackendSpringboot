//package com.alphacore.service;
////////
//
////////import java.util.HashMap;
////////import java.util.Map;
////////
////////import org.apache.catalina.startup.ClassLoaderFactory.Repository;
////////import org.springframework.stereotype.Service;
////////
////////import com.alphacore.model.Telemetry;
////////import com.alphacore.repository.TelemetryRepository;
////////
////////@Service
////////public class TelemetryService {
////////
////////    private final TelemetryRepository telemetryRepository;
////////
////////    TelemetryService(TelemetryRepository telemetryRepository) {
////////        this.telemetryRepository = telemetryRepository;
////////    }
////////	
////////	public Telemetry processData(Telemetry data) {
////////		double tempDiff =data.getInletTemp() -data.getOutletTemp();
////////		double invalidDiff=data.getOutletTemp()-data.getInletTemp();
////////		data.setTemperatureDiff(tempDiff);
////////		
////////		// 🔥 Trap Logic (basic version first)
////////        if (invalidDiff > 50) {
////////            data.setTrapStatus("Invalid");
////////            data.setStatusColor("Purple");
////////        }
////////        else 
////////        	if (tempDiff>=0 && tempDiff>10 && tempDiff < 30) {
////////            data.setTrapStatus("PartialLeak");
////////            data.setStatusColor("Orange");
////////        }
////////        else if (tempDiff >=0 && tempDiff<10 ) {
////////            data.setTrapStatus("FullLeak");
////////            data.setStatusColor("red");
////////        }
////////        else if (data.getInletTemp()<90) {
////////            data.setTrapStatus("Blocked");
////////            data.setStatusColor("Blue");
////////        }
////////        else {
////////            data.setTrapStatus("Normal");
////////            data.setStatusColor("Green");
////////        }
////////        double loss = tempDiff * 2;
////////        data.setSteamLossKgHr(loss);
////////        data.setSteamLossKgDay(loss * 24);
////////        data.setCostLoss(loss * 1.8);
////////        		
////////        data.setTimestamp(System.currentTimeMillis());
////////        double saved = tempDiff *1; // abhi basic
////////        if(data.getTrapStatus()=="Normal") {
////////        	   data.setSteamSavedKg(saved);
////////               data.setCostSaved(saved * 1.8);	
////////        }
////////        return data;	
////////	}
////////	
////////	public Map<String ,Object> getDashboardData(){
////////		Map<String , Object > data=new HashMap<>();
////////		data.put("totalSteamLoss",telemetryRepository.getTotalSteamLoss());
////////		data.put("totalCostLoss",telemetryRepository.getTotalCostLoss());
////////		data.put("normalCount", telemetryRepository.getNormalCount());
////////		data.put("PartialLeakCount", telemetryRepository.getPartialLeakCount());
////////		data.put("FullLeakCount",telemetryRepository.getFullLeakCount());
////////		data.put("BlockedCount",telemetryRepository.getBlockedCount());
////////		data.put("InvalidCount", telemetryRepository.getInvalidCount());
////////		data.put("TotalcostSaved", telemetryRepository.getTotalcostSaved());
////////		data.put("TotalsteamSavedKg", telemetryRepository.getTotalSteamSavedKg());
////////		
////////		return data;
////////		
////////		
////////	}
////////
////////	
////////}
//////
//////
//////package com.alphacore.service;
//////
//////import com.alphacore.model.Telemetry;
//////import com.alphacore.repository.TelemetryRepository;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.stereotype.Service;
//////
//////import java.util.List;
//////
//////@Service
//////public class TelemetryService {
//////
//////    @Autowired
//////    private TelemetryRepository repo;
//////
//////    // 🔹 SAVE DATA (ThingsBoard se aayega)
//////    public Telemetry save(Telemetry t) {
//////
//////       
//////
//////        if (t.getDeviceId() == null) {
//////            t.setDeviceId("unknown_device");
//////        }
//////
//////        // ✅ Debug print (VERY IMPORTANT 🔥)
//////        System.out.println("📥 Data received from ThingsBoard: " + t);
//////
//////        // ✅ Save to DB
//////        Telemetry savedData = repo.save(t);
//////
//////        System.out.println("✅ Data saved in DB: " + savedData);
//////
//////        return savedData;
//////    }
//////
//////    // 🔹 GET ALL DATA (testing ke liye)
//////    public List<Telemetry> getAll() {
//////        return repo.findAll();
//////    }
//////
//////    // 🔹 GET BY ID (optional)
//////    public Telemetry getById(Long id) {
//////        return repo.findById(id).orElse(null);
//////    }
//////}
////
////
////package com.alphacore.service;
////
////import com.alphacore.model.Device;
////import com.alphacore.model.Telemetry;
////import com.alphacore.repository.DeviceRepository;
////import com.alphacore.repository.TelemetryRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import java.util.List;
////
////@Service
////public class TelemetryService {
////
////    @Autowired
////    private TelemetryRepository repo;
////
////    public Telemetry save(Telemetry t) {
////    	
////    	
////    	
////        // 🔥 DEVICE ID FIX
////        if (t.getDevice() == null || t.getDevice().isEmpty()) {
////            t.setDevice("unknown_device");
////        }
////        // 🔥 DEVICE MAPPING (ADD THIS)
////        Device device = deviceRepo.findByDeviceId(t.getDevice());
////
////        if (device == null) {
////            // 🆕 create new device
////            device = new Device();
////            device.setDeviceId(t.getDevice());
////
////            // 👉 name = deviceId
////            device.setName(t.getDevice());
////
////            // 👉 location empty
////            device.setLocation("");
////
////            deviceRepo.save(device);
////
////            System.out.println("🆕 New Device Created: " + t.getDevice());
////        }
////
////        // ✅ relation set (IMPORTANT)
////        t.setDevice(device);
////        // 🔥 NULL SAFETY (VERY IMPORTANT)
////        if (t.getBatteryPercent() == null) t.setBatteryPercent(0);
////        if (t.getCostLoss() == null) t.setCostLoss(0.0);
////        if (t.getCostSaved() == null) t.setCostSaved(0.0);
////        if (t.getSteamLossKgHr() == null) t.setSteamLossKgHr(0.0);
////        if (t.getSteamLossKgDay() == null) t.setSteamLossKgDay(0.0);
////        if (t.getSteamSavedKg() == null) t.setSteamSavedKg(0.0);
////        if (t.getTemperatureDiff() == null) t.setTemperatureDiff(0.0);
////        if(t.getDurationCostLoss() == null) t.setDurationCostLoss(0.0);
////        if(t.getConditionDurationReadable() ==null) t.setConditionDurationReadable("----");
////        if(t.getDeviceName() == null) t.setDeviceName("name not found");
////        // 🔥 TIMESTAMP FIX
////        if (t.getTimestamp() == null) {
////            t.setTimestamp(System.currentTimeMillis());
////        }
////
////        // 🔥 OPTIONAL (status safety)
////        if (t.getTrapStatus() == null) {
////            t.setTrapStatus("unknown");
////        }
////
////        // ✅ DEBUG PRINT
////        System.out.println("📥 Data received from ThingsBoard: " + t);
////
////        // ✅ SAVE
////        Telemetry savedData = repo.save(t);
////
////        System.out.println("✅ Data saved in DB: " + savedData);
////
////        return savedData;
////    }
////
////    public List<Telemetry> getAll() {
////        return repo.findAll();
////    }
////
////    public Telemetry getById(Long id) {
////        return repo.findById(id).orElse(null);
////    }
////    
////    
////    @Autowired
////    private DeviceRepository deviceRepo;
////    
////  
////}
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.alphacore.AlphacoreBackendApplication;
//import com.alphacore.model.Device;
//import com.alphacore.model.Telemetry;
//import com.alphacore.repository.DeviceRepository;
//import com.alphacore.repository.TelemetryRepository;
//
//@Service
//public class TelemetryService {
//
//    private final AlphacoreBackendApplication alphacoreBackendApplication;
//
//	@Autowired
//	private TelemetryRepository repo;
//	@Autowired
//	private DeviceRepository deviceRepo;
//
//    TelemetryService(AlphacoreBackendApplication alphacoreBackendApplication) {
//        this.alphacoreBackendApplication = alphacoreBackendApplication;
//    }
//
//	public Telemetry save(Telemetry t) {
//
//		// ✅ sensorId fix
//		if (t.getSensorId() == null || t.getSensorId().isEmpty()) {
//			t.setSensorId("unknown_sensor");
//		}
//		   Device device = deviceRepo.findBySensorId(t.getSensorId());
//		   
//		   if(device == null) {
//			   device = new Device();
//			   device.setSensorId(t.getSensorId());
//			   device.setName(t.getDeviceName());
//			   device.setLocation("........");
//			   deviceRepo.save(device);
//			   System.out.println("new device created "+t.getSensorId());
//		   }
//		   t.setDevice(device);
//		// ✅ DEVICE NAME (optional)
//		if (t.getDeviceName() == null) {
//			t.setDeviceName("unknown_name");
//		}
//
//		// 🔥 NULL SAFETY
//		if (t.getBatteryPercent() == null)
//			t.setBatteryPercent(0);
//		if (t.getCostLoss() == null)
//			t.setCostLoss(0.0);
//		if (t.getCostSaved() == null)
//			t.setCostSaved(0.0);
//		if (t.getSteamLossKgHr() == null)
//			t.setSteamLossKgHr(0.0);
//		if (t.getSteamLossKgDay() == null)
//			t.setSteamLossKgDay(0.0);
//		if (t.getSteamSavedKg() == null)
//			t.setSteamSavedKg(0.0);
//		if (t.getTemperatureDiff() == null)
//			t.setTemperatureDiff(0.0);
//		if (t.getDurationCostLoss() == null)
//			t.setDurationCostLoss(0.0);
//		if (t.getConditionDurationReadable() == null)
//			t.setConditionDurationReadable("----");
//
//		// 🔥 TIMESTAMP
//		if (t.getTimestamp() == null) {
//			t.setTimestamp(System.currentTimeMillis());
//		}
//
//		// 🔥 STATUS
//		if (t.getTrapStatus() == null) {
//			t.setTrapStatus("unknown");
//		}
//
//		
//		t.setDevice(device);
//		System.out.println("📥 Data received: " + t);
//
//		Telemetry savedData = repo.save(t);
//
//		System.out.println("✅ Saved: " + savedData);
//
//		return savedData;
//	}
//
//	public List<Telemetry> getAll() {
//		return repo.findAll();
//	}
//
//	public Telemetry getById(Long id) {
//		return repo.findById(id).orElse(null);
//	}
//}