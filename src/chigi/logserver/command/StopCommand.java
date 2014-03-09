package chigi.logserver.command;

import chigi.logserver.collection.HandlerCollection;
import chigi.logserver.command.exception.ArgsInvalidException;
import chigi.logserver.command.exception.CommandErrorException;
import chigi.logserver.command.exception.CommandNotExistsException;
import chigi.logserver.handler.ServerHandler;

/**
 *
 * @author 郷
 */
public class StopCommand extends BaseCommand {

    private String type_to_stop = null;
    private int id_to_stop = -1;

    @Override
    public String getCommandName() {
        return "stop";
    }

    @Override
    public void initProperties() throws ArgsInvalidException {
        for (Argument arg : getArgs()) {
            switch (arg.getName()) {
                case "__SYS__1":
                    this.type_to_stop = (String) arg.getValue();
                    break;
                case "__SYS__2":
                    try {
                        this.id_to_stop = Integer.parseInt((String) arg.getValue());
                    } catch (NumberFormatException numberFormatException) {
                        throw new ArgsInvalidException("Please specific an valid port.");
                    }
                    break;
                default:
                    throw new ArgsInvalidException("Error argument near "
                            + (arg.getName().startsWith("__SYS__")
                            ? ((String) arg.getValue())
                            : ((String) arg.getName())));
            }
        }
    }

    @Override
    public void run() throws ArgsInvalidException, CommandNotExistsException {
        for (Argument arg : getArgs()) {
            switch (type_to_stop) {
                case "server":
                    ServerHandler target_server
                            = (ServerHandler) HandlerCollection.INSTANCE_MAP.get(ServerHandler.class).get(id_to_stop);
                    try {
                        target_server.close();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new ArgsInvalidException("Please specific an valid port.");
                    } catch (NullPointerException e) {
                        throw new ArgsInvalidException("There is no instance for server-" + id_to_stop);
                    }
                    getClient().println(target_server.getClass().getSimpleName() + " [Stopped] : " + id_to_stop);
                    target_server = null;
                    System.gc();
                    break;
                default:
                    throw new ArgsInvalidException(type_to_stop + " is not valid server module.");
            }
        }
    }

}
