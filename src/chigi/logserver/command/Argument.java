/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chigi.logserver.command;

/**
 * 参数对象
 * @author 郷
 */
public class Argument {
    private String name;
    private Object value;

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    

    public Argument(String name) {
        this.name = name;
    }
    
}
