/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver.command;

import chigi.logserver.command.exception.ArgsInvalidException;

/**
 *
 * @author 郷
 */
public class StartCommand extends BaseCommand {
    
    private String type_to_start = null;
    private String sysArg2 = null;

    @Override
    public String getCommandName() {
        return "start";
    }

    @Override
    public void run() throws ArgsInvalidException {
        if (type_to_start.equals("LogInputServer")) {
            StartLogInputServerCommand cmd = new StartLogInputServerCommand();
            try {
                cmd.setPort(Integer.parseInt(sysArg2));
            } catch (NumberFormatException numberFormatException) {
                throw new ArgsInvalidException("Please specific an valid port.");
            }
            BaseCommand.setClientToCommand(this.getClient(), cmd);
            cmd.run();
        } else if (type_to_start.equals("LogOutputServer")) {
            StartLogOutputServerCommand cmd = new StartLogOutputServerCommand();
            try {
                cmd.setPort(Integer.parseInt(sysArg2));
            } catch (NumberFormatException numberFormatException) {
                throw new ArgsInvalidException("Please specific an valid port.");
            }
            BaseCommand.setClientToCommand(this.getClient(), cmd);
            cmd.run();
        }else{
            throw new ArgsInvalidException(type_to_start + " is not valid server module.");
        }
    }

    @Override
    public void initProperties() throws ArgsInvalidException {
        for (Argument arg : getArgs()) {
            switch (arg.getName()) {
                case "__SYS__1":
                    this.type_to_start = (String) arg.getValue();
                    break;
                case "__SYS__2":
                    this.sysArg2 = (String) arg.getValue();
                    break;
                default:
                    throw new ArgsInvalidException("Error argument near " 
                            + (arg.getName().startsWith("__SYS__")?
                                    ((String) arg.getValue())
                                    :((String) arg.getName())));
            }
        }
    }
    
}
