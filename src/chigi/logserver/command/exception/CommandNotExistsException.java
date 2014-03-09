/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver.command.exception;

/**
 *
 * @author 郷
 */
public class CommandNotExistsException extends Exception{

    private String commandName = null;

    public String getCommandName() {
        return commandName;
    }

    
    public CommandNotExistsException(String commandName) {
        super("Command " + commandName + " Not Exists!");
        this.commandName = commandName;
    }

    public CommandNotExistsException(String commandName, Throwable cause) {
        super("Command " + commandName + " Not Exists!", cause);
        this.commandName = commandName;
    }

    public CommandNotExistsException(Throwable cause) {
        super(cause);
    }
    
}
