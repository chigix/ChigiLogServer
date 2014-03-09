package chigi.logserver.handler;

import chigi.logserver.collection.ServersCollection;
import chigi.logserver.config.BaseConfig;
import chigi.logserver.config.ClientConfig;
import chigi.logserver.config.Config;
import chigi.logserver.config.enums.ConfigStatus;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 服务器实例控制器
 *
 * @author 郷
 */
public abstract class ServerHandler extends BaseHandler implements Runnable{

    private ExecutorService threadPool;
    private ServerSocket serverSocket = null;
    private ConsoleHandler console = null;

    /**
     * 创建服务器对象
     * @param c
     */
    public ServerHandler(BaseConfig c) {
        super(c);
        ServersCollection.set(this.getId(), this);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * 启动服务器（可指定线程数）
     */
    @Override
    public void run() {
        Config config = (Config) this.getConfig();
        this.console = new ConsoleHandler(config);
        this.threadPool = Executors.newCachedThreadPool();
        try {
            this.serverSocket = new ServerSocket(config.getServerConfig().getPort());
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName())
                    .log(Level.SEVERE, String.valueOf(
                            config.getServerConfig().getPort()) + "端口已被其他进程占用，请检查", ex);
            if (config.getStatus() == ConfigStatus.RUNNING) {
                this.close();
            }
            return;
        }
        console.log("千木服务器进程启动");
        console.print();
        config.setStatus(ConfigStatus.RUNNING);
        boolean flag = true;
        while (flag) {
            switch (config.getStatus()) {
                case RUNNING:
                    Socket clientSocket = null;
                    try {
                        clientSocket = this.serverSocket.accept();
                    } catch (IOException ex) {
                        // 判断当前服务器进程是否仍在运行
                        // 仅在运行期间才抛出读取异常
                        if (config.getStatus() == ConfigStatus.RUNNING) {
                            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }else{
                            return;
                        }
                    }
                    ClientConfig clientConfig = new ClientConfig(this, clientSocket);
                    this.threadPool.execute(this.getClientHandler(clientConfig));
                    break;
                case INIT:
                case HALTED:
                case SUSPENDED:
                default:
                    // 若当前服务器已停止，则跳出循环
                    flag = false;
                    break;
            }
        }
    }

    /**
     * 关闭当前服务器线程
     */
    public void close() {
        Config config = (Config) this.getConfig();
        switch (config.getStatus()) {
            case RUNNING:
                break;
            case INIT:
            case SUSPENDED:
            case HALTED:
            default:
                System.out.println("当前服务器线程已停止。");
                return;
                // break;
        }
        config.setStatus(ConfigStatus.HALTED);
        this.threadPool.shutdown();
        System.out.println("所有连接将在60秒内全部结束");
        try {
            if (!this.threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                this.threadPool.shutdownNow();
                if (!this.threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.out.println("线程池关闭失败");
                }
            }
        } catch (InterruptedException ex) {
            this.threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
        try {
            this.serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, "当前服务器已关闭", ex);
        }
        this.serverSocket = null;
        System.gc();
        System.out.println("【~~千木服务器进程停止~~】");
        config.setStatus(ConfigStatus.HALTED);
    }

    /**
     * 获取 客户端连接实例
     */
    public abstract ClientHandler getClientHandler(ClientConfig config);
}
