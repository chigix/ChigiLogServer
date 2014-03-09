/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver.handler.client;

import chigi.logserver.config.ClientConfig;
import chigi.logserver.handler.ClientHandler;

/**
 * 日志输出连接实例
 * @author 郷
 */
public class LogOutputServerHandler extends ClientHandler{

    public LogOutputServerHandler(ClientConfig config) {
        super(config);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
