package chigi.logserver.config;

/**
 *
 * @author 郷
 */
public class ServerConfig extends BaseConfig{
    private int port = 8080;
    private String type = "LogInputServer";

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
