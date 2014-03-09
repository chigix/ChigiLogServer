/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver.handler;

import chigi.logserver.config.BaseConfig;
import chigi.logserver.config.ClientConfig;
import chigi.logserver.config.Config;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 客户端连接实例对象
 * @author 郷
 */
public abstract class ClientHandler extends BaseHandler implements Runnable{

    public ClientHandler(ClientConfig config) {
        super(config);
        this.clientSocket = config.getClientSocket();
        this.console = new ConsoleHandler((BaseConfig)((Config) config.getServerHandler().getConfig()).getConsoleConfig());
        try {
            InputStream in = this.clientSocket.getInputStream();
            OutputStream out = this.clientSocket.getOutputStream();
            this.byteReader = new InputStreamReader(in);
            this.byteWriter = new OutputStreamWriter(out);
            this.charReader = new BufferedReader(this.byteReader);
            this.charWriter = new PrintWriter(this.byteWriter);
            this.byteBufferedWriter = new BufferedOutputStream(out);
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName())
                    .log(Level.SEVERE, "I/O 控制出错", ex);
        }
    }
    
    /**
     * 关闭当前连接
     */
    public void close(){
        try {
            this.byteBufferedWriter.close();
            this.byteReader.close();
            this.byteWriter.close();
            this.charReader.close();
            this.charWriter.close();
            this.clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            getConsole().log(this.getClientSocket().getInetAddress().getHostAddress() + "连接断开");
            getConsole().print();
            this.console = null;
            System.gc();
        }
    }
    
    /**
     * 当前连接线程 SOCKET
     */
    private Socket clientSocket;
    /**
     * 当前线程的字符输出流
     */
    private PrintWriter charWriter;
    /**
     * 当前线程的字节输出流
     */
    private BufferedOutputStream byteBufferedWriter;
    /**
     * 当前线程的字符输入流
     */
    private BufferedReader charReader;
    /**
     * 当前线程的字节输入流
     */
    private InputStreamReader byteReader;
    /**
     * 当前线程的字节输出流
     */
    private OutputStreamWriter byteWriter;

    /**
     * 当前线程的对应终端对象
     */
    private ConsoleHandler console = null;
    
    public ConsoleHandler getConsole() {
        return console;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public PrintWriter getCharWriter() {
        return charWriter;
    }

    public BufferedOutputStream getByteBufferedWriter() {
        return byteBufferedWriter;
    }

    public BufferedReader getCharReader() {
        return charReader;
    }

    public InputStreamReader getByteReader() {
        return byteReader;
    }

    public OutputStreamWriter getByteWriter() {
        return byteWriter;
    }
    
    
}
