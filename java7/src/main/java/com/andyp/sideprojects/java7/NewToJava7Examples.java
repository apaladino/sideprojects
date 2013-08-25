package com.andyp.sideprojects.java7;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Andy
 * Date: 8/24/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewToJava7Examples {

    public static void main(String[] args){

        new NewToJava7Examples().runExamples();
    }

    private void runExamples() {
        runDiamondOperatorExample();
        runSwtichStatementAcceptsStringExample();
        runTryWithResourcesExample();
        runNumericLiteralsWithUnderscoresExample();
        runMultipleExceptionHandlingExample();
        runNioFileSystemExample();
    }

    private void runNioFileSystemExample() {
        /*
            Java 7 has a new NIO file system api
         */
        System.out.println("\nRunning runNioFileSystemExample...\n");

        Path path = Paths.get("src/main/resources/com/andyp/sideprojects/java7/users.txt");

        System.out.println("File Name:" + path.getFileName());
        System.out.println("Number of Names:" + path.getNameCount());
        System.out.println("File names: ");
        for(int i=0; i < path.getNameCount(); i++){
            System.out.println("\t" + path.getName(i));
        }

        System.out.println("File Parent:" + path.getParent());

    }

    private void runMultipleExceptionHandlingExample() {
        /*
            Java 7 allows you to catch multiple exceptions in the same
            catch block
         */
        System.out.println("\nRunning runMultipleExceptionHandlingExample...\n");
        try{
            int invalidNumber = formatNumber("####");
        }catch (NullPointerException | ClassCastException | NumberFormatException e){
            System.out.println(e.getClass().getName() + " exception occurred: " + e.getMessage());
        }
    }

    private int formatNumber(Object o) throws NullPointerException, ClassCastException, NumberFormatException{
        return Integer.parseInt((String)o);
    }

    private void runNumericLiteralsWithUnderscoresExample() {
        /*
            Java 7 allows you to have numeric litersls with underscores.

              ex: 1_000_000, instead of 1000000
         */
        System.out.println("\nRunning runNumericLiteralsWithUnderscoresExample...\n");
        int oneMillion = 1_000_000;

        System.out.println("Dr. Evil said: \"I'll blow up the world unless you pay " +
           "me $" + oneMillion + " dollars!\"");
    }

    private void runDiamondOperatorExample() {
        /*
            new <> diamond operator allows you to not have to specify a generic type
            in the new variable declaration
         */
        System.out.println("\nRunning runDiamonOperatorExample...\n");
        Map<String, String> nameToJobMap = new HashMap<>();

        nameToJobMap.put("Luke", "Fighter pilot");
        nameToJobMap.put("Obi Wan", "Jedi Knight");
        nameToJobMap.put("Darth Vader", "Sith Lord");

        for(String name : nameToJobMap.keySet()){
            System.out.println(name + "'s job is " + nameToJobMap.get(name));
        }
    }

    private void runSwtichStatementAcceptsStringExample() {
        /*
            Switch statements now allow for String parameters, which is cool
         */
        System.out.println("\nRunning runSwitchStatementAcceptsStringExample....\n");

        String[] characters = new String[] { "Fred", "Daphney", "Thelma", "Shaggy", "Scooby", "Old Man Witherspoon"};

        for(String character : characters){
            System.out.print("Character: " + character + " said: ");

            switch (character){
                case "Fred": { System.out.println("I love ascots!");
                    break;
                }
                case "Daphney" : {
                    System.out.println("Where's Shaggy and Scooby?");
                    break;
                }
                case "Thelma" : {
                    System.out.println("Jinkeez!");
                    break;
                }
                case "Shaggy" : {
                    System.out.println("ZOIKS!!");
                    break;
                }
                case "Scooby" : {
                    System.out.println("ROOBY ROOBY ROO!");
                    break;
                }
                case "Old Man Witherspoon":{
                    System.out.println("I would have gotten away with it too, " +
                            "if it weren't for those pesky kids!!");
                    break;
                }
            }
        }
    }

    private void runTryWithResourcesExample() {
        /*
            Try-with-resources.  All resource objects defined
            in try resouce declaration section need to subclass
            AutoCloseable.
         */
        System.out.println("\nRunning runTryWithResourcesExample....\n");
        System.out.println("Printing all users in users.txt");


        try( InputStream in = Files.newInputStream(
              Paths.get("src/main/resources/com/andyp/sideprojects/java7/users.txt"));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))){

            String line;
            while ( (line = reader.readLine()) != null ){
                System.out.println(line);
            }


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
