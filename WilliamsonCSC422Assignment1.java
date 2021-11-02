/*
    Name: Mitchell Williamson
    Course: CSC 422
    Email: williamm15@csp.edu
    Due Date: 11/7/2021
    Assignment: Assignment 1, version control 
 */
package williamsoncsc422assignment1;

import java.util.ArrayList;
import java.util.Scanner;

public class WilliamsonCSC422Assignment1 {

// As Pet Objects are created, they are added to this ArrayList
public static ArrayList<Pet> petList = new ArrayList<>();

    public static void main(String[] args) {
        // validator is what controls the while loop. While true, the program runs. If it is false, the program is closed
        boolean validator =  true;
        Scanner sc = new Scanner(System.in);
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
                        // calling updatePet method
                        updatePet();
                        break;
                    case 4:
                        // calling removePet method
                        removePet();
                        break;
                    case 5:
                        // calling searchName method
                        searchName();
                        break;
                    case 6:
                        // calling searchAge method
                        searchAge();
                        break;
                    case 7:
                        // switching the validator boolean from true to false. This breaks the while loop and closes to program
                        System.out.println("Goodbye!");
                        validator = false;
                        break;
                    default:
                        System.out.println("Error: Please type a number between 1 and 7.");
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
        System.out.println("2) Add more pets");
        System.out.println("3) Update an existing pet");
        System.out.println("4) Remove an existing pet");
        System.out.println("5) Search pets by name");
        System.out.println("6) Search pets by age");
        System.out.println("7) Exit program");
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
                        Pet newPet = new Pet(splitData[0], age);
                        petList.add(newPet);
                        i++;
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
    }
    // searchName method searches pets by the name entered
    public static void searchName(){
        System.out.print("\nEnter a name to search: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("\n+---------------------+");
        System.out.printf("|%-3s", " ID ");
        System.out.printf("|%-10s", " NAME ");
        System.out.printf("|%-4s", " AGE ");
        System.out.print("|\n");
        System.out.println("+---------------------+");
        // rows counts how many pets are printed
        int rows = 0;
        // looping through each index of the array
        for(int i = 0; i < petList.size(); i++){
            // if the name entered matches a name that is in the petList ArrayList, it is displayed
            if (name.toLowerCase().equals(petList.get(i).getName().toLowerCase())){
                System.out.printf("|%3d" , i);
                System.out.print(" | ");
                System.out.printf("%-9s" , petList.get(i).getName());
                System.out.printf("|%4d" , petList.get(i).getAge());
                System.out.print(" |\n");
                rows++;
            }
        }
        System.out.println("+---------------------+");
        System.out.println(rows + " rows in set.\n");
    }
    // searchAge method searches pets by the age entered
    public static void searchAge(){
        // loop runs until break is read after input is validated as an int
        while(true){
            System.out.print("\nEnter an age to search: ");
            Scanner sc = new Scanner(System.in);
            String strAge = sc.nextLine();
            // checking if input is an int
            Boolean ageCheck = isInt(strAge);
            if (ageCheck){
                int age = Integer.parseInt(strAge);
                System.out.println("\n+---------------------+");
                System.out.printf("|%-3s", " ID ");
                System.out.printf("|%-10s", " NAME ");
                System.out.printf("|%-4s", " AGE ");
                System.out.print("|\n");
                System.out.println("+---------------------+");
                // rows counts how many pets are printed
                int rows = 0;
                // looping through each index of the array
                for(int i = 0; i < petList.size(); i++){
                    // if the age entered matches an age that is in the petList ArrayList, it is displayed
                    if (age == petList.get(i).getAge()){
                        System.out.printf("|%3d" , i);
                        System.out.print(" | ");
                        System.out.printf("%-9s" , petList.get(i).getName());
                        System.out.printf("|%4d" , petList.get(i).getAge());
                        System.out.print(" |\n");
                        rows++;
                    }
                }
                System.out.println("+---------------------+");
                System.out.println(rows + " rows in set.\n");
                break;
            }
            else{
                System.out.println("Error: Please type an integer as an age.");
            }
        }
    }
    public static void updatePet(){
        // calling petDisplay method to show all pets to the user
        petDisplay();
        while(true){
            System.out.print("\nEnter the pet ID to update: ");
            Scanner sc = new Scanner(System.in);
            String strId = sc.nextLine();
            // calling isInt method to make sure the id entered is a number
            boolean idCheck = isInt(strId);
            if (idCheck){
                int id = Integer.parseInt(strId);
                if (id < petList.size()){
                    while(true){
                        System.out.print("Enter new name and new age: ");
                        String petData = sc.nextLine();
                        String[] splitData = petData.split(" ");
                        // validating user input
                        if (splitData.length == 2){
                            boolean ageCheck = isInt(splitData[1]);
                            if (ageCheck){
                                // calling isInt method to make sure the user entered a number
                                int age = Integer.parseInt(splitData[1]);
                                // printing the old name and age to the user
                                System.out.println(petList.get(id).getName() + " " + petList.get(id).getAge() + " changed to " + splitData[0] + " " + age + ".");
                                // setting the pet at the id entered to the name and age entered by the user
                                petList.get(id).setName(splitData[0]);
                                petList.get(id).setAge(age);
                                // breaking inner loop
                                break;
                            }
                            else{
                                System.out.println("Error: The age entered is not an integer.");
                            }
                        }
                        else{
                            System.out.println("Error: Make sure you enter a one word name and an age separated by a space.");
                        }
                    }
                    // breaking outer loop
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