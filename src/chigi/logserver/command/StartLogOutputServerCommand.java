/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver.command;

import chigi.logserver.command.exception.ArgsInvalidException;
import chigi.logserver.config.Config;
import chigi.logserver.handler.server.LogOutputServerHandler;

/**
 *
 * @author 郷
 */
public class StartLogOutputServerCommand extends StartCommand{
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
        LogOutputServerHandler handler = new LogOutputServerHandler(log_server_config);
        Thread log_server = new Thread(handler);
        log_server.start();
        getClient().println("  LogOutputServer [running]: " + String.valueOf(handler.getId()));
    }
}
