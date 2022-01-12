/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adedoyinolanipekun.data;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ADEDOYIN
 */
public class medicationData {

    /*
    medicationID (100 characters max);
    name (allowed only letters, numbers, ‘-‘, ‘_’);
    weight;
    code (allowed only upper case letters, underscore and numbers);
    image (picture of the medication case).
     */
    private String code;
    private String name;
    private String imagelocation;
    private String medicationID;
    private int sweight;

    public boolean name_code_Check(String regx, String name) throws Exception {
        boolean bResponse = false;
        //String regx = "[a-zA-Z0-9]+\\.?";
//        String regx ="[^A-Za-z0-9\\_]";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        if (matcher.matches()) {
            //if pattern matches
            bResponse = true;
        } else {
            //if pattern does not matches
            throw new Exception("Invalid Format");
        }

        return bResponse;
    }

    public Map<String, String> MedicationReg(String medicationID, String name, String weight,
            String code, String image) throws Exception {
        Map<String, String> Response = new HashMap<>();
        //
        try {
            //check name validity
            name_code_Check("[^A-Za-z0-9\\_.-]", name);

            //check code validity
            name_code_Check("[^A-Za-z0-9\\_]", code);

            Path newFilePath = Paths.get(System.getProperty("user.dir"));
            String dirName = newFilePath.toString();
            String fileName2 = dirName + "/medicationImages/";

            String Record = medicationID + " " + name + " " + weight + " " + code + " " + fileName2;
            writeToFile wtf = new writeToFile();
            Response.put("Message", wtf.AppConfig(Record, "Medicationdb.txt"));
        } catch (Exception e) {
            System.out.println(e);
            Response.put("Exception", e.toString());
        }
        return Response;
    }

    public Map<String, String> MedicationRead(String MedicationID) throws Exception {
        Map<String, String> Response = new HashMap<String, String>();
        //
        Path newFilePath = Paths.get(System.getProperty("user.dir"));
        String dirName = newFilePath.toString();
        String fileName2 = dirName + "/Medicationdb.txt";
        File f2 = new File(fileName2);
        try {
            Scanner value = new Scanner(f2);
            while (value.hasNextLine()) {
                String row;

                row = value.nextLine();
                if (row.contains("MedicationID")) {
                    continue;
                }
                if (row.isEmpty()) {
                    break;
                }
                Scanner data = new Scanner(row);

                medicationID = data.next() + " ";

                medicationID = medicationID.trim();
                Response.put("medicationID", medicationID);

                if (medicationID.equalsIgnoreCase(MedicationID)) {

                    name = data.next();
                    name = name.trim();
                    Response.put("name", name);

                    //integer value
                    sweight = data.nextInt();
                    Response.put("weight", String.valueOf(sweight));

                    code = data.next();
                    Response.put("batterycapacity", code);

                    imagelocation = data.next();
                    imagelocation = imagelocation.trim();
                    Response.put("imagelocation", imagelocation);
                }

                data.close();
            }
            if (Response.size() == 1) {
                throw new Exception("Specified Medication not found");
            }

            value.close();
        } catch (Exception e) {
//            Response.put("Error", e.toString());
            System.out.println(e.getMessage());
            throw new Exception(e);
        }
        return Response;
    }

    public List<Map<String, String>> MedicationRead() throws Exception {
        List<Map<String, String>> FinalResponse = new ArrayList<>();
        //
        Path newFilePath = Paths.get(System.getProperty("user.dir"));
        String dirName = newFilePath.toString();
        String fileName2 = dirName + "/Medicationdb.txt";
        File f2 = new File(fileName2);
        try {
            Scanner value = new Scanner(f2);
            while (value.hasNextLine()) {
                Map<String, String> Response = new HashMap<String, String>();
                String row;

                row = value.nextLine();
               
                Scanner data = new Scanner(row);

                medicationID = data.next() + " ";

                medicationID = medicationID.trim();
                Response.put("medicationID", medicationID);

                name = data.next();
                name = name.trim();
                Response.put("name", name);

                //integer value
                sweight = data.nextInt();
                Response.put("weight", String.valueOf(sweight));

                code = data.next();
                Response.put("batterycapacity", code);

                imagelocation = data.next();
                imagelocation = imagelocation.trim();
                Response.put("imagelocation", imagelocation);
                FinalResponse.add(Response);

                data.close();
            }

            value.close();
        } catch (Exception e) {
//            Response.put("Error", e.toString());
            System.out.println(e.getMessage());
            throw new Exception(e);
        }
        return FinalResponse;
    }

}
