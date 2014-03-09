package chigi.logserver.command;

import chigi.logserver.command.exception.ArgsInvalidException;
import chigi.logserver.command.exception.CommandErrorException;
import chigi.logserver.command.exception.CommandNotExistsException;
import chigi.logserver.handler.ClientHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command 基类
 * @author 郷
 */
public abstract class BaseCommand {
    private ClientHandler client;
    public abstract String getCommandName();
    public abstract void initProperties() throws ArgsInvalidException;
    public abstract void run() throws ArgsInvalidException,CommandNotExistsException;
    public static BaseCommand buildCommand(String command, ClientHandler client) 
            throws CommandNotExistsException, ArgsInvalidException,CommandErrorException{
        if (command == null || command.length() <1) {
            throw new CommandErrorException("COMMAND ERROR");
        }
        BaseCommand command_to_run = null;
        List<Argument> args = null;
        String[] parts = command.split("\"");
        boolean even_flag = false;
        Argument to_be_set_string_value = null;
        for (String part : parts) {
            if (even_flag) {
                // 当前为偶数个，应为引号内字符串内容
                if (to_be_set_string_value == null) {
                    // 当前 字符串本身 整体作为一个 完整参数对象
                    Argument current_new_arg = new Argument("__SYS__");
                    current_new_arg.setValue(part);
                    args.add(current_new_arg);
                }else{
                    // 当前字符串是作为最近一次的待设置值的参数的值
                    to_be_set_string_value.setValue(part);
                    to_be_set_string_value = null;
                }
            }else{
                // 当前为奇数个，继续进行后续解析
                String[] tmpSubs = part.split("\\s+");
                for (String tmpSub : tmpSubs) {
                    if (command_to_run == null) {
                        // 当前字符串内容为目标命令名称
                        try {
                            command_to_run = (BaseCommand) Class.forName("chigi.logserver.command." + 
                                    (new StringBuilder())
                                            .append(Character.toUpperCase(tmpSub.charAt(0)))
                                            .append(tmpSub.substring(1)).toString()
                                    + "Command").newInstance();
                            args = command_to_run.getArgs();
                            command_to_run.client = client;
                        } catch (ClassNotFoundException ex) {
                            throw new CommandNotExistsException(tmpSub, ex);
                        } catch (InstantiationException ex) {
                            Logger.getLogger(BaseCommand.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(BaseCommand.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (StringIndexOutOfBoundsException ex){
                            throw new CommandErrorException("COMMAND ERROR!", ex);
                        }
                    }else{
                        // 普通的参数 或 参数键值对的键部分
                        if (tmpSub.startsWith("=")) {
                            // 说明命令中存在 `=` 前有空号的错误写法
                            throw new ArgsInvalidException("Wrong syntax near `" + tmpSub + "` !");
                        }else if (tmpSub.endsWith("=")) {
                            // 键值对型参数的键部分，值需要在下一循环中追加
                            Argument current_new_arg = new Argument(tmpSub.substring(0, tmpSub.length()-1));
                            args.add(current_new_arg);
                            to_be_set_string_value = current_new_arg;
                        }else if (tmpSub.indexOf("=")!=-1) {
                            // 一个完整键值对，直接拆分成key-value放入Argument 对象中
                            String[] kv = tmpSub.split("=");
                            Argument current_new_arg = new Argument(kv[0]);
                            current_new_arg.setValue(kv[1]);
                            args.add(current_new_arg);
                        }else{
                            //普通的参数，直接创建参数对象，加入对象集合
                            Argument current_new_arg = new Argument("__SYS__");
                            current_new_arg.setValue(tmpSub);
                            args.add(current_new_arg);
                        }
                    }
                }
            }
            even_flag = !even_flag;
        }
        int sys_count = 0;
        for (int i = 0; i < args.size(); i++) {
            if (args.get(i).getName().equals("__SYS__")) {
                Argument sysarg = new Argument("__SYS__" + ++sys_count);
                sysarg.setValue(args.get(i).getValue());
                args.set(i, sysarg);
            }
        }
        command_to_run.initProperties();
        return command_to_run;
    }
    
    private List<Argument> args = new ArrayList<Argument>();

    public List<Argument> getArgs() {
        return args;
    }

    public ClientHandler getClient() {
        return client;
    }
    
    public static void setClientToCommand(ClientHandler handler,BaseCommand cmd){
        cmd.client = handler;
    }
    
}