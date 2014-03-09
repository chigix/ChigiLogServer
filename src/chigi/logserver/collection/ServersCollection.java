/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver.collection;

import chigi.logserver.handler.ServerHandler;
import java.util.ArrayList;

/**
 * 服务器实例集合
 * @author 郷
 */
public class ServersCollection{
    private static ArrayList<ServerHandler> CONSOLES = new ArrayList<ServerHandler>();
    public static ServerHandler get(int id){
        return CONSOLES.get(id);
    }
    public static ServerHandler set(int id, ServerHandler console){
        CONSOLES.set(id, console);
        return console;
    }
}
