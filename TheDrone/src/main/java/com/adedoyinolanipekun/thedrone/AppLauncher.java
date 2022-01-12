/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adedoyinolanipekun.thedrone;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import org.rakam.server.http.HttpServer;
import org.rakam.server.http.HttpServerBuilder;

/**
 *
 * @author ADEDOYIN
 */
public class AppLauncher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            new AppLauncher().run();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
    }

    public void run() throws IOException, InterruptedException {
        /**
         * @URL reference https://github.com/buremba/netty-rest
         */
        HttpServer build = new HttpServerBuilder()
                .setHttpServices(new HashSet<>(Arrays.asList(new services()))).build();
        build.bindAwait("", 19051);
    }
}
