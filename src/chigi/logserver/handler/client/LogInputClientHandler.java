/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver.handler.client;

import chigi.logserver.config.ClientConfig;
import chigi.logserver.handler.ClientHandler;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 郷
 */
public class LogInputClientHandler extends ClientHandler{

    public LogInputClientHandler(ClientConfig config) {
        super(config);
    }

    @Override
    public void run() {
        int oneByte = -1;
        List bytes = new LinkedList();
        while (true) {
            // 实时判断连接是否断开
            try {
                getByteWriter().write(0);
                getByteWriter().flush();
            } catch (IOException ex) {
                this.close();
                return;
            }
            try {
                oneByte = getByteReader().read();
                if (oneByte == (byte) '\n') {
                    byte[] bytes_arr = new byte[bytes.size()];
                    for (int i=0;i<bytes.size();i++) {
                        Integer b = (Integer) bytes.get(i);
                        bytes_arr[i] = (b).byteValue();
                    }
                    System.out.println(new String(bytes_arr));
                    System.out.println(bytes.size());
                    bytes.clear();
                }else{
                    bytes.add(oneByte);
                }
            } catch (IOException ex) {
                Logger.getLogger(LogInputClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
