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
public class CommandErrorException extends Exception{

    public CommandErrorException() {
    }

    public CommandErrorException(String message) {
        super(message);
    }

    public CommandErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandErrorException(Throwable cause) {
        super(cause);
    }
    
}
