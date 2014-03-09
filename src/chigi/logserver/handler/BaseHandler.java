/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver.handler;

import chigi.logserver.config.BaseConfig;

/**
 *
 * @author 郷
 */
abstract public class BaseHandler{
    private static Object SYNC = new Object();
    private static int NEXT_ID = 0;
    private static int getNextId(){
        synchronized(SYNC){
            return ++NEXT_ID;
        }
    }
    
    private int id;
    private BaseConfig config;
    public int getId() {
        return id;
    }

    public BaseConfig getConfig() {
        return config;
    }

    public BaseHandler(BaseConfig config) {
        this.config = config;
        this.id = getNextId();
    }
    
}
