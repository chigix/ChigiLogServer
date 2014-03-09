/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver;

import chigi.logserver.config.Config;
import chigi.logserver.handler.server.AdminServerHandler;
import chigi.logserver.handler.server.LogInputServerHandler;
import chigi.logserver.handler.server.LogOutputServerHandler;

/**
 *
 * @author 郷
 */
public class Main {
    public static void main(String[] args){
        Config config = new Config(8081);
        Thread server = new Thread(new AdminServerHandler(config));
        server.start();
//        Config outconfig = new Config(8081);
//        Thread outserver = new Thread(new LogOutputServerHandler(outconfig));
//        outserver.start();
//        Config inconfig = new Config(8082);
//        Thread inserver = new Thread(new LogInputServerHandler(inconfig));
//        inserver.start();
    }
}
