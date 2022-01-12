/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adedoyinolanipekun.thedrone;

import java.util.List;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author ADEDOYIN
 */
public class servicesTest extends TestCase {
    
    public servicesTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of DroneRegistration method, of class services.
     */
    public void testDroneRegistration() {
        System.out.println("DroneRegistration");
        String serialno = "";
        String model = "";
        String weightlimit = "";
        String batterycapacity = "";
        String state = "";
        services instance = new services();
        Map<String, String> expResult = null;
        Map<String, String> result = instance.DroneRegistration(serialno, model, weightlimit, batterycapacity, state);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MedicationRegistration method, of class services.
     */
    public void testMedicationRegistration() {
        System.out.println("MedicationRegistration");
        String medicationID = "";
        String name = "";
        String weight = "";
        String code = "";
        String image = "";
        services instance = new services();
        Map<String, String> expResult = null;
        Map<String, String> result = instance.MedicationRegistration(medicationID, name, weight, code, image);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DroneAvailability method, of class services.
     */
    public void testDroneAvailability() {
        System.out.println("DroneAvailability");
        String serialno = "";
        services instance = new services();
        Map<String, String> expResult = null;
        Map<String, String> result = instance.DroneAvailability(serialno);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DroneLoading method, of class services.
     */
    public void testDroneLoading() {
        System.out.println("DroneLoading");
        String serialno = "";
        String medicationID = "";
        services instance = new services();
        Map<String, String> expResult = null;
        Map<String, String> result = instance.DroneLoading(serialno, medicationID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DroneLoadingCheck method, of class services.
     */
    public void testDroneLoadingCheck() {
        System.out.println("DroneLoadingCheck");
        services instance = new services();
        List<Map<String, String>> expResult = null;
        List<Map<String, String>> result = instance.DroneLoadingCheck();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DroneBatteryLevel method, of class services.
     */
    public void testDroneBatteryLevel() {
        System.out.println("DroneBatteryLevel");
        String serialno = "";
        services instance = new services();
        Map<String, String> expResult = null;
        Map<String, String> result = instance.DroneBatteryLevel(serialno);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
