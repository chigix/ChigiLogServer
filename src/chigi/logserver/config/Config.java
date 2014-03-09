package chigi.logserver.config;

import chigi.logserver.config.enums.ConfigStatus;

/**
 * 服务器实例配置对象
 * @author 郷
 */
public class Config extends BaseConfig{
    /**
     * 当前整体线程状态
     */
    private ConfigStatus status = ConfigStatus.INIT;
    private ServerConfig serverConfig = new ServerConfig();
    private ConsoleConfig consoleConfig = new ConsoleConfig();

    public Config(int port) {
        serverConfig.setPort(port);
    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public void setServerConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public ConfigStatus getStatus() {
        return status;
    }

    public void setStatus(ConfigStatus status) {
        this.status = status;
    }

    public ConsoleConfig getConsoleConfig() {
        return consoleConfig;
    }

    public void setConsoleConfig(ConsoleConfig consoleConfig) {
        this.consoleConfig = consoleConfig;
    }

}
