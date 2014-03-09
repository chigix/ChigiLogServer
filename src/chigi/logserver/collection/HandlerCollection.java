/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver.collection;

import chigi.logserver.handler.BaseHandler;
import chigi.logserver.handler.ClientHandler;
import chigi.logserver.handler.ConsoleHandler;
import chigi.logserver.handler.ServerHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 所有实例总集合
 * @author 郷
 */
public class HandlerCollection {

    /**
     * 所有类型-实例序列总映射
     */
    public static final HashMap<Class,List<BaseHandler>> INSTANCE_MAP;
    static {
        INSTANCE_MAP = new HashMap();
        INSTANCE_MAP.put(ServerHandler.class, new ArrayList<BaseHandler>());
        INSTANCE_MAP.put(ClientHandler.class, new ArrayList<BaseHandler>());
        INSTANCE_MAP.put(ConsoleHandler.class, new ArrayList<BaseHandler>());
    }
    
    public static Integer push(BaseHandler handler){
        for (Class type : INSTANCE_MAP.keySet()) {
            Class target_type = handler.getClass();
            while (!target_type.equals(Object.class)) {
                if (target_type.equals(type)) {
                    INSTANCE_MAP.get(type).add(handler);
                    return INSTANCE_MAP.get(type).indexOf(handler);
                }
                target_type = target_type.getSuperclass();
            }
        }
        return null;
    }
}
