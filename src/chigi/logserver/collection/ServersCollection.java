/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver.collection;

import chigi.logserver.handler.ServerHandler;

/**
 * 服务器实例集合
 * @author 郷
 */
public class ServersCollection{
    public static ServerHandler get(int id){
        return (ServerHandler) HandlerCollection.INSTANCE_MAP.get(ServerHandler.class).get(id);
    }
}
