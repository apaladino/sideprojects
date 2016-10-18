package com.andyp.java8;

import java.io.FileReader;
import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NashornScriptEngineExamples {

	public static void main(String [] args) throws ScriptException, IOException{
		
		/*
		 * ScriptEngine example - using Nashorn script engine
		 */
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine se = sem.getEngineByName("nashorn");
		se.eval("function printStuff(stuff){ print(stuff); }");
		se.eval("printStuff('Hello world!')");
		
		/*
		 * Example running a javascript file
		 */
		String filePath = System.getProperty("user.dir") + "/files/sampleJavascript.js";
		se.eval(new FileReader(filePath));
		
		
		/*
		 * List different types of script engines
		 */
		sem.getEngineFactories().forEach(f -> System.out.println(f.getEngineName()));
	}
}
