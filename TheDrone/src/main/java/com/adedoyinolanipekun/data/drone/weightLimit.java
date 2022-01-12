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
public class weightLimit {
     //(500gr max);
    private int weightlimit;

    public int getWeightlimit() {
        return weightlimit;
    }

    public void setWeightlimit(int weightlimit) throws SizeLimitExceededException {
        int WeightCheck = weightlimit;
        if (WeightCheck > 500)
            throw new SizeLimitExceededException("Product Weight ("+weightlimit + ") is greater than 500gr");
       
        this.weightlimit = weightlimit;
    }
    
}
