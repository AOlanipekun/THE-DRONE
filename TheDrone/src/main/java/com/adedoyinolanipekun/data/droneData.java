/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adedoyinolanipekun.data;

import com.adedoyinolanipekun.data.drone.model;
import com.adedoyinolanipekun.data.drone.serialNo;
import com.adedoyinolanipekun.data.drone.state;
import com.adedoyinolanipekun.data.drone.weightLimit;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ADEDOYIN
 */
public class droneData {

    //(percentage);
    private int batterycapacity;
    public String model;
    public String state;
    public String serialnumber;
    public String sweightlimit;

    private model _model;
    private state _state;
    private serialNo _serialnumber;
    private weightLimit weightlimit;

    public serialNo getSerialnumber() {
        return _serialnumber;
    }

    public void setSerialnumber(serialNo _serialnumber) {
        this._serialnumber = _serialnumber;
    }

    public int getBatterycapacity() {
        return batterycapacity;
    }

    public void setBatterycapacity(int batterycapacity) {
        this.batterycapacity = batterycapacity;
    }

    public model getModel() {
        return _model;
    }

    public void setModel(model _model) {
        this._model = _model;
    }

    public state getState() {
        return _state;
    }

    public void setState(state _state) {
        this._state = _state;
    }

    public weightLimit getWeightlimit() {
        return weightlimit;
    }

    public void setWeightlimit(weightLimit weightlimit) {
        this.weightlimit = weightlimit;
    }

    public Map<String, String> DroneReg(String serialno, String model,
            String weightlimit, String batterycapacity, String state) throws Exception {
        Map<String, String> Response = new HashMap<String, String>();
        //
        try {
            //check serial no validity
            serialNo SN = new serialNo();
            SN.setSerialnumber(serialno);
            DroneRead_ValueExist(serialno);

            //check model type
            model M = new model();
            M.modelCheck(model);

            //Check weightlimit is not exceeded
            weightLimit WL = new weightLimit();
            WL.setWeightlimit(Integer.parseInt(weightlimit));

            //check the drone state is a valid state
            state ST = new state();
            ST.stateCheck(state);

            //check that battery capacity is between 0-100
            if (Integer.parseInt(batterycapacity) > 100 || Integer.parseInt(batterycapacity) < 0) {
                throw new Exception("Battery capacity invalid");
            }

            String Record = serialno + " " + model + " " + weightlimit + " "
                    + batterycapacity + " " + state;
            writeToFile wtf = new writeToFile();
            Response.put("Message", wtf.AppConfig(Record, "Dronedb.txt"));
        } catch (Exception e) {
            System.out.println(e);
            Response.put("Exception", e.toString());
        }
        return Response;
    }

    public Map<String, String> DroneRead(String serialno) throws Exception {
        Map<String, String> Response = new HashMap<String, String>();
        //
        Path newFilePath = Paths.get(System.getProperty("user.dir"));
        String dirName = newFilePath.toString();
        String fileName2 = dirName + "/Dronedb.txt";
        File f2 = new File(fileName2);
        try {
            Scanner value = new Scanner(f2);
            while (value.hasNextLine()) {
                String row;

                row = value.nextLine();
                if (row.contains("serialno")) {
                    continue;
                }
                if (row.isEmpty()) {
                    break;
                }
                Scanner data = new Scanner(row);

                serialnumber = data.next() + " ";

                serialnumber = serialnumber.trim();
                Response.put("serialnumber", serialnumber);

                if (serialnumber.equalsIgnoreCase(serialno)) {

                    model = data.next();
                    model = model.trim();
                    Response.put("model", model);

                    //integer value
                    sweightlimit = data.next();
                    Response.put("weightlimit", sweightlimit);

                    //integer value
                    batterycapacity = data.nextInt();
                    Response.put("batterycapacity", String.valueOf(batterycapacity));

                    state = data.next();
                    state = state.trim();
                    Response.put("state", state);
                }

                data.close();
            }
            if (Response.size() == 1) {
                throw new Exception("Specified Drone not found");
            }

            value.close();
        } catch (Exception e) {
//            Response.put("Error", e.toString());
            System.out.println(e.getMessage());
            throw new Exception(e);
        }
        return Response;
    }

