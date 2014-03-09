/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver;

import chigi.logserver.config.Config;
import chigi.logserver.handler.server.AdminServerHandler;

/**
 *
 * @author 郷
 */
public class Main {
    public static void main(String[] args){
        Config config = new Config(8081);
        Thread server = new Thread(new AdminServerHandler(config));
        server.start();
    }
}
