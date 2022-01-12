/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adedoyinolanipekun.data.drone;

import javax.ws.rs.NotFoundException;

/**
 *
 * @author ADEDOYIN
 */
public class model {

    //(Lightweight, Middleweight, Cruiserweight, Heavyweight);
    public final String model_Lightweight = "model_Lightweight";

    public final String model_Middleweight = "model_Middleweight";

    public final String model_Cruiserweight = "model_Cruiserweight";

    public final String model_Heavyweight = "model_Heavyweight";

    public model() {

    }

    public boolean modelCheck(String model) {
        boolean bResponse = false;
        switch (model) {
            case model_Lightweight:
            case model_Middleweight:
            case model_Cruiserweight:
            case model_Heavyweight:
                bResponse = true;
                break;
            default:
                throw new NotFoundException("Model type not found");

        }

        return bResponse;
    }

}
