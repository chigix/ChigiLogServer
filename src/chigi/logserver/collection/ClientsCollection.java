package chigi.logserver.collection;

import chigi.logserver.handler.ClientHandler;
import java.util.ArrayList;

/**
 *
 * @author 郷
 */
public class ClientsCollection {
    private static ArrayList<ClientHandler> CONSOLES = new ArrayList<ClientHandler>();
    public static ClientHandler get(int id){
        return CONSOLES.get(id);
    }
    public static ClientHandler set(int id, ClientHandler console){
        CONSOLES.set(id, console);
        return console;
    }
}
