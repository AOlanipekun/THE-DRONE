/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adedoyinolanipekun.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author ADEDOYIN
 */
public class writeToFile {

//    final String FILE_NAME = "Dronedb.txt";
    FileInputStream fileinput = null;

    public String AppConfig(String Record, String FILE_NAME) throws Exception {
        String sResponse = "";
        try {
            //
            Path newFilePath = Paths.get(System.getProperty("user.dir"));
            String dirName = newFilePath.toString();
            String fileName2 = dirName + "/"+FILE_NAME;
            File file = new File(fileName2);
            if (file.exists()) {
                sResponse = createConfigFile(Record, FILE_NAME);
            } else {
                sResponse = "File(" + FILE_NAME + ") does not exist";
            }

//            fileinput = new FileInputStream(file);
        } catch (Exception e) {
            //sResponse = "Error: File not found" + e;
            e.printStackTrace();
            throw new Exception("Error: File not found" + e);
        } finally {
            if (fileinput != null) {
                try {
                    fileinput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new Exception("An Error occured:" + e);
                }
            }
        }
        return sResponse;
    }

    public String createConfigFile(String Record, String FILE_NAME) throws Exception {
        String sResponse = "";
        FileOutputStream fos = null;
        FileWriter writer = null;
        try {
            Path newFilePath = Paths.get(System.getProperty("user.dir"));
            String dirName = newFilePath.toString();
            String fileName2 = dirName + "/"+FILE_NAME ;
            File f1 = new File(dirName);
            File f2 = new File(fileName2);
            f1.mkdir();
            f2.createNewFile();

            //File fout = new File(meterno + "_" + tdate + ".txt");   //MeterNo_Date
            fos = new FileOutputStream(f2, true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(Record);
            bw.newLine();

            bw.close();
            sResponse = "Record saved successfully";
        } catch (FileNotFoundException ex) {
            System.out.println("File not found" + ex);
            //sResponse = "File not found" + ex;
            throw new Exception("File not found" + ex);
        } catch (IOException ex) {
            System.out.println("Exception while writing file " + ex);
            // sResponse = "Exception while writing file " + ex;
            throw new Exception("Exception while writing file " + ex);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }

                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
                System.out.println("Error while closing stream: " + ex);
                // sResponse = "Exception while closing stream: " + ex;
                throw new Exception("Exception closing stream " + ex);
            }
        }
        return sResponse;
    }
}
