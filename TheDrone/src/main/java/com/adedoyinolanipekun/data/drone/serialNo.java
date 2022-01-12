/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adedoyinolanipekun.data.drone;

import javax.naming.SizeLimitExceededException;

/**
 *
 * @author ADEDOYIN
 */
public class serialNo {
    // (100 characters max);

    private String serialnumber;

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) throws SizeLimitExceededException {
        int checkSerialno = serialnumber.length();
        if (checkSerialno > 100) {
            throw new SizeLimitExceededException("Serial No's Character ("+serialnumber +") is greater than 100");
        }
        this.serialnumber = serialnumber;
    }

}