    public boolean DroneRead_ValueExist(String serialno) throws Exception {
        boolean Response = false;
        //
        Path newFilePath = Paths.get(System.getProperty("user.dir"));
        String dirName = newFilePath.toString();
        String fileName2 = dirName + "/Dronedb.txt";
        File f2 = new File(fileName2);
        int size = 0;
        try {
            Scanner value = new Scanner(f2);
            while (value.hasNextLine()) {
                String row;
                row = value.nextLine();

                if (row.contains(serialno)) {
                    throw new Exception("Serial No already exist");
                }
                size++;
            }
            if (size == 10) {
                throw new Exception("New Drone can not be register (Max No Of Drone reached)");
            }
            value.close();
        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new Exception(e);
        }
        return Response;
    }

    public Map<String, String> DroneBatterylevel(String serialno) throws Exception {
        Map<String, String> Response = new HashMap<String, String>();
        //
        Path newFilePath = Paths.get(System.getProperty("user.dir"));
        String dirName = newFilePath.toString();
        String fileName2 = dirName + "/Dronedb.txt";
        File f2 = new File(fileName2);
        try {
            Scanner value = new Scanner(f2);
            while (value.hasNextLine()) {
                String row;

                row = value.nextLine();
                if (row.contains("serialno")) {
                    continue;
                }
                if (row.isEmpty()) {
                    break;
                }
                Scanner data = new Scanner(row);

                serialnumber = data.next() + " ";

                serialnumber = serialnumber.trim();
                Response.put("serialnumber", serialnumber);

                if (serialnumber.equalsIgnoreCase(serialno)) {

                    model = data.next();
                    model = model.trim();
                    Response.put("model", model);

                    //integer value
                    sweightlimit = data.next();
                    Response.put("weightlimit", sweightlimit);

                    //integer value
                    batterycapacity = data.nextInt();
                    Response.put("batterycapacity", String.valueOf(batterycapacity));

                    if (batterycapacity < 25) {
                        Response.put("state", "Battery Capacity is LOW, Drone can not be loaded");
                    } else {

                        state = data.next();
                        state = state.trim();
                        Response.put("state", state);
                    }
                }

                data.close();
            }
            if (Response.size() == 1) {
                Response.put("Error", "Specified Drone not found");
            }

            value.close();
        } catch (Exception e) {
            Response.put("Error", e.toString());
            System.out.println(e.getMessage());
        }
        return Response;
    }

    public List<Map<String, String>> DroneRead() throws Exception {
        List<Map<String, String>> FinalResponse = new ArrayList<>();

        //
        Path newFilePath = Paths.get(System.getProperty("user.dir"));
        String dirName = newFilePath.toString();
        String fileName2 = dirName + "/Dronedb.txt";
        File f2 = new File(fileName2);
        try {
            Scanner value = new Scanner(f2);
            while (value.hasNextLine()) {
                Map<String, String> Response = new HashMap<String, String>();
                String row;

                row = value.nextLine();
                if (row.contains("serialno")) {
                    continue;
                }
                if (row.isEmpty()) {
                    break;
                }
                Scanner data = new Scanner(row);

                serialnumber = data.next() + " ";

                serialnumber = serialnumber.trim();
                Response.put("serialnumber", serialnumber);

                model = data.next();
                model = model.trim();
                Response.put("model", model);

                //integer value
                sweightlimit = data.next();
                Response.put("weightlimit", sweightlimit);

                //integer value
                batterycapacity = data.nextInt();
                Response.put("batterycapacity", String.valueOf(batterycapacity));

                state = data.next();
                state = state.trim();
                Response.put("state", state);

                data.close();

                if (("idle".equals(state)) && (batterycapacity >= 25)) {
                    FinalResponse.add(Response);
                }
            }
            if (FinalResponse.size() < 1) {
                Map<String, String> Response = new HashMap<String, String>();
                Response.put("state", "Drone can not be loaded");
                FinalResponse.add(Response);
            }

            value.close();
        } catch (Exception e) {
            Map<String, String> Response = new HashMap<String, String>();
            Response.put("Error", e.toString());
            System.out.println(e.getMessage());
            FinalResponse.add(Response);
        }
        return FinalResponse;
    }

