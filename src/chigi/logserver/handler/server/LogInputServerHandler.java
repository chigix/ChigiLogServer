package chigi.logserver.handler.server;

import chigi.logserver.collection.ClientsCollection;
import chigi.logserver.config.BaseConfig;
import chigi.logserver.config.ClientConfig;
import chigi.logserver.handler.ClientHandler;
import chigi.logserver.handler.ServerHandler;

/**
 * 日志输入类型服务器实例
 * @author 郷
 */
public class LogInputServerHandler extends ServerHandler{

    public LogInputServerHandler(BaseConfig c) {
        super(c);
    }

    @Override
    public ClientHandler getClientHandler(ClientConfig config) {
        if (config.getId() == null) {
            return null;
        }else if(ClientsCollection.get(config.getId()) == null){
            return null;
        }else{
            return ClientsCollection.get(config.getId());
        }
    }

}
