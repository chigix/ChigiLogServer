/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chigi.logserver.command;

import chigi.logserver.config.Config;
import chigi.logserver.handler.server.LogInputServerHandler;

/**
 *
 * @author 郷
 */
public class StartLogInputServerCommand extends StartCommand {

    private Integer port = null;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public void run() {
        Config log_server_config = new Config(this.port);
        LogInputServerHandler handler = new LogInputServerHandler(log_server_config);
        Thread log_server = new Thread(handler);
        log_server.start();
        getClient().println("  LogInputServer [running]: " + String.valueOf(handler.getId()));
    }

}
