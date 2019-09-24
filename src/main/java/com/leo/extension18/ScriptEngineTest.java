package com.leo.extension18;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 脚本引擎
 */
public class ScriptEngineTest {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        System.out.println(engine.getClass().getName());

        System.out.println("Result ：" + engine.eval("function f(){return 10;};f()*24"));
    }
}
