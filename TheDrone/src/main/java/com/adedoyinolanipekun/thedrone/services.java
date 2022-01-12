/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adedoyinolanipekun.thedrone;

import com.adedoyinolanipekun.data.droneData;
import com.adedoyinolanipekun.data.medicationData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Path;
import org.rakam.server.http.HttpService;
import org.rakam.server.http.annotations.JsonRequest;

/**
 *
 * @author ADEDOYIN
 */
@Path("/")
public class services extends HttpService {

    /**
     *
     * @param meterno meter number
     * @param pass the pass word for the user
     *
     *
     * @param serial number (100 characters max);
     * @param model (Lightweight, Middleweight,Cruiserweight, Heavyweight);
     * @param weightlimit (500gr max);
     * @param batterycapacity(percentage);
     * @param state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).
     * @return
     */
    @JsonRequest
    @ApiOperation(value = "Register a Drone")
    @Path("/drone_reg")
    public Map<String, String> DroneRegistration(@ApiParam("serialno") String serialno,
            @ApiParam("model") String model,
            @ApiParam("weightlimit") String weightlimit,
            @ApiParam("batterycapacity") String batterycapacity,
            @ApiParam("state") String state) {
        
        Map<String, String> Response = new HashMap<String, String>();
        try {
            droneData dd = new droneData();
            Response = dd.DroneReg(serialno, model, weightlimit, batterycapacity, state);
            
        } catch (Exception ex) {
            Logger.getLogger(services.class.getName()).log(Level.SEVERE, null, ex);
            
            Response.put("Error", ex.getMessage());
        }
        return Response;
    }
    
    @JsonRequest
    @ApiOperation(value = "Register a medication")
    @Path("/medication_reg")
    public Map<String, String> MedicationRegistration(@ApiParam("medicationID") String medicationID,
            @ApiParam("name") String name,
            @ApiParam("weight") String weight,
            @ApiParam("code") String code,
            @ApiParam("image") String image) {
        
        Map<String, String> Response = new HashMap<String, String>();
        try {
            medicationData md = new medicationData();
            Response = md.MedicationReg(medicationID, name, image, code, image);
            
        } catch (Exception ex) {
            Logger.getLogger(services.class.getName()).log(Level.SEVERE, null, ex);
            
            Response.put("Error", ex.getMessage());
        }
        return Response;
    }
    
    @JsonRequest
    @ApiOperation(value = "Drone Availabilty")
    @Path("/drone_availability")
    public Map<String, String> DroneAvailability(@ApiParam("serialno") String serialno) {
        
        Map<String, String> Response = new HashMap<String, String>();
        try {
            droneData dd = new droneData();
            Response = dd.DroneRead(serialno);
            
        } catch (Exception ex) {
            Logger.getLogger(services.class.getName()).log(Level.SEVERE, null, ex);
            
            Response.put("Error", ex.getMessage());
        }
        return Response;
        
    }
    
    @JsonRequest
    @ApiOperation(value = "Drone Availabilty")
    @Path("/drone_loading")
    public Map<String, String> DroneLoading(@ApiParam("serialno") String serialno,
            @ApiParam("medicationID") String medicationID) {
        Map<String, String> Response = new HashMap<String, String>();
        try {
            droneData dd = new droneData();
            Response = dd.DroneLoading(serialno, medicationID);
            
        } catch (Exception ex) {
            Logger.getLogger(services.class.getName()).log(Level.SEVERE, null, ex);
            
            Response.put("Error", ex.getMessage());
        }
        return Response;
    }
    
    @JsonRequest
    @ApiOperation(value = "Drone Loading Check for available drones")
    @Path("/drone_LoadingCheck")
    public List<Map<String, String>> DroneLoadingCheck() {
        List<Map<String, String>> Response = new ArrayList<>();
        try {
            droneData dd = new droneData();
            Response = dd.DroneRead();
            
        } catch (Exception ex) {
            Logger.getLogger(services.class.getName()).log(Level.SEVERE, null, ex);
            Map<String, String> Response1 = new HashMap<>();
            Response1.put("Error", ex.getMessage());
            Response.add(Response1);
        }
        return Response;
    }
    
    @JsonRequest
    @ApiOperation(value = "List Of Available Medication")
    @Path("/Medication_List")
    public List<Map<String, String>> MedicationList() {
        List<Map<String, String>> Response = new ArrayList<>();
        try {
            medicationData md = new medicationData();
            Response = md.MedicationRead();
            
        } catch (Exception ex) {
            Logger.getLogger(services.class.getName()).log(Level.SEVERE, null, ex);
            Map<String, String> Response1 = new HashMap<>();
            Response1.put("Error", ex.getMessage());
            Response.add(Response1);
        }
        return Response;
    }
    
    @JsonRequest
    @ApiOperation(value = "Drone Battery level")
    @Path("/drone_batterylevel")
    public Map<String, String> DroneBatteryLevel(@ApiParam("serialno") String serialno) {
        
        Map<String, String> Response = new HashMap<String, String>();
        try {
            droneData dd = new droneData();
            Response = dd.DroneBatterylevel(serialno);
            
        } catch (Exception ex) {
            Logger.getLogger(services.class.getName()).log(Level.SEVERE, null, ex);
            
            Response.put("Error", ex.getMessage());
        }
        return Response;
    }
   
}
