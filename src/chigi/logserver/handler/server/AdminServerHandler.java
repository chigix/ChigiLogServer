package chigi.logserver.handler.server;

import chigi.logserver.collection.ClientsCollection;
import chigi.logserver.config.BaseConfig;
import chigi.logserver.config.ClientConfig;
import chigi.logserver.handler.ClientHandler;
import chigi.logserver.handler.ServerHandler;
import chigi.logserver.handler.client.AdminClientHandler;

/**
 *
 * @author 郷
 */
public class AdminServerHandler extends ServerHandler{

    public AdminServerHandler(BaseConfig c) {
        super(c);
    }

    @Override
    public ClientHandler getClientHandler(ClientConfig config) {
        if (config.getId() == null) {
            return new AdminClientHandler(config);
        }else if(ClientsCollection.get(config.getId()) == null){
            return null;
        }else{
            return ClientsCollection.get(config.getId());
        }
    }

    @Override
    public String getName() {
        return "千木管理员服务器";
    }
    
}
