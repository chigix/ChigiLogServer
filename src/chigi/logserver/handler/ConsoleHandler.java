package chigi.logserver.handler;

import chigi.logserver.config.BaseConfig;
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
    
    private ArrayList<String> msg = new ArrayList<String>();
    private static final PrintWriter TERMINAL;

    static{
        TERMINAL = new PrintWriter(System.out,true);
    }
    
    /**
     * 加入需要被调试的信息
     * @param msg
     * @return 
     */
    public ConsoleHandler log(String msg){
        StringBuilder builder = new StringBuilder("[" + new Date() + "] ");
        builder.append("\"");
        builder.append(msg);
        builder.append("\"");
        if (msg != null && !msg.isEmpty()) {
            this.msg.add(builder.toString());
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
        StringBuilder builder = new StringBuilder(prefix);
        builder.append(" - - [").append(new Date()).append("] ").append("\"").append(msg).append("\"");
        if (!msg.isEmpty()) {
            this.msg.add(builder.toString());
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
            this.msg.clear();
        }
    }
    
}
