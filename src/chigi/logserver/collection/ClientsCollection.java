package chigi.logserver.collection;

import chigi.logserver.handler.ClientHandler;
import java.util.ArrayList;

/**
 *
 * @author 郷
 */
public class ClientsCollection {
    public static ClientHandler get(int id){
        return (ClientHandler) HandlerCollection.INSTANCE_MAP.get(ClientHandler.class).get(id);
    }
}
