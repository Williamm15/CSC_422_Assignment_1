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
            int input = sc.nextInt();
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
                    // switching the validator boolean from true to false. This breaks the while loop and closes to program
                    System.out.println("Goodbye!");
                    validator = false;
                    break;
            }
        }while(validator);
    }
    // this method displays the menu of options to the user
    public static void menuDisplay(){
        System.out.println("What would you like to do?");
        System.out.println("1) View all pets");
        System.out.println("2) Add more pets");
        System.out.println("3) Exit program");
    }
    // this method displays the data that is the the petList ArrayList to the user
    public static void petDisplay(){
        System.out.println("+---------------------+");
        System.out.println("| ID | NAME     | AGE |");
        System.out.println("+---------------------+");
        for (int i = 0; i < petList.size(); i++){
            System.out.println("| " + i + "  |  " + petList.get(i).getName() + "  | " + petList.get(i).getAge() + "   |");
        }
        System.out.println("+---------------------+");
    }
    public static void addPet(){
        while(true){
            System.out.print("Add pet (name, age): ");
            Scanner sc = new Scanner(System.in);
            String petData = sc.nextLine();
            if (petData.toLowerCase().equals("done")){
                break;
            }
            else{
                String[] splitData = petData.split(" ");
                int age = Integer.parseInt(splitData[1]);
                Pet newPet = new Pet(splitData[0], age);
                petList.add(newPet);
            }
        }
    }
}
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