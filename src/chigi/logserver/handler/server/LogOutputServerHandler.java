/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver.handler.server;

import chigi.logserver.collection.ClientsCollection;
import chigi.logserver.config.BaseConfig;
import chigi.logserver.config.ClientConfig;
import chigi.logserver.handler.ClientHandler;
import chigi.logserver.handler.ServerHandler;
import chigi.logserver.handler.client.LogOutputClientHandler;

/**
 *
 * @author 郷
 */
public class LogOutputServerHandler extends ServerHandler{

    public LogOutputServerHandler(BaseConfig c) {
        super(c);
    }

    @Override
    public ClientHandler getClientHandler(ClientConfig config) {
        if (config.getId() == null) {
            return new LogOutputClientHandler(config);
        }else if(ClientsCollection.get(config.getId()) == null){
            return null;
        }else{
            return ClientsCollection.get(config.getId());
        }
    }

    @Override
    public String getName() {
        return "千木日志同步输出服务器";
    }
    
}
