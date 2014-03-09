/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver.config;

import chigi.logserver.handler.ServerHandler;
import java.net.Socket;

/**
 * 客户端连接配置
 * @author 郷
 */
public class ClientConfig extends BaseConfig{
    /**
     * 当前连接的目标服务器实例对象
     */
    private ServerHandler serverHandler;
    
    /**
     * 当前连接线程的客户端 Socket 对象
     */
    private Socket clientSocket;
    
    /**
     * 当前连接实例ID
     */
    private Integer id;
    
    private ClientConfig(){}
    
    public ClientConfig(ServerHandler serverHandler, Socket clientSocket) {
        this.serverHandler = serverHandler;
        this.clientSocket = clientSocket;
    }

    public ServerHandler getServerHandler() {
        return serverHandler;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
