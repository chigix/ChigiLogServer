/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chigi.logserver.handler.client;

import chigi.logserver.config.ClientConfig;
import chigi.logserver.handler.ClientHandler;
import java.io.IOException;

/**
 *
 * @author 郷
 */
public class LogOutputClientHandler extends ClientHandler {

    public LogOutputClientHandler(ClientConfig config) {
        super(config);
    }

    @Override
    public void run() {
        while (true) {
            try {
                getByteWriter().write(32);
                getByteWriter().write(8);
                getByteWriter().flush();
            } catch (IOException ex) {
                this.close();
                return;
            }
            try {
                String r = getCharReader().readLine();
            } catch (IOException ex) {
                this.close();
                return;
            }
        }
    }

    @Override
    public void println(String msg) {
        this.getCharWriter().println(msg);
        try {
            getByteWriter().flush();
        } catch (IOException ex) {
            this.close();
            return;
        }
    }
    
}
