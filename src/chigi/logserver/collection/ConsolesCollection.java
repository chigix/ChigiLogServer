package chigi.logserver.collection;

import chigi.logserver.handler.ConsoleHandler;
import java.util.ArrayList;

/**
 * 控制台集合类
 * @author 郷
 */
public class ConsolesCollection{
    public static ConsoleHandler get(int id){
        return (ConsoleHandler) HandlerCollection.INSTANCE_MAP.get(ConsoleHandler.class).get(id);
    }
}
