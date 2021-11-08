/*
    Name: Mitchell Williamson
    Course: CSC 422
    Email: williamm15@csp.edu
    Due Date: 11/14/2021
    Assignment: Assignment 2, GitHub issue and project
 */
package williamsoncsc422assignment1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class WilliamsonCSC422Assignment1 {
// As Pet Objects are created, they are added to this ArrayList
public static ArrayList<Pet> petList = new ArrayList<>();
// Using Properties instead of a standard .txt file. This is because a key can be assigned to each line, making searching and updating easier if it is needed later.
public static Properties p = new Properties();
    public static void main(String[] args) throws IOException {
        // validator is what controls the while loop. While true, the program runs. If it is false, the program is closed
        boolean validator =  true;
        Scanner sc = new Scanner(System.in);
        try {
            InputStream is = new FileInputStream("database.properties");
            p.load(is);
            int id = 0;
            while(p.getProperty(String.valueOf(id)) != null){
                String dataSplit[] = p.getProperty(String.valueOf(id)).split(":");
                int age = Integer.parseInt(dataSplit[1]);
                Pet newPet = new Pet(dataSplit[0],age);
                petList.add(newPet);
                id++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: There was an issue loading the database.");
        }
        do {
            // calling the menuDisplay method
            menuDisplay();
            String strInput = sc.nextLine();
            // checking is the input is an int
            boolean inputCheck = isInt(strInput);
            if (inputCheck){
                int input = Integer.parseInt(strInput);
                // Switch controls output based on what option is typed by the user
                switch(input){
                    case 1: 
                        // calling the petDisplay method
                        petDisplay();
                        break;
                    case 2:
                        // calling the addPet method
                        addPet();
                        break;
                    case 3:
                        // calling removePet method
                        removePet();
                        break;
                    case 4:
                        // switching the validator boolean from true to false. This breaks the while loop and closes to program
                        System.out.println("Goodbye!");
                        validator = false;
                        break;
                    default:
                        System.out.println("Error: Please type a number between 1 and 4.");
                        break;
                }
            }
            else{
                System.out.println("Error: Please type an integer as your choice.");
            }
        }while(validator);
    }
    // this method displays the menu of options to the user
    public static void menuDisplay(){
        System.out.println("\nWhat would you like to do?");
        System.out.println("1) View all pets");
        System.out.println("2) Add new pets");
        System.out.println("3) Remove an existing pet");
        System.out.println("4) Exit program");
        System.out.print("Your choice: ");
    }
    // this method displays the data that is the the petList ArrayList to the user
    public static void petDisplay(){
        System.out.println("\n+---------------------+");
        System.out.printf("|%-3s", " ID ");
        System.out.printf("|%-10s", " NAME ");
        System.out.printf("|%-4s", " AGE ");
        System.out.print("|\n");
        System.out.println("+---------------------+");
        for (int i = 0; i < petList.size(); i++){
            System.out.printf("|%3d" , i);
            System.out.print(" | ");
            System.out.printf("%-9s" , petList.get(i).getName());
            System.out.printf("|%4d" , petList.get(i).getAge());
            System.out.print(" |\n");
        }
        System.out.println("+---------------------+");
        System.out.println(petList.size() + " rows in set.\n");
    }
    public static void addPet(){
        // adding a new line for formatting
        System.out.println();
        // i tracks how many new pets are added
        int i = 0;
        // this loop runs until the break statement is read when "done" is typed by the user
        while(true){
            if (petList.size() < 5){
                System.out.print("Add pet (name, age): ");
                Scanner sc = new Scanner(System.in);
                String petData = sc.nextLine();
                // checking if "done" was typed
                if (petData.toLowerCase().equals("done")){
                    // checking if only one pet was added. If so, printing grammatically correct "pet" instead of "pets"
                    if(i == 1){
                        System.out.println(i + " pet added.");
                    }
                    else{
                        System.out.println(i + " pets added.");
                    }
                    break;
                }
                else{
                    // splitting the user input and checking if there are exactly 2 indices in the array
                    String[] splitData = petData.split(" ");
                    if (splitData.length == 2){
                        // calling isInt() method to check if what is supposed to be the age is an int
                        Boolean intCheck = isInt(splitData[1]);
                        // if intCheck is true, the pet is added
                        if (intCheck){
                            int age = Integer.parseInt(splitData[1]);
                            if (age < 21 && age > 0){
                                Pet newPet = new Pet(splitData[0], age);
                                petList.add(newPet);
                                i++;
                                try{
                                    OutputStream os = new FileOutputStream("database.properties");
                                    // data entered in the property overrides existing pet data, so we are adding the pets that already were entered back into the database here.
                                    for(int y = 0; y < petList.size(); y++){
                                        p.setProperty(String.valueOf(y) , petList.get(y).getName() + ":" + petList.get(y).getAge());
                                    }
                                    p.store(os, null);
                                }
                                catch(IOException e){
                                    System.out.println("Error: An issue occured saving your pet.");
                                }
                            }
                            else{
                                System.out.println("Error: Pet age must be between 1 and 20.");
                            }
                        }
                        else{
                            System.out.println("Error: Please type an integer as an age.");
                        }
                    }
                    // if the array size is anything other than 2, the following error is printed
                    else{
                        System.out.println("Error: Please enter a one word name and an age separated by a space.");
                    }
                }
            }
            else{
                System.out.println("Error: The database only supports up to five pets.");
            }
        }
    }
    public static void removePet(){
        // calling petDisplay method to show all pets to the user
        petDisplay();
        while(true){
            System.out.print("Enter the pet ID to remove: ");
            Scanner sc = new Scanner(System.in);
            String strId = sc.nextLine();
            // calling isInt method for input validation
            boolean idCheck = isInt(strId);
            if (idCheck){
                int id = Integer.parseInt(strId);
                if (id < petList.size()){
                    try{
                        OutputStream os = new FileOutputStream("database.properties");
                        // clearing the data that is stored in the property file
                        p.clear();
                        for (int i = 0; i < petList.size(); i++){
                            if (i != id){
                                p.setProperty(String.valueOf(id) , petList.get(i).getName() + ":" + petList.get(i).getAge());
                            }
                        }
                        p.store(os, null);
                    }
                    catch(IOException e){
                       System.out.println("Error: There was an issue removing your pet.");
                    }
                    // printing to the user the pet that is being removed from the database
                    System.out.println(petList.get(id).getName() + " " + petList.get(id).getAge() + " is removed.");
                    // removing the pet from the database
                    petList.remove(id);
                    break;
                }
                else{
                   System.out.println("Error: The ID entered is invalid.");
                }
            }
            else{
                System.out.println("Error: The ID entered is not an integer.");
            }
        }
    }
    // isInt() is a method for checking if an input can be converted to an int. Returns true or false
    public static boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException e ) {
            return false;
        }
    }
}
// defining pet class with constructor, getter, and setter methods
class Pet{
    private String name;
    private int age;
    public Pet(String name, int age){
        this.name = name;
        this.age = age;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
}