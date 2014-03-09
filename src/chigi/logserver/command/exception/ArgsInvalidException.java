package chigi.logserver.command.exception;

/**
 * 参数错误异常
 * @author 郷
 */
public class ArgsInvalidException extends Exception{

    public ArgsInvalidException() {
    }

    public ArgsInvalidException(String message) {
        super(message);
    }

    public ArgsInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArgsInvalidException(Throwable cause) {
        super(cause);
    }
    
}
