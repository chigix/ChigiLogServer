/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chigi.logserver.handler.client;

import chigi.logserver.config.ClientConfig;
import chigi.logserver.handler.ClientHandler;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 日志输入客户端连接实例对象
 *
 * @author 郷
 */
public class LogInputClientHandler extends ClientHandler {

    public LogInputClientHandler(ClientConfig config) {
        super(config);
    }

    @Override
    public void run() {
        int oneByte = 0;
        List bytes = new LinkedList();
        while (true) {
            // 实时判断连接是否断开
            try {
                getByteWriter().write(32);
                getByteWriter().write(8);
                getByteWriter().flush();
            } catch (IOException ex) {
                this.close();
                return;
            }
            try {
                oneByte = getByteReader().read();
            } catch (IOException ex) {
                this.close();
                return;
            }
            if (oneByte == (byte) '\n' || oneByte == (byte) '\r') {
                byte[] bytes_arr = new byte[bytes.size()];
                for (int i = 0; i < bytes.size(); i++) {
                    Integer b = (Integer) bytes.get(i);
                    bytes_arr[i] = b.byteValue();
                }
                getConsole().log(new String(bytes_arr));
                getConsole().print();
                bytes.clear();
                oneByte = 0;
            } else {
                bytes.add(oneByte);
            }
        }
    }

}