    private Map<String, String> DroneAvailableForLoading(String serialno) throws Exception {
        Map<String, String> FinalResponse = new HashMap<>();

        //
        Path newFilePath = Paths.get(System.getProperty("user.dir"));
        String dirName = newFilePath.toString();
        String fileName2 = dirName + "/Dronedb.txt";
        File f2 = new File(fileName2);
        try {
            Scanner value = new Scanner(f2);
            while (value.hasNextLine()) {
                Map<String, String> Response = new HashMap<String, String>();
                String row;

                row = value.nextLine();
                if (row.contains("serialno")) {
                    continue;
                }
                if (row.isEmpty()) {
                    break;
                }
                Scanner data = new Scanner(row);

                serialnumber = data.next() + " ";

                serialnumber = serialnumber.trim();
                Response.put("serialnumber", serialnumber);

                if (serialnumber.equalsIgnoreCase(serialno)) {
                    model = data.next();
                    model = model.trim();
                    Response.put("model", model);

                    //integer value
                    sweightlimit = data.next();
                    Response.put("weightlimit", sweightlimit);

                    //integer value
                    batterycapacity = data.nextInt();
                    Response.put("batterycapacity", String.valueOf(batterycapacity));

                    state = data.next();
                    state = state.trim();
                    Response.put("state", state);

                    data.close();

                    if (("idle".equals(state)) && (batterycapacity >= 25)) {
                        FinalResponse = (Response);
                    }
                }
            }
            if (FinalResponse.size() < 1) {
//                Map<String, String> Response = new HashMap<String, String>();
//                Response.put("state", "Drone can not be loaded");
                throw new Exception("Drone can not be loaded");
            }

            value.close();
        } catch (Exception e) {
//            Map<String, String> Response = new HashMap<String, String>();
//            Response.put("Error", e.toString());
//            System.out.println(e.getMessage());
//            FinalResponse = (Response)
            throw new Exception(e);
        }
        return FinalResponse;
    }

    public Map<String, String> writeLoadedDrone(Map<String, String> droneDetails, Map<String, String> MedicationDetails) throws Exception {
        Map<String, String> Response = new HashMap<String, String>();
        //
        try {

            String Record = droneDetails.get("serialnumber") + " " + MedicationDetails.get("medicationID");
            writeToFile wtf = new writeToFile();
            Response.put("Message", wtf.AppConfig(Record, "LoadedDronedb.txt"));
        } catch (Exception e) {
            System.out.println(e);
//            Response.put("Exception", e.toString());
            throw new Exception(e);
        }
        return Response;
    }

    public Map<String, String> DroneLoading(String serialno, String medicationID) throws Exception {
        Map<String, String> Response = new HashMap<String, String>();
        //
        try {
            //check if drone exist
            Map<String, String> droneDetails = DroneRead(serialno);

            //check if drone is avilable for loading
            DroneAvailableForLoading(serialno);

            //check if medication exist
            medicationData md = new medicationData();
            Map<String, String> medicationDetails = md.MedicationRead(medicationID);

            //check that the weigth of medications do not 
            //exceed that of the capacity of the drone
            //load the drone
            if (Integer.parseInt(droneDetails.get("weightlimit")) < Integer.parseInt(medicationDetails.get("weight"))) {
                Response.put("SerialNo", serialno);
                Response.put("medicationID", medicationID);
                Response.put("Error", "Medication Weight Exceed the drone WeightLimit");
            } else {
                Response.put("SerialNo", serialno);
                Response.put("medicationID", medicationID);
                writeLoadedDrone(droneDetails, medicationDetails);
                Response.put("Success", "Medication Loaded Successfully");
            }
        } catch (Exception e) {
            Response.put("SerialNo", serialno);
            Response.put("medicationID", medicationID);
            Response.put("Error", e.toString());
            System.out.println(e.getMessage());
        }
        return Response;
    }

}
