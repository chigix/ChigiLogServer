package chigi.logserver.handler;

import chigi.logserver.collection.ConsolesCollection;
import chigi.logserver.config.BaseConfig;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * 控制台实例对象
 * @author 郷
 */
public class ConsoleHandler extends BaseHandler{

    public ConsoleHandler(BaseConfig config) {
        super(config);
    }
    
    private ArrayList msg = new ArrayList();
    private static final PrintWriter TERMINAL;
    private static final OutputStreamWriter BYTE_WRITER;

    static{
        TERMINAL = new PrintWriter(System.out,true);
        BYTE_WRITER = new OutputStreamWriter(System.out);
    }
    
    /**
     * 加入需要被调试的信息
     * @param msg
     * @return 
     */
    public ConsoleHandler log(String msg){
        if (msg != null && !msg.isEmpty()) {
            this.msg.add("[" + new Date() + "] " + "\"" + msg + "\"");
        }
        return this;
    }

    /**
     * 加入需要被调试的信息
     * @param prefix
     * @param msg
     * @return 
     */
    public ConsoleHandler log(String prefix,String msg){
        if (msg == null) {
            msg = "NULL";
        }
        if (!msg.isEmpty()) {
            this.msg.add(prefix + " - - [" + new Date() + "] " + "\"" + msg + "\"");
        }
        return this;
    }
    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder("");
        for (Object item : this.msg) {
            tmp.append("\n").append(item);
        }
        return tmp.toString();
    }
    
    /**
     * 线程安全地输出
     */
    public void print(){
        synchronized(ConsoleHandler.TERMINAL){
            ConsoleHandler.TERMINAL.println(this);
        }
    }
    
}
