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
public class state {
    //(IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING)

    private final String state_IDLE = "idle";
    private final String state_LOADING = "loading";
    private final String state_LOADED = "loaded";
    private final String state_DELIVERING = "delivering";
    private final String state_DELIVERED = "delivered";
    private final String state_RETURNING = "returning";

    public state() {
    }

    public boolean stateCheck(String state) {
        boolean bResponse = false;
        switch (state) {
            case state_IDLE:
            case state_LOADING:
            case state_LOADED:
            case state_DELIVERING: 
            case state_DELIVERED:
            case state_RETURNING:
                bResponse = true;
                break;
            default:
                throw new NotFoundException("State not Valid");

        }

        return bResponse;
    }
}
