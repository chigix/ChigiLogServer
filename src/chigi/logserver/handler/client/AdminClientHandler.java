package chigi.logserver.handler.client;

import chigi.logserver.command.BaseCommand;
import chigi.logserver.command.exception.ArgsInvalidException;
import chigi.logserver.command.exception.CommandErrorException;
import chigi.logserver.command.exception.CommandNotExistsException;
import chigi.logserver.config.BaseConfig;
import chigi.logserver.config.ClientConfig;
import chigi.logserver.handler.ClientHandler;
import java.io.IOException;

/**
 *
 * @author 郷
 */
public class AdminClientHandler extends ClientHandler {

    public AdminClientHandler(ClientConfig config) {
        super(config);
    }

    @Override
    public void run() {
        try {
            getCharWriter().println("Welcome to the Chigi Server Admin");
            getCharWriter().println("Your Chigi connection id is " + getId());
            getCharWriter().println("Server Version: " + BaseConfig.VERSION + " (Apache 2 License)");
            getCharWriter().println("Type 'help' for help\n");
            getByteWriter().flush();
        } catch (IOException ex) {
            this.close();
            return;
        }
        String command = "";
        while (true) {
            // 实时判断连接是否断开
            try {
                getCharWriter().print("chigi>");
                getByteWriter().flush();
            } catch (IOException ex) {
                this.close();
                return;
            }
            try {
                command = getCharReader().readLine();
            } catch (IOException ex) {
                this.close();
                return;
            }
            if (command != null) {
                switch (command) {
                    case "help":
                        getCharWriter().println("For information about Chigi Server products and services, visit:");
                        getCharWriter().println("    https://github.com/chigix/ChigiLogServer");
                        getCharWriter().println();
                        getCharWriter().println("List of all CHIGI Server commands:");
                        getCharWriter().println("shutd\tShut down current running server.");
                        getCharWriter().flush();
                        break;
                    case "shutd":
                        getConsole().log("服务器停止运行");
                        getConsole().log("退出~~");
                        getConsole().print();
                        this.close();
                        getCharWriter().println("Server Shut down");
                        getCharWriter().println("EXIT");
                        getCharWriter().flush();
                        System.exit(0);
                        break;
                    default:
                        // 直接忽略空命令
                        if (command.length() < 1) {
                            break;
                        }
                        BaseCommand cmd = null;
                        try {
                            cmd = BaseCommand.buildCommand(command, this);
                        } catch (CommandNotExistsException ex) {
                            getCharWriter().println("Command " + ex.getCommandName() + " is INVALID");
                            break;
                        } catch (ArgsInvalidException ex) {
                            getCharWriter().println(ex.getMessage());
                            break;
                        } catch (CommandErrorException ex) {
                            getCharWriter().println(ex.getMessage());
                            break;
                        }
                        try {
                            cmd.run();
                        } catch (ArgsInvalidException ex) {
                            getCharWriter().println(ex.getMessage());
                            break;
                        } catch (CommandNotExistsException ex) {
                            getCharWriter().println("Command: " + ex.getCommandName() + " is INVALID!");
                            break;
                        }
                        break;
                }
            }
        }
    }

    @Override
    public void println(String msg) {
        this.getCharWriter().println(msg);
    }

}
