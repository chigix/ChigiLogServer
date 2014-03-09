package chigi.logserver.collection;

import chigi.logserver.handler.ConsoleHandler;
import java.util.ArrayList;

/**
 * 控制台集合类
 * @author 郷
 */
public class ConsolesCollection{
    private static ArrayList<ConsoleHandler> CONSOLES = new ArrayList<ConsoleHandler>();
    public static ConsoleHandler get(int id){
        return CONSOLES.get(id);
    }
    public static ConsoleHandler set(int id, ConsoleHandler console){
        CONSOLES.set(id, console);
        return console;
    }
}
