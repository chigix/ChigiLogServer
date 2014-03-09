/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver.config.exception;

/**
 *
 * @author 郷
 */
public class ConfigInitException extends Exception{

    public ConfigInitException() {
    }

    public ConfigInitException(String message) {
        super(message);
    }

    public ConfigInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigInitException(Throwable cause) {
        super(cause);
    }
    
}
